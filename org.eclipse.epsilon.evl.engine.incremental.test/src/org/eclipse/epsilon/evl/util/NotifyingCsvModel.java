package org.eclipse.epsilon.evl.util;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.incremental.dom.IIncrementalModule;
import org.eclipse.epsilon.eol.incremental.models.IIncrementalModel;

/**
 * A CSV model that listens for changes in the underlying CSV file and notifies any changes.
 * It assumes that rows maintain order, i.e., deleted rows cause all rows to shift up, added rows
 * are at the end. 
 *  
 * @author Horacio Hoyos Rodriguez
 *
 */
public class NotifyingCsvModel extends CsvModel implements IIncrementalModel {
	
	private class CsvFileWatcher implements Runnable {

	    private final Path dir;
	    private final WatchService watcher;
	    private final WatchKey key;

	    @SuppressWarnings("unchecked")
	    <T> WatchEvent<T> cast(WatchEvent<?> event) {
	        return (WatchEvent<T>) event;
	    }

	    /**
	     * Creates a WatchService and registers the given directory
	     */
	    public CsvFileWatcher(Path csvFile) throws IOException {
	        this.dir = csvFile.getParent();
	        this.watcher = FileSystems.getDefault().newWatchService();
	        // We are only interested in changes in the CSV file
//	        this.key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
	        this.key = dir.register(watcher, ENTRY_MODIFY);
	    }

	    public void run() {
	        try {
	            for (;;) {
	                // wait for key to be signalled
	                WatchKey key = watcher.take();

	                if (this.key != key) {
	                    System.err.println("WatchKey not recognized!");
	                    continue;
	                }

	                for (WatchEvent<?> event : key.pollEvents()) {
	                    WatchEvent<Path> ev = cast(event);
	                    System.out.format("%s: %s\n", ev.kind(), dir.resolve(ev.context()));
	                    final Path changed = (Path) event.context();
	                    System.out.println(changed);
	                    if (changed.endsWith(file)) {
	                        BufferedReader changesReader;
							try {
								changesReader = Files.newBufferedReader(Paths.get(file), cs);
							} catch (IOException e) {
								// log!! e.printStackTrace();
								break;
							}
							/** The rows. */
							Collection<Map<String, Object>> newRows = new LinkedList<Map<String, Object>>();
							try {
								createRows(changesReader, newRows, knownHeaders, fieldSeparator, varargsHeaders);
							} catch (Exception e) {
								// log!! e.printStackTrace();
								break;
							}
							// Verify order of notifications?
		                    notifyDeleted(newRows);
		                    notifyChanged(newRows);
		                    nofityAdded(newRows);
	                    }
	                }
	                // reset key
	                if (!key.reset()) {
	                    break;
	                }
	            }
	        } catch (InterruptedException x) {
	            return;
	        }
	    }

		private void notifyChanged(Collection<Map<String, Object>> newRows) {
			// TODO Implement CsvFileWatcher.notifyChanged
			throw new UnsupportedOperationException("Unimplemented Method    CsvFileWatcher.notifyChanged invoked.");
		}

		private void nofityAdded(Collection<Map<String, Object>> newRows) {
			// TODO Implement CsvFileWatcher.nofityAdded
			throw new UnsupportedOperationException("Unimplemented Method    CsvFileWatcher.nofityAdded invoked.");
		}

		private void notifyDeleted(Collection<Map<String, Object>> newRows) {
			// TODO Implement CsvFileWatcher.notifyDeleted
			throw new UnsupportedOperationException("Unimplemented Method    CsvFileWatcher.notifyDeleted invoked.");
		}
	}

	private boolean deliver = false;
	
	Collection<IIncrementalModule> modules = new HashSet<IIncrementalModule>();

	private CsvFileWatcher watcher;

	private ExecutorService executor;

	private Future<?> future;
	
	@Override
	protected void loadModel() throws EolModelLoadingException {
		super.loadModel();
		// After model is loaded, register the watch service
		try {
			this.watcher = new CsvFileWatcher(Paths.get(this.file));
		} catch (IOException e) {
			throw new EolModelLoadingException(e, this);
		}
	}
	
	@Override
	public String getModelId() {
		return file;
	}

	@Override
	public boolean supportsNotifications() {
		return true;
	}

	@Override
	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
		if (deliver) {
			executor = Executors.newSingleThreadExecutor();
	        future = executor.submit(watcher);
	        executor.shutdown();

	        // Now, the watcher runs in parallel
	        // Do other stuff here

	        // Shutdown after 10 seconds
	        //executor.awaitTermination(10, TimeUnit.SECONDS);
	        // abort watcher
	        //future.cancel(true);
		}
		else {
			if (executor != null) {
				future.cancel(true);
				executor.shutdownNow();
			}
		}
		
	}

	@Override
	public void dispose() {
		super.dispose();
		if (executor != null) {
			future.cancel(true);
			executor.shutdownNow();
		}
	}

	@Override
	public boolean isDelivering() {
		return deliver;
	}

	@Override
	public Collection<IIncrementalModule> getModules() {
		return modules;
	}

}
