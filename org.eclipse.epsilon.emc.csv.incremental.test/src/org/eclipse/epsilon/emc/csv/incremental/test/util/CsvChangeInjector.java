package org.eclipse.epsilon.emc.csv.incremental.test.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvChangeInjector implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvChangeInjector.class);

	private int idField;
	private String targetId;
	private int fieldToChange;
	private String newFieldValue;
	private Path modelPath;
	private CSVFormat csvFormat;

	public CsvChangeInjector(int idField, String targetId, int fieldToChange, String newFieldValue,
			Path modelPath, CSVFormat csvFormat) {
				this.idField = idField;
				this.targetId = targetId;
				this.fieldToChange = fieldToChange;
				this.newFieldValue = newFieldValue;
				this.modelPath = modelPath;
				this.csvFormat = csvFormat;
	}

	@Override
	public void run() {
		
		LinkedHashMap<String, Integer> headerMap;
		List<List<String>> output = new ArrayList<>();
		logger.debug("Parsing file");
		try (
				Reader reader = Files.newBufferedReader(modelPath);
				CSVParser parser = new CSVParser(reader, csvFormat);
		) {
			headerMap = (LinkedHashMap<String, Integer>) parser.getHeaderMap();
			for (CSVRecord record : parser) {
				List<String> r = new ArrayList<>();
				record.forEach(r::add);
				if (record.get(idField).equals(targetId)) {
					logger.info("Found matching record, modifiyng field");
					r.set(fieldToChange, newFieldValue);
				}
				output.add(r);
			}	
		} catch (IOException e) {
			logger.error("Error reading from CSV file.", e);
			return;
		}
		if (!headerMap.isEmpty()) {
			logger.debug("Adding headers");
			String[] headers = headerMap.keySet().toArray(new String[headerMap.keySet().size()]);
			csvFormat = csvFormat.withHeader(headers).withSkipHeaderRecord(false);
		}
		logger.info("Writting to file");
		try (
				BufferedWriter writer = Files.newBufferedWriter(modelPath);
				CSVPrinter csvPrinter = csvFormat.print(writer);
				)
		{
			csvPrinter.printRecords(output);
		} catch (IOException e) {
			logger.error("Error writing to CSV file.", e);
		}
	}

}
