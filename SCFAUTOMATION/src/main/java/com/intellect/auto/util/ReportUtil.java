package com.intellect.auto.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportUtil {

	public static void configureExtHtmlReport(ExtentHtmlReporter htmlReporter) {
		 htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setDocumentTitle("AUTOMATION");
	        htmlReporter.config().setReportName("SCF 18.1 Report");
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public static void configureExtReport(ExtentReports reports) {
		reports.setSystemInfo("OS ::: ", System.getProperty("os.name"));
		reports.setSystemInfo("OS Version ::: ", System.getProperty("os.version"));
		reports.setSystemInfo("OS Version ::: ", System.getProperty("java.runtime.version"));
		reports.setSystemInfo("Host Name ::: ", System.getProperty("user.name"));
		reports.setSystemInfo("Environment ::: ", "SIT");
		reports.setSystemInfo("User Name ::: ", System.getProperty("user.name"));
		
	}

}
