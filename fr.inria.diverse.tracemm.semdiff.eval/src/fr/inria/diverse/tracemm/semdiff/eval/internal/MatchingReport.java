package fr.inria.diverse.tracemm.semdiff.eval.internal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MatchingReport {
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String ENTRY_SEPARATOR = ";";
	private static final String REPORT_FOLDER = "report/";
	private static final String REPORT_FILEENDING = ".csv";
	
	private List<MatchingReportEntry> entries = new ArrayList<MatchingReportEntry>();
	
	public void addMatchingReportEntry(MatchingReportEntry entry) {
		entries.add(entry);
	}
	
	public void printReportToFile() {
		File file = new File(REPORT_FOLDER + getReportFilename());
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(printReport());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String printReport() {
		StringBuffer str = new StringBuffer();
		str.append(printHeader());
		for (MatchingReportEntry entry : entries) {
			str.append(entry.getLeftTracemodel());
			str.append(ENTRY_SEPARATOR);
			str.append(entry.getRightTracemodel());
			str.append(ENTRY_SEPARATOR);
			str.append(entry.getMatchingTime());
			str.append(LINE_SEPARATOR);
		}
		return str.toString();
	}
	
	private String printHeader() {
		return "left" + ENTRY_SEPARATOR + "right" + ENTRY_SEPARATOR + "matchingtime" + LINE_SEPARATOR;
	}
	
	private String getReportFilename() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return dateFormat.format(new Date()) + REPORT_FILEENDING;
	}
}
