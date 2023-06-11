package api.utilities.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportsManager implements ITestListener {
    public ExtentSparkReporter sparkReport;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;

    public void onStart(ITestContext testContext) {
        String TimeStamp = new SimpleDateFormat("dd-MM-yyy HH-mm-ss").format(new Date());
        repName="Test-Report"+TimeStamp+".html";
//------------------------------------------------------------------------------------------------
        sparkReport = new ExtentSparkReporter(".\\reports\\"+repName);
        sparkReport.config().setDocumentTitle("RestAssuredAutomationProject");
        sparkReport.config().setTheme(Theme.DARK);
        sparkReport.config().setReportName("TestAPIDemoWebsite");
//------------------------------------------------------------------------------------------------
        extent = new ExtentReports();
        extent.attachReporter(sparkReport);
        extent.setSystemInfo("Application Name: ", "Pest Store Users API");
        extent.setSystemInfo("Executed on Browser: ", System.getProperty("browser"));
        extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
        extent.setSystemInfo("Environment: ", "QA");
        extent.setSystemInfo("User: ", "Toma");
    }
    //------------------------------------------------------------------------------------------------
    public void onTestSuccess(ITestResult result)
    {
      test= extent.createTest(result.getName());
      test.assignCategory(result.getMethod().getGroups());
      test.createNode(result.getName());
      test.log(Status.PASS,"Test Passed");
    }
    //------------------------------------------------------------------------------------------------
    public void onTestFailure(ITestResult result)
    {
        test= extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,"Test Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }
    //------------------------------------------------------------------------------------------------

    public void onTestSkipped(ITestResult result)
    {
        test= extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,"Test Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }
    //------------------------------------------------------------------------------------------------
    public void onFinish(ITestContext context)
    {
        extent.flush();
    }
    //------------------------------------------------------------------------------------------------
}