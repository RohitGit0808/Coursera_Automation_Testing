package com.identifycourses.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.identifycourses.models.SearchResults;

public class CsvUtil {
	private static final Logger logger = LogManager.getLogger(CsvUtil.class);

	public static void writeSearchResultsToCsv(String relativeCsvPathFromProjectRoot, List<SearchResults> searchResults) {
		Path projectRoot = Paths.get(System.getProperty("user.dir"));
		Path csvPath = projectRoot.resolve(relativeCsvPathFromProjectRoot);

		try {
			Files.createDirectories(csvPath.getParent());

			try (BufferedWriter writer = Files.newBufferedWriter(
					csvPath,
					StandardCharsets.UTF_8,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING)) {
				writer.write("Course Name,Rating,Duration");
				writer.newLine();

				if (searchResults != null) {
					for (SearchResults result : searchResults) {
						String line = new StringBuilder()
								.append(escapeCsv(result.getCourseName())).append(',')
								.append(escapeCsv(result.getRating())).append(',')
								.append(escapeCsv(result.getDuration()))
								.toString();
						writer.write(line);
						writer.newLine();
					}
				}
			}
			logger.info("Wrote {} search result(s) to {}", searchResults == null ? 0 : searchResults.size(), csvPath.toString());
		} catch (IOException e) {
			logger.error("Failed to write search results to CSV at " + csvPath, e);
		}
	}

	private static String escapeCsv(String value) {
		if (value == null) {
			return "";
		}
		boolean containsSpecial = value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r");
		String escaped = value.replace("\"", "\"\"");
		return containsSpecial ? "\"" + escaped + "\"" : escaped;
	}
} 