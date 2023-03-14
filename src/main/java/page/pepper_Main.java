package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// page_url = about:blank
public class pepper_Main {

    public pepper_Main(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}