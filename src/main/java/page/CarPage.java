package page;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static main.Main.driver;

// page_url = https://www.otomoto.pl/osobowe/renault/megane?search%5Bfilter_enum_generation%5D=gen-iv-2016
public class CarPage {
    @FindBy(xpath = "//span[contains(@class, 'offer-title')]")
    public WebElement spanRenaultMeganeIntensEdc;



    public CarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<Pair<String, String>> auctions = new ArrayList<>();

    public Pair<String, String> scrapeCar() throws InterruptedException {
        try {
            return Pair.of(driver.getCurrentUrl(), spanRenaultMeganeIntensEdc.getText());
        }
        catch(Exception e) {
            return Pair.of(driver.getCurrentUrl(), "error");
        }
    };

    public void showSpeed() {
        for (Pair<String, String> auction : auctions) {
            System.out.println("Adres URL: " + auction.getLeft());
            System.out.println("Nazwa: " + auction.getRight());
        }

    }

}