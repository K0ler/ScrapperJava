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
				"https://gamelaunch.wazdan.com/demo-demo/gamelauncher?license=wazdancom&mode=demo&game=406&platform=desktop");
		pf.resetVarTemp();
		return pf.checkIfPageLoaded();

	}

	public boolean check_canvas() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(canvas));
		return canvas.isDisplayed();
	}

	public void reset_listener() throws InterruptedException {
		pf.initListenerComplete();
	}
	
	public boolean check_message(String message, int numbersOfRerun) throws InterruptedException {
		boolean result = false;
		do {
			Thread.sleep(1000);
			result = pf.checkResponse(message);
			numbersOfRerun--;
		} while (numbersOfRerun > 0 && result == false);
		return result;
	}
	
	public boolean check_message(String message, boolean resetMessages) throws InterruptedException {
		try {
		wait.until(ExpectedConditions.jsReturnsValue("if(window.varTemp.includes('" + message + "')) return 'true';"));
		} catch (Exception e) {
			return false;
		}
		if(resetMessages==true) pf.resetVarTemp();
		return true;
	}

	public boolean press_space() throws InterruptedException {
		canvas.sendKeys(Keys.SPACE);
		return true;
	}
	
	

}