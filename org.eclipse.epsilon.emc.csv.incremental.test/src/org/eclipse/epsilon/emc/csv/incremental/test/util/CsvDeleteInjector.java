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

public class CsvDeleteInjector implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvDeleteInjector.class);


	private int idField;
	private String deletedRowId;
	private CSVFormat csvFormat;
	private Path path;

	public CsvDeleteInjector(int idField, String deletedRowId, Path path, CSVFormat csvFormat) {
		this.idField = idField;
		this.deletedRowId = deletedRowId;
		this.path = path;
		this.csvFormat = csvFormat;
	}

	@Override
	public void run() {
		LinkedHashMap<String, Integer> headerMap;
		List<List<String>> output = new ArrayList<>();
		logger.debug("Parsing file");
		try (
				Reader reader = Files.newBufferedReader(path);
				CSVParser parser = new CSVParser(reader, csvFormat);
		) {
			headerMap = (LinkedHashMap<String, Integer>) parser.getHeaderMap();
			for (CSVRecord record : parser) {
				List<String> r = new ArrayList<>();
				if (record.get(idField).equals(deletedRowId)) {
					continue;
				}
				record.forEach(r::add);
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
				BufferedWriter writer = Files.newBufferedWriter(path);
				CSVPrinter csvPrinter = csvFormat.print(writer);
	        )
		{
			csvPrinter.printRecords(output);
		} catch (IOException e) {
			logger.error("Error writing to CSV file.", e);
		}
	}

}
