package org.eclipse.epsilon.emc.csv.incremental.test.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvAddRowInjector implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(CsvAddRowInjector.class);

	private String[] rowData;
	private Path modelPath;
	private CSVFormat csvFormat;
	private CsvAppendMethod appendMethod;
	

	public CsvAddRowInjector(String[] data, Path csvFilePath, CSVFormat withFirstRecordAsHeader,
			CsvAppendMethod random) {
		this.rowData = data;
		this.modelPath = csvFilePath;
		this.csvFormat = withFirstRecordAsHeader;
		this.appendMethod = random;
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
			List<CSVRecord> records = parser.getRecords();
			int position = 0;
			if (appendMethod.equals(CsvAppendMethod.APPEND)) {
				position = records.size()-1;
			}
			else if (appendMethod.equals(CsvAppendMethod.RANDOM)) {
				Random rand = new Random();
				position = rand.nextInt(records.size());
			}
			CSVRecord record;
			for (int i = 0; i < records.size(); i++) {
				List<String> r = new ArrayList<>();
				record = records.get(i);
				record.forEach(r::add);
				output.add(r);
			}
			output.add(position, Arrays.asList(rowData));
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
				CSVPrinter csvPrinter = csvFormat.print(writer);// new CSVPrinter(writer, csvFormat);
	        )
		{
			csvPrinter.printRecords(output);
		} catch (IOException e) {
			logger.error("Error writing to CSV file.", e);
		}
	}
}
