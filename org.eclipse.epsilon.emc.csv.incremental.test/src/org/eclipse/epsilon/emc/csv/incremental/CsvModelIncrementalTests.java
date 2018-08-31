package org.eclipse.epsilon.emc.csv.incremental;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.eclipse.epsilon.base.incremental.execute.IIncrementalModule;
import org.eclipse.epsilon.base.incremental.models.IIncrementalModel;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.csv.CsvModel;
import org.eclipse.epsilon.emc.csv.incremental.test.CsvIncrementalModelTestSuite;
import org.eclipse.epsilon.emc.csv.incremental.test.util.AbstractModule;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvAppendMethod;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvChangeInjector;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvDeleteInjector;
import org.eclipse.epsilon.emc.csv.incremental.test.util.CsvMergeFileInjector;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.junit.Before;
import org.junit.Test;

public class CsvModelIncrementalTests {
	
	static final String CHANGED_ROW_EMAIL_NEW_VALUE = "new.email@changes.org";
	static final String CHANGED_ROW_EMAIL_ID = "386-53-1139";
	static final String DELETED_ROW_ID = "164-18-3409";
	private static final String NEW_ROW_ID = "833-32-9040";
	
	private Runnable changeInjector;
	
	private Runnable deleteInjector;
	
	private Runnable newObjectInjector;
	
	private class EventResult {
		
		private final String objectId;
		private final Object object;
		private final String propertyName;
		
		public EventResult(String objectId, Object object, String propertyName) {
			super();
			this.objectId = objectId;
			this.object = object;
			this.propertyName = propertyName;
		}
		
		public String getObjectId() {
			return objectId;
		}
		
		public Object getObject() {
			return object;
		}
		
		public String getPropertyName() {
			return propertyName;
		}
		
	}
	
	private class LightModule extends AbstractModule {
		
		private final BlockingQueue<EventResult> drop;
		
		public LightModule(BlockingQueue<EventResult> drop) {
			super();
			this.drop = drop;
		}

		@Override
		public void onChange(IIncrementalModel model, Object object, String propertyName) {
			try {
				drop.put(new EventResult(model.getElementId(object), object, propertyName));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onCreate(IIncrementalModel model, Object object) {
			try {
				drop.put(new EventResult(null, object, null));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onDelete(IIncrementalModel model, Object object) {
			try {
				drop.put(new EventResult(model.getElementId(object), object, null));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private CsvModelIncremental model;
	private Future<?> future;
	
	public static String[] recordToArray(CSVRecord csvRecord) {
		String[] result = new String[csvRecord.size()];
		int i = 0;
		Iterator<String> it = csvRecord.iterator();
		while(it.hasNext()) {
			result[i++] = it.next();
		}
		return result;
	}

	@Before
	public void setup() throws EolModelLoadingException, IOException {
		File tmpFile = stream2file(CsvIncrementalModelTestSuite.class.getResource("BASE_MOCK_DATA.csv").openStream(),
				"csvTest", "tmp");
		StringProperties properties = new StringProperties();
		properties.put(CsvModel.PROPERTY_NAME, "people");
		properties.put(CsvModel.PROPERTY_HAS_KNOWN_HEADERS, "true");
		properties.put(CsvModel.PROPERTY_ID_FIELD, "id");
		properties.put(CsvModel.PROPERTY_HAS_VARARGS_HEADERS, "true");
		Path path = tmpFile.toPath();
		properties.put(CsvModel.PROPERTY_FILE, path.toString());
		model = new CsvModelIncremental();
		model.load(properties);
		changeInjector = new CsvChangeInjector(0, CHANGED_ROW_EMAIL_ID, 3, CHANGED_ROW_EMAIL_NEW_VALUE,
				path, CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader());
		
		deleteInjector = new CsvDeleteInjector(0, DELETED_ROW_ID,
				path, CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader());
		
		File newData = stream2file(CsvIncrementalModelTestSuite.class.getResource("NEW_MOCK_DATA.csv").openStream(),
				"csvTestNewData", "tmp");
		newObjectInjector = new CsvMergeFileInjector(path,
				newData.toPath(),
				CSVFormat.RFC4180.withDelimiter(',').withFirstRecordAsHeader(),
				CsvAppendMethod.APPEND);
	}

	
	@Test
	public void testChangesFromFile() throws Exception {
		
		BlockingQueue<EventResult> drop = new SynchronousQueue<EventResult> ();
		IIncrementalModule moduleMock = new LightModule(drop);
		model.getModules().add(moduleMock );
		model.setDeliver(true);
		ExecutorService executor = Executors.newSingleThreadExecutor();
        future = executor.submit(changeInjector);
        executor.shutdown();
        // Wait for changes in file to finish
        while (future.isDone()) { }
        // Wait for event
        EventResult result = drop.take();
        assertThat("Changed row id does not match", result.getObjectId(), is(CHANGED_ROW_EMAIL_ID));
        assertThat("Changed property does not match", result.getPropertyName(), is("email"));
        Object changed = result.getObject();
        assertThat(changed, is(instanceOf(Map.class)));
        @SuppressWarnings("unchecked")
		Map<String, Object> row = (Map<String, Object>)changed;
        assertThat("New property value does not match", row.get("email"), is(CHANGED_ROW_EMAIL_NEW_VALUE));
        executor = Executors.newSingleThreadExecutor();
        future = executor.submit(deleteInjector);
        executor.shutdown();
        // Wait for changes in file to finish
        while (future.isDone()) { }
        // Wait for event
        result = drop.take();
        assertThat("Deleted row id does not match", result.getObjectId(), is(DELETED_ROW_ID));
        executor = Executors.newSingleThreadExecutor();
        future = executor.submit(newObjectInjector);
        executor.shutdown();
        // Wait for changes in file to finish
        while (future.isDone()) { }
        // Wait for event
        result = drop.take();
        changed = result.getObject();
        assertThat(changed, is(instanceOf(Map.class)));
		row = (Map<String, Object>)changed;
		assertThat("New row id does not match", row.get("id"), is(NEW_ROW_ID));
		model.setDeliver(false);
	}
	
	public static File stream2file (InputStream in, String name, String extension) throws IOException {
        
		byte[] buffer = new byte[in.available()];
	    in.read(buffer);
		final File tempFile = File.createTempFile(name, extension);
        tempFile.deleteOnExit();
        try (
        		FileOutputStream out = new FileOutputStream(tempFile);
        		OutputStream outStream = new FileOutputStream(tempFile);) {
        	
        		outStream.write(buffer);
        }
        in.close();
        return tempFile;
    }
}
