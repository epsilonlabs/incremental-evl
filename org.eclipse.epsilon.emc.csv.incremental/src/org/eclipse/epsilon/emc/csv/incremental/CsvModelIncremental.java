package org.eclipse.epsilon.emc.csv.incremental;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.epsilon.base.incremental.exceptions.EolIncrementalExecutionException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotInstantiableModelElementValueException;
import org.eclipse.epsilon.base.incremental.exceptions.models.NotSerializableModelException;
import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.base.incremental.trace.impl.MemoryModelTraceFactory;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A CSV model that attaches a watch service to the CSV file location in order
 * to detect changes in the file.
 * 
 * @author Horacio Hoyos Rodriguez
 *
 */
public class CsvModelIncremental extends CsvModel implements IIncrementalModel {

	private static final Logger logger = LoggerFactory.getLogger(CsvModelIncremental.class);

	/**
	 * A runnable to listen for changes in the CSV file.
	 * 
	 * @author Horacio Hoyos Rodriguez
	 *
	 */
	private class CsvFileWatcher implements Runnable {

		private final Path dir;
		private final WatchService watcher;
		private final WatchKey key;
		private CsvModelIncremental model;
		private boolean working = false;

		@SuppressWarnings("unchecked")
		<T> WatchEvent<T> cast(WatchEvent<?> event) {
			return (WatchEvent<T>) event;
		}

		/**
		 * Creates a WatchService and registers the given directory
		 */
		public CsvFileWatcher(Path csvFile, CsvModelIncremental model) throws IOException {
			this.model = model;
			this.dir = csvFile.getParent();
			this.watcher = FileSystems.getDefault().newWatchService();
			this.key = dir.register(watcher, ENTRY_MODIFY);
		}

		public void run() {
			try {
				logger.info("Listening to changes in the CSV file");
				for (;;) {
					working = false;
					// wait for key to be signalled
					WatchKey key = watcher.take();
					logger.debug("key signaled");
					if (this.key != key) {
						logger.error("WatchKey not recognized!");
						continue;
					}

					for (WatchEvent<?> event : key.pollEvents()) {
						WatchEvent.Kind<?> kind = event.kind();
						// This key is registered only for ENTRY_MODIFY events,
						// but an OVERFLOW event can occur regardless if events
						// are lost or discarded.
						if (kind == OVERFLOW) {
							logger.info("key event was OVERFLOW");
							continue;
						}
						WatchEvent<Path> ev = cast(event);
						logger.debug("{}: {}\n", ev.kind(), dir.resolve(ev.context()));
						final Path changed = ev.context();
						if (file.endsWith(changed.toString())) {
							working = true;
							logger.debug("model file changed");
							BufferedReader changesReader;
							try {
								changesReader = Files.newBufferedReader(Paths.get(file), cs);
							} catch (IOException e) {
								logger.error("Exception creating reador from file.", e);
								break;
							}
							/** The rows. */
							List<Map<String, Object>> newRows;
							try {
								newRows = (List<Map<String, Object>>) CsvModel.createRows(changesReader, knownHeaders,
										fieldSeparator, varargsHeaders);
							} catch (Exception e) {
								logger.error("Exception reading new rows.", e);
								break;
							}
							// Load the new rows into the model
							logger.debug("Copy oldRows");
							List<Map<String, Object>> oldRowsSorted = new LinkedList<>(model.rows);
							model.rows = newRows;
							model.clearCache();
							// Sort both rows by id.
							logger.debug("Copy newRows");
							List<Map<String, Object>> newRowsSorted = new LinkedList<>(newRows);
							logger.debug("Sorting");
							Collections.sort(oldRowsSorted, RowComparator);
							Collections.sort(newRowsSorted, RowComparator);
							detectChanges(oldRowsSorted, newRowsSorted);
						}
					}
					// Reset the key -- this step is critical if you want to
					// receive further watch events. If the key is no longer valid,
					// the directory is inaccessible so exit the loop.
					boolean valid = key.reset();
					if (!valid) {
						break;
					}
				}
			} catch (InterruptedException x) {
				logger.info("CvsFileWatcher interrupted.");
				return;
			}
		}

		@SuppressWarnings("unchecked")
		private void detectChanges(List<Map<String, Object>> oldRows, List<Map<String, Object>> newRows) {

			logger.info("detectChanges");
			Iterator<Map<String, Object>> oldIterator = oldRows.iterator();
			Iterator<Map<String, Object>> newIterator = newRows.iterator();
			logger.debug("oldRows size = " + oldRows.size());
			logger.debug("newRows size = " + newRows.size());
			int cmpRslt = 0;
			Map<String, Object> oldRow = null;
			Map<String, Object> newRow = null;
			while (oldIterator.hasNext() && newIterator.hasNext()) {
				// Use the compare result to advance the iterators. If 0 advance both, if <0
				// advance old until we match,
				// if >0 advance new till we match
				if (cmpRslt == 0) {
					oldRow = oldIterator.next();
					newRow = newIterator.next();
				} else if (cmpRslt < 0) {
					oldRow = oldIterator.next();
				} else {
					newRow = newIterator.next();
				}
				logger.debug("comparing {} to {}", oldRow, newRow);
				cmpRslt = RowComparator.compare(oldRow, newRow);
				logger.debug("Compare result was: {}", cmpRslt);
				if (cmpRslt == 0) {
					logger.info("Rows match, comparing values");
					// Same id, compare changes
					if (model.knownHeaders) {
						for (String k : oldRow.keySet()) {
							Object oldValue = oldRow.get(k);
							Object newValue = newRow.get(k);
							logger.debug("comparing field {}: {} vs. {}", k, oldValue, newValue);
							if (!oldValue.equals(newValue)) {
								logger.info("Change detected");
								if (model.isDelivering()) {
									logger.info("Notifying listening modules");
									for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
										try {
											m.onChange(model, newRow, k);
										} catch (EolRuntimeException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							}
						}
					} else {
						List<String> oldValue = (List<String>) oldRow.get(CsvModel.HEADERLESS_FIELD_NAME);
						List<String> newValue = (List<String>) newRow.get(CsvModel.HEADERLESS_FIELD_NAME);
						logger.debug("comparing all fields {} vs. {}", oldValue, newValue);
						if (!oldValue.equals(newValue)) {
							logger.info("Change detected");
							if (model.isDelivering()) {
								logger.info("Notifying listening modules");
								for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
									try {
										m.onChange(model, newRow, CsvModel.HEADERLESS_FIELD_NAME);
									} catch (EolRuntimeException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}

				} else if (cmpRslt < 0) {
					logger.info("Row was deleted: {}", oldRow);
					// The old row is not in the new rows, signal a deletion
					if (model.isDelivering()) {
						logger.info("Notifying listening modules");
						for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
							try {
								m.onDelete(model, oldRow);
							} catch (EolRuntimeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} else {
					logger.info("Row was added: {}", newRow);
					// The new row is not in the old rows, signal an instantiation
					if (model.isDelivering()) {
						logger.info("Notifying listening modules");
						for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
							try {
								m.onCreate(model, newRow);
							} catch (EolRuntimeException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			// Deal with different number of rows
			while (newIterator.hasNext()) {
				newRow = newIterator.next();
				logger.info("Additional row found: {}", newRow);
				// Signal instantiation
				if (model.isDelivering()) {
					logger.info("Notifying listening modules");
					for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
						try {
							m.onCreate(model, newRow);
						} catch (EolRuntimeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
			while (oldIterator.hasNext()) {
				oldRow = oldIterator.next();
				logger.info("Deleting surplus row: {}", oldRow);
				// The old row is not in the new rows, signal a deletion
				if (model.isDelivering()) {
					logger.info("Notifying listening modules");
					for (IIncrementalModule<?, ?, ?, ?> m : model.getModules()) {
						try {
							m.onDelete(model, oldRow);
						} catch (EolRuntimeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		public boolean isWorking() {
			return working;
		}

		/**
		 * Compare rows using the idFiled (either name or position)
		 */
		private Comparator<Map<String, Object>> RowComparator = new Comparator<Map<String, Object>>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(Map<String, Object> row1, Map<String, Object> row2) {
				String id1;
				String id2;
				if (model.knownHeaders) {
					id1 = (String) row1.get(model.idFieldName);
					id2 = (String) row2.get(model.idFieldName);
				} else {
					id1 = (String) ((List<String>) row1.get("field")).get(model.idFieldIndex);
					id2 = (String) ((List<String>) row2.get("field")).get(model.idFieldIndex);
				}
				return id1.compareTo(id2);
			}
		};
	}

	private boolean deliver;
	private Collection<IIncrementalModule<?, ?, ?, ?>> modules = new HashSet<IIncrementalModule<?, ?, ?, ?>>();

	private CsvFileWatcher watcher;

	private ExecutorService executor;

	private Future<?> future;

	@Override
	protected void loadModel() throws EolModelLoadingException {
		super.loadModel();
		logger.info("Model loaded, creating watcher");
		try {
			this.watcher = new CsvFileWatcher(Paths.get(this.file), this);
		} catch (IOException e) {
			throw new EolModelLoadingException(e, this);
		}

	}

	@Override
	public String getModelUri() {
		return file;
	}

	@Override
	public boolean supportsNotifications() {
		return !(idFieldName.isEmpty() && (idFieldIndex == -1));
	}

	@Override
	// FIXME Use the elementids?
	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
		if (deliver) {
			logger.info("Runing watcher in separate thread.");
			executor = Executors.newSingleThreadExecutor();
			future = executor.submit(watcher);
			executor.shutdown();
		} else {
			if (executor != null) {
				logger.info("Stoping watcher.");
				while (watcher.isWorking()) { /* Wait */
					logger.debug("Wauting for watcher to finish");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break;
					}
				}
				future.cancel(true);
				executor.shutdownNow();
			}
		}
	}

	public boolean isBusy() {
		if (future.isCancelled() || !future.isDone()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isDelivering() {
		return deliver;
	}

	@Override
	public Collection<IIncrementalModule<?, ?, ?, ?>> getModules() {
		return modules;
	}

	@Override
	protected void disposeModel() {
		logger.info("Dispossing model");
		super.disposeModel();
		if (executor != null) {
			future.cancel(true);
			executor.shutdownNow();
		}
	}

	@Override
	public Set<String> getAllTypeNamesOf(Object instance) {
		// TODO Auto-generated method stub
		return super.getAllTypeNamesOf(instance);
	}

	@Override
	public String convertToString(Object instance) throws NotSerializableModelException {
		if (!owns(instance)) {
			throw new NotSerializableModelException("Can not convert objects that dont belong to this model.");
		}
		@SuppressWarnings("unchecked")
		Map<String, String> row = (Map<String, String>) instance;
		StringBuilder builder1 = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		Character sep = Character.MIN_VALUE;
		for (Entry<String, String> e : row.entrySet()) {
			builder1.append(e.getKey());
			builder1.append(sep);
			builder2.append(e.getValue());
			builder2.append(sep);
			sep = getFieldSeparator();
		}
		builder1.append("\n");
		builder1.append(builder2);
		return builder1.toString();

	}

	@Override
	public Object createFromString(String value) throws NotInstantiableModelElementValueException {
		InputStream is = new ByteArrayInputStream(value.getBytes());

		// read it with BufferedReader
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		List<Map<String, Object>> valuerows;
		try {
			valuerows = createRows(br, isKnownHeaders(), getFieldSeparator(), isVarargsHeaders());
		} catch (Exception e) {
			throw new NotInstantiableModelElementValueException("Unable to crate instance from value.", e);
		}
		Map<String, Object> row = valuerows.get(0);
		String id = getElementId(row);
		@SuppressWarnings("unchecked")
		Map<String, Object> existingRow = (Map<String, Object>) getElementById(id);
		if (!row.equals(existingRow)) {
			throw new NotInstantiableModelElementValueException("Craeted instance does noe belong to this model.", row);
		}
		return row;
	}

}
