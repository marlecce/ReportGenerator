package main.java.utils;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.TreeSet;

public class Writer {

	private WriterFormat format = null;

	// Delimiters that must be in the CSV file
	private static final String DELIMITER = ",";
	private static final String SEPARATOR = "\n";

	// File header
	private static final String HEADER = "IP Address,Number of requests,Percentage of the total amount of requests,Total Bytes sent,Percentage of the total amount of bytes";

	public Writer(WriterFormat format) {
		this.format = format;
	}

	/**
	 * 
	 * @param matches
	 */
	public void write(TreeSet<Match> matches) {

		if ( this.format.equals(WriterFormat.CSV) ) {			
			writeCSV(matches);
		}
		
		if ( this.format.equals(WriterFormat.JSON) ) {			
			writeJSON(matches);
		}
		
	}

	/**
	 * 
	 * @param matches
	 */
	private void writeCSV(TreeSet<Match> matches) {
		FileWriter file = null;

		try {
			file = new FileWriter("reports/ipaddr.csv");

			file.append(HEADER);

			file.append(SEPARATOR);

			Iterator<Match> it = matches.iterator();

			while (it.hasNext()) {
				Match match = (Match) it.next();

				file.append(match.getIp());
				file.append(DELIMITER);
				file.append(Integer.toString(match.getOKTotalRequests()));
				file.append(DELIMITER);
				file.append(Double.toString(match.getPercentageTotalRequests()));
				file.append(DELIMITER);
				file.append(Integer.toString(match.getTotalOKBytes()));
				file.append(DELIMITER);
				file.append(Double.toString(match.getPercentageTotalBytes()));
				file.append(SEPARATOR);
			}

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param matches
	 */
	private void writeJSON(TreeSet<Match> matches) {
		FileWriter file = null;

		try {
			file = new FileWriter("reports/ipaddr.json");
	
			file.append("{ \"rows\" : [");

			Iterator<Match> it = matches.iterator();
			int index = 1;
			
			while (it.hasNext()) {
				Match match = (Match) it.next();
				file.append("{");
				
				file.append(" \"ip\": \"" + match.getIp() + "\"");
				file.append(DELIMITER);
				file.append(" \"numberRequests\": \"" + Integer.toString(match.getOKTotalRequests()) + "\"");
				file.append(DELIMITER);
				file.append(" \"percentageTotalRequests\": \"" +Double.toString(match.getPercentageTotalRequests()) + "\"");
				file.append(DELIMITER);
				file.append(" \"totalBytesSent\": \"" +Integer.toString(match.getTotalOKBytes()) + "\"");
				file.append(DELIMITER);
				file.append(" \"percentageTotalBytes\": \"" +Double.toString(match.getPercentageTotalBytes()) + "\"");
				file.append(SEPARATOR);
				file.append("}");
				if ( index != matches.size() ) {
					file.append(DELIMITER);
				}
				index++;
			}
			
			file.append("]}");

			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
