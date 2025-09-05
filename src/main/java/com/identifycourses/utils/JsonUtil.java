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

import com.identifycourses.models.LanguageDetails;
import com.identifycourses.models.LanguageLevels;

public class JsonUtil {
	private static final Logger logger = LogManager.getLogger(JsonUtil.class);

	public static void writeLanguageDataToJson(String relativeJsonPathFromProjectRoot,
			List<LanguageDetails> languages,
			List<LanguageLevels> levels) {
		Path projectRoot = Paths.get(System.getProperty("user.dir"));
		Path jsonPath = projectRoot.resolve(relativeJsonPathFromProjectRoot);

		try {
			Files.createDirectories(jsonPath.getParent());

			String json = buildJson(languages, levels);
			try (BufferedWriter writer = Files.newBufferedWriter(
					jsonPath,
					StandardCharsets.UTF_8,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING)) {
				writer.write(json);
			}
			logger.info("Wrote language data to {} (languages: {}, levels: {})",
					jsonPath.toString(),
					languages == null ? 0 : languages.size(),
					levels == null ? 0 : levels.size());
		} catch (IOException e) {
			logger.error("Failed to write language data to JSON at " + jsonPath, e);
		}
	}

	private static String buildJson(List<LanguageDetails> languages, List<LanguageLevels> levels) {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("\"languages\":");
		sb.append('[');
		if (languages != null) {
			for (int i = 0; i < languages.size(); i++) {
				LanguageDetails ld = languages.get(i);
				sb.append('{')
					.append("\"language\":\"").append(escapeJson(ld.getLanguage())).append("\",")
					.append("\"count\":").append(ld.getCount())
					.append('}');
				if (i < languages.size() - 1) sb.append(',');
			}
		}
		sb.append(']');
		sb.append(',');
		sb.append("\"levels\":");
		sb.append('[');
		if (levels != null) {
			for (int i = 0; i < levels.size(); i++) {
				LanguageLevels ll = levels.get(i);
				sb.append('{')
					.append("\"languageLevel\":\"").append(escapeJson(ll.getLanguageLevel())).append("\",")
					.append("\"count\":").append(ll.getCount())
					.append('}');
				if (i < levels.size() - 1) sb.append(',');
			}
		}
		sb.append(']');
		sb.append('}');
		return sb.toString();
	}

	private static String escapeJson(String value) {
		if (value == null) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (c) {
				case '"': sb.append("\\\""); break;
				case '\\': sb.append("\\\\"); break;
				case '\b': sb.append("\\b"); break;
				case '\f': sb.append("\\f"); break;
				case '\n': sb.append("\\n"); break;
				case '\r': sb.append("\\r"); break;
				case '\t': sb.append("\\t"); break;
				default:
					if (c < 0x20) {
						sb.append(String.format("\\u%04x", (int)c));
					} else {
						sb.append(c);
					}
			}
		}
		return sb.toString();
	}
} 