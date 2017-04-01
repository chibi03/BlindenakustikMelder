package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class JSONDownloader {

	private static final String GET = "GET";
	private static final String CONFIG_PROPERTIES = "config.properties";

	public static Properties getProperies() throws FileNotFoundException {
		Properties prop = new Properties();

		String filename = CONFIG_PROPERTIES;
		InputStream input = JSONDownloader.class.getResourceAsStream(filename);
		if (input == null) {
			throw new FileNotFoundException("Sorry, unable to find " + filename);
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void getJSON(String dataUrl, String fileName) {
		try {
			URL url = new URL(dataUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(GET);
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
				String output;
				while ((output = br.readLine()) != null) {
					bufferedWriter.write(output);
					bufferedWriter.newLine();
					bufferedWriter.flush();
				}
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

}
