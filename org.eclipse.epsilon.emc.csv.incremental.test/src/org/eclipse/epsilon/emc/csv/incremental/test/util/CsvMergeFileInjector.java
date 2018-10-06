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

public class CsvMergeFileInjector implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvMergeFileInjector.class);


	private Path targetFile;
	private Path sourceFile;
	private CSVFormat csvFormat;
	private CsvAppendMethod appendMethod;

	public CsvMergeFileInjector(Path targetFile, Path sourceFile, CSVFormat csvFormat,
			CsvAppendMethod appendMethod) {
				this.targetFile = targetFile;
				this.sourceFile = sourceFile;
				this.csvFormat = csvFormat;
				this.appendMethod = appendMethod;
	}

	@Override
	public void run() {
		List<List<String>> newFields = new ArrayList<>();
		logger.debug("Parsing source file");
		try (
				Reader reader = Files.newBufferedReader(sourceFile);
				CSVParser parser = new CSVParser(reader, csvFormat);
		) {
			for (CSVRecord record : parser) {
				List<String> r = new ArrayList<>();
				record.forEach(r::add);
				newFields.add(r);
			}	
		} catch (IOException e) {
			logger.error("Error parsing CSV file.", e);
			return;
		}
		LinkedHashMap<String, Integer> headerMap;
		List<List<String>> output = new ArrayList<>();
		logger.debug("Parsing target file");
		try (
				Reader reader = Files.newBufferedReader(targetFile);
				CSVParser parser = new CSVParser(reader, csvFormat);
		) {
			headerMap = (LinkedHashMap<String, Integer>) parser.getHeaderMap();
			for (CSVRecord record : parser) {
				List<String> r = new ArrayList<>();
				record.forEach(r::add);
				output.add(r);
			}	
		} catch (IOException e) {
			logger.error("Error parsing CSV file.", e);
			return;
		}
		switch(appendMethod) {
		case APPEND:
			output.addAll(newFields);
			break;
		default:
			break;
		
		}
		if (!headerMap.isEmpty()) {
			String[] headers = headerMap.keySet().toArray(new String[headerMap.keySet().size()]);
			csvFormat = csvFormat.withHeader(headers).withSkipHeaderRecord(false);
		}
		logger.info("Writting to file");
		try (
				BufferedWriter writer = Files.newBufferedWriter(targetFile);
				CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat);
	        )
		{
			csvPrinter.printRecords(output);
		} catch (IOException e) {
			logger.error("Error writing CSV file.", e);
			e.printStackTrace();
		}
	}

}
