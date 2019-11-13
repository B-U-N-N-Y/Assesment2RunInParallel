package com.tutorialsninjaandheatclinic.TutorialsninjaAndHeatclinicAutomation.base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.tutorialsninjaandheatclinic.TutorialsninjaAndHeatclinicAutomation.constant.FileConstant;
import com.tutorialsninjaandheatclinic.TutorialsninjaAndHeatclinicAutomation.constant.GridConnection;
import com.tutorialsninjaandheatclinic.TutorialsninjaAndHeatclinicAutomation.utils.ReadPropertiesFile;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class operate to choose the driver and choose that it run with grid
 * connection or without grid connection
 * 
 * @author arjun.santra
 *
 */

public class TestBase {
	public WebDriver driver;
	public Properties baseClass;
	public String url;
	public String browser;
	int downloadFile;
	String con;
	String environmentVariable;

	String huburl;

	/**
	 * This method choose which browser invoke and also choose normal execution
	 * happen or grid execution happen
	 * 
	 * @param browser
	 * @param url
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@Parameters({ "browser" })
	@BeforeTest
	public void intitailizeBrowser(String browser) throws IOException {
		//this.browser = browser;
		baseClass = new ReadPropertiesFile().loadProperty(FileConstant.CONFIG_FILE);
		//url = baseClass.getProperty("url");
	//	browser = baseClass.getProperty("browser");
		con = baseClass.getProperty("connection");
		huburl = baseClass.getProperty("remoteconnectionurl");
		//environmentVariable=System.getenv("browser");
		//this.url = url;

		System.out.println("browser is " + browser);
		if (con.equals("normal")) {

			if (browser.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().arch64().setup();
				System.setProperty("webdriver.chrome.driver", FileConstant.CHROME_PATH);
				driver = new ChromeDriver();
				// driver.get(url);

			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().arch64().setup();
				driver = new FirefoxDriver();
				// driver.get(url);
			} else if (browser.equalsIgnoreCase("internet explorer")) {
				WebDriverManager.iedriver().arch32().setup();
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
				driver = new InternetExplorerDriver(ieCaps);
			}
		} else if (con.equalsIgnoreCase("grid")) {
			WebDriver drv = new GridConnection().gridCon(driver, browser, huburl);
			this.driver = drv;

		}
	}



	/**
	 * This method used to close the driver.
	 */
	@AfterTest()
	public void endTest() {
		driver.quit();
	}
}

//environmentVariable.equalsIgnoreCase("internet explorer")||
