package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import main.PUtils;
import static main.Main.driver;
import static main.Main.wait;

import org.openqa.selenium.Keys;

public class WebPageComponent {

	public WebPageComponent(WebDriver driver) throws InterruptedException {
		PageFactory.initElements(driver, this);
	}
	
	PUtils pf = new PUtils();

	@FindBy(tagName = "canvas")
	private static WebElement canvas;

	public boolean open_page() throws InterruptedException {
		driver.get(
				"https://www.otomoto.pl/");
		return pf.checkIfPageLoaded();

	}
	
	

}