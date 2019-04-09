package Extractor;

import java.io.*;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Extractor {
	public static void main(String[] args) throws IOException {

		Day[] StockData; // Important object array that holds all stock data

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Example URL: https://finance.yahoo.com/quote/%5EGSPC/history?period1=-630961200&period2=1543640400&interval=1d&filter=history&frequency=1d
		String enteredURL = br.readLine();

		long startTime = System.nanoTime();

		// Establish connection to Yahoo Finance
		URL url = new URL(enteredURL);
		URLConnection urlConn = url.openConnection();

		try {
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			return;
		}

		// Get stock data from Yahoo Finance as a string
		String entirePage = "";
		String line = br.readLine();
		while (line != null) {
			entirePage = entirePage + line;
			line = br.readLine();
		}
		String trimPage = entirePage.substring(entirePage.indexOf("HistoricalPriceStore") + 32);
		trimPage = trimPage.substring(0, trimPage.indexOf("]") + 1);
		System.out.println("Extracted Page Data:");
		System.out.println(trimPage);

		// Format the string into JSON & get stock info from JSON & store stock info in an array
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(trimPage);
			JSONArray array = (JSONArray) obj;

			StockData = new Day[array.size()];

			System.out.println("");
			System.out.println("Formatted & Stored Stock Data:");
			System.out.println("");

			for (int i = 0; i < array.size(); i++) {
				System.out.println("Daily Stock Information: ");
				System.out.println(array.get(i));

				JSONObject obj2 = (JSONObject) array.get(i);

				System.out.println("date=" + obj2.get("date"));
				System.out.println("open=" + obj2.get("open"));
				System.out.println("high=" + obj2.get("high"));
				System.out.println("low=" + obj2.get("low"));
				System.out.println("close=" + obj2.get("close"));
				System.out.println("adjclose=" + obj2.get("adjclose"));
				System.out.println("volume=" + obj2.get("volume"));
				System.out.println("");

				StockData[i] = new Day(
						Long.parseLong(obj2.get("date").toString()),
						Double.parseDouble(obj2.get("open").toString()),
						Double.parseDouble(obj2.get("high").toString()),
						Double.parseDouble(obj2.get("low").toString()),
						Double.parseDouble(obj2.get("close").toString()),
						Double.parseDouble(obj2.get("adjclose").toString()),
						Double.parseDouble(obj2.get("volume").toString()));
			}

		} catch (ParseException pe) {
			System.out.println("ERROR: " + pe);
			return;
		}
		System.out.println(System.nanoTime() - startTime);
	}
}
