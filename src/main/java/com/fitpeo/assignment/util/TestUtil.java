package com.fitpeo.assignment.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLECIT_WAIT = 10;

	public static List<HashMap<String, Object>> getJsonData(String filePath) throws IOException {
		// Read the JSON file content into a string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Initialize ObjectMapper for JSON deserialization
		ObjectMapper mapper = new ObjectMapper();

		// Deserialize the JSON content into a list of HashMaps
		List<HashMap<String, Object>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, Object>>>() {
				});

		return data;
	}
}
