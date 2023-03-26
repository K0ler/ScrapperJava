package main;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;

	@BeforeSuite
	public void setDriverPath() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		wait.pollingEvery(Duration.ofMillis(50));
		driver.manage().window().maximize();
		js = (JavascriptExecutor) driver;
	}
	
	@AfterSuite
	public void tearDown() throws IOException {
		//if (driver !=null)
		//	driver.quit();
	}
}
