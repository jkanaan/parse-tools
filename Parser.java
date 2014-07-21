package parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/* 
 * An expandable class that is set up to pull data from files with explicitly chosen delimiters 
 * and fields. The fields must appear in a particular order for the class to work and will be 
 * stored in an ArrayList of HashMaps (each HashMap contains the info for one item - this could 
 * be all of the details regarding one person, for example).
 */

public class Parser {

	/*
	 * The delimiters that will be used to separate fields in the file. One is
	 * for files that use the pipe symbol, one for files that use spaces, and
	 * one for files that use commas.
	 */
	final static String pipeDelimiter = "\\||\n";
	final static String spaceDelimiter = " |\n";
	final static String commaDelimiter = ",|\n";

	private String[] fields;

	public Parser() {
	}

	public Parser(String[] fields) {
		this.fields = fields;
	}

	/*
	 * Each of these parse functions corresponds to a particular delimiter and
	 * its fields. They route the appropriate file, delimiter, and set of fields
	 * to the main parse function. Each has a static and non-static version.
	 */
	public List<HashMap<String, String>> pipeParse(File pipe) {
		return (parse(pipe, fields, pipeDelimiter));
	}

	public List<HashMap<String, String>> spaceParse(File space) {
		return (parse(space, fields, spaceDelimiter));
	}

	public List<HashMap<String, String>> commaParse(File comma) {
		return (parse(comma, fields, commaDelimiter));
	}

	public static List<HashMap<String, String>> pipeParse(File pipe,
			String[] fields) {
		return (parse(pipe, fields, pipeDelimiter));
	}

	public static List<HashMap<String, String>> spaceParse(File space,
			String[] fields) {
		return (parse(space, fields, spaceDelimiter));
	}

	public static List<HashMap<String, String>> commaParse(File comma,
			String[] fields) {
		return (parse(comma, fields, commaDelimiter));
	}

	/*
	 * Non-static version of the main parse function.
	 */
	public List<HashMap<String, String>> parse(File file, String delimiter) {
		return parse(file, fields, delimiter);
	}

	/*
	 * The main, generic parse function that retrieves all of the data and puts
	 * it into an ArrayList of HashMaps.
	 */
	public static List<HashMap<String, String>> parse(File file,
			String[] fields, String delimiter) {
		try {
			Scanner scanner = new Scanner(file);
			scanner.useDelimiter(delimiter);
			/*
			 * ArrayList itemList will hold every HashMap containing a item's
			 * information.
			 */
			List<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>();
			/* HashMap itemInfo will hold 1 item's info */
			HashMap<String, String> itemInfo = new HashMap<String, String>();

			int count = 0;
			while (scanner.hasNext()) {
				String token = scanner.next().trim();
				// System.out.println(count%pipeFields.length+token);//Test Code
				/*
				 * The current field that the loop is extracting, based on the
				 * fields array.
				 */
				int currentField = count % fields.length;

				/*
				 * Creates a new HashMap to add info to, because 0 indicates a
				 * new item.
				 */
				if (currentField == 0) {
					itemInfo = new HashMap<String, String>();
				}

				/*
				 * Makes sure that all data of the same type has the same format
				 * (i.e "Male" vs. "M").
				 */
				// token = standardize(fields[currentField], token);

				/* Places a piece of data into a item's HashMap. */
				itemInfo.put(fields[currentField], token);

				/* Once the last field is reached... */
				if (currentField == fields.length - 1) {
					/* Adds an empty middle initial field, if it is missing. */
					if (!Arrays.asList(fields).contains("middleInitial")) {
						itemInfo.put("middleInitial", "");
					}
					/* Adds this item's info to the ArrayList to be returned. */
					itemList.add(itemInfo);
				}
				count++;
			}

			scanner.close();
			/*
			 * Returns an ArrayList of HashMaps
			 */
			return itemList;

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		return null;
	}
}
