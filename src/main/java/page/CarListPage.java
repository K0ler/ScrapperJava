package page;

import main.PUtils;
import main.SQLUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static main.Main.driver;

// page_url = https://www.otomoto.pl/
public class CarListPage {
    public CarListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    PUtils pf = new PUtils();
    @FindBy(xpath = "//main[@data-testid='search-results']")
    public WebElement listaSamochodów;

    private List<Pair<String, String>> auctions = new ArrayList<>();

    public boolean scrapePage() throws InterruptedException, SQLException {


        List<WebElement> carlist = listaSamochodów.findElements(By.tagName("article"));
        int i = 1;
        for (WebElement singlecar : carlist) {
            Actions actions = new Actions(driver);

            actions.keyDown(Keys.CONTROL).click(singlecar).perform();

            Set<String> windowHandles = driver.getWindowHandles();

            String oldWindowHandle = windowHandles.toArray()[windowHandles.size() - 2].toString();
            String newWindowHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
            driver.switchTo().window(newWindowHandle);

            System.out.println("Aktualny tytuł: " + driver.getTitle());
            System.out.println("Aktualny adres URL: " + driver.getCurrentUrl());

            CarPage car = new CarPage(driver);
            auctions.add(Pair.of(car.scrapeCar()));
            i++;
            driver.close();
            driver.switchTo().window(oldWindowHandle);
            //if(i == 3) break;
        }
        sendResultToSQL();
        return true;
    };

    public boolean sendResultToSQL() throws SQLException {

        SQLUtils sql = new SQLUtils();
        sql.connectToSQL();
        for (Pair<String, String> auction : auctions) {
            sql.sendToSQL("INSERT INTO otomoto (url, title) values ('" + auction.getLeft() + "', '" + auction.getRight() + "')");
        }
        return true;
    };

}