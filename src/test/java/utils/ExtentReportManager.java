package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportManager {
    private static final ExtentReports extent = new ExtentReports();

    static {
        String reportPath = System.getProperty("user.dir") + "/target/reports/extent/extent.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Cucumber Test Report");
        reporter.config().setDocumentTitle("Automation Results");
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{
                ViewName.DASHBOARD,
                ViewName.TEST,
                ViewName.CATEGORY,
                ViewName.AUTHOR,
                ViewName.EXCEPTION
        }).apply();

        extent.attachReporter(reporter);
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }
}
