package com.example.demo.helper;

import java.io.*;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Tutorial;

public class CSVHelper {
	
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
	  // This method will check if the file if of format csv of not
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {

	      return false;
	    }
	    return true;
	  }
	  // This method will read CSV file and return it's data
	  public static List<Tutorial> csvToTutorials(InputStream is) {
		    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		        CSVParser csvParser = new CSVParser(fileReader,
		            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
		    	

		      List<Tutorial> tutorials = new ArrayList<Tutorial>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

		      for (CSVRecord csvRecord : csvRecords) {
		        Tutorial tutorial = new Tutorial(

		              Long.parseLong(csvRecord.get("Id")),
		              csvRecord.get("Title"),
		              csvRecord.get("Description"),
		              Boolean.parseBoolean(csvRecord.get("Published"))
		            );
		        System.out.println("-----");

		        tutorials.add(tutorial);
		      }
		      return tutorials;
		    } catch (IOException e) {
		      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		    }
		  }

}
	


