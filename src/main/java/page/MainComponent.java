package page;

import main.PUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static main.Main.driver;

// page_url = https://justjoin.it/all/testing
public class MainComponent {

    PUtils pf = new PUtils();
    @FindBy(xpath = "//*[@id='filter_enum_body_type']")
    public WebElement TypNadwozia;

    @FindBy(xpath = "//*[@id='filter_enum_make']")
    public WebElement Markapojazdu;

    @FindBy(xpath = "//*[@id='filter_enum_model']")
    public WebElement Modelpojazdu;

    @FindBy(xpath = "//*[@id='filter_enum_generation']")
    public WebElement Generacja;

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    public WebElement buttonAccept;

    @FindBy(xpath = "//button[contains(@data-testid, 'submit')]")
    public WebElement buttonSubmit2;

    public MainComponent(WebDriver driver) throws InterruptedException {
        PageFactory.initElements(driver, this);
    }
    public String chooseNadwozie(String typ) throws InterruptedException {
        TypNadwozia.click();
        List<WebElement> options = TypNadwozia.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            System.out.printf(option.getText());
            if (option.getText().equals(typ))
            {
                option.click(); // click the desired option
                break;
            }
        }
        return TypNadwozia.getText();
    }

    public void acceptCookies() throws InterruptedException {
        WebElement banner = driver.findElement(By.cssSelector("div#onetrust-banner-sdk"));

        if (banner.isDisplayed()) {
            banner.findElement(By.cssSelector("button#onetrust-accept-btn-handler")).click();
        }
    }

    public String chooseMarka(String typ) throws InterruptedException {
        Markapojazdu.click();
        List<WebElement> options = Markapojazdu.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            String text = option.getText();
            String shortenedText = text.indexOf(" (") >= 0 ? text.substring(0, text.indexOf(" (")) : text;
            System.out.println(option.getText());
            if (shortenedText.equals(typ))
            {
                option.click(); // click the desired option
                break;
            }
        }
        return Markapojazdu.getText();
    }

    public String chooseModel(String typ) throws InterruptedException {
        Modelpojazdu.click();
        List<WebElement> options = Modelpojazdu.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            String text = option.getText();
            String shortenedText = text.indexOf(" (") >= 0 ? text.substring(0, text.indexOf(" (")) : text;
            System.out.printf(option.getText());
            if (shortenedText.equals(typ))
            {
                option.click(); // click the desired option
                break;
            }
        }
        return Modelpojazdu.getText();
    }

    public String chooseGeneracja(String typ) throws InterruptedException {
        Generacja.click();
        List<WebElement> options = Generacja.findElements(By.tagName("li"));
        for (WebElement option : options)
        {
            String text = option.getText();
            String shortenedText = text.indexOf(" (") >= 0 ? text.substring(0, text.indexOf(" (")) : text;
            System.out.println(option.getText());
            if (shortenedText.equals(typ))
            {
                option.click(); // click the desired option
                break;
            }
        }
        return Generacja.getText();
    }


}