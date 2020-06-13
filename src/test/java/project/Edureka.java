package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

public class Edureka {
	
	public static WebDriver driver;
	static Properties prop= new Properties();
	static ExtentReports report = new ExtentReports("./Report/Report.html");
	static ExtentTest test = report.startTest("Edureka_Project_Test");
	public static void browserinvoke() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver=new ChromeDriver();
	}
	
	public static void browsermanage() {
		driver.manage().window().maximize();
	}
	
	public static void propertyfile() throws IOException {
		String file="./Input.properties";
		File ofile=new File(file);
		FileInputStream ifile=new FileInputStream(ofile);
		prop.load(ifile);
		System.out.println(prop.getProperty("URL"));
	}
	
	public static void launchurl() throws IOException {
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		String title=driver.getTitle();
		if(title.equals("Home | Simple PHP Website")) {
			test.log(LogStatus.PASS, "Navigated to"+" "+title+" "+"page");
		}
		else {
			test.log(LogStatus.FAIL, "Not Navigated to"+" "+title+" "+"page");
		}
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./Screenshot/Homepage.jpg"));	
		test.log(LogStatus.PASS, test.addScreenCapture("./Screenshot/Homepage.jpg")+"Open Simple PHP Website");
	}
	
	public static void main(String[] args) throws IOException {
		propertyfile();
		browserinvoke();
		browsermanage();
		launchurl();
		report.endTest(test);
		report.flush();
	}


}
