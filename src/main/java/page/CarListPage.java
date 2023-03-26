package page;

import main.PUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public boolean scrapePage() throws InterruptedException {

        List<Pair<String, String>> auctions = new ArrayList<>();

        List<WebElement> carlist = listaSamochodów.findElements(By.tagName("article"));

        for (WebElement singlecar : carlist) {
            // Utworzenie obiektu klasy Actions
            Actions actions = new Actions(driver);

            // Naciśnięcie klawisza Ctrl lub Command i kliknięcie na link
            actions.keyDown(Keys.CONTROL).click(singlecar).perform();

            // Pobranie listy dostępnych okien
            Set<String> windowHandles = driver.getWindowHandles();

            // Przełączenie się na ostatnie okno
            String oldWindowHandle = windowHandles.toArray()[windowHandles.size() - 2].toString();
            String newWindowHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
            driver.switchTo().window(newWindowHandle);

            // Wyświetlenie aktualnego tytułu i adresu URL
            System.out.println("Aktualny tytuł: " + driver.getTitle());
            System.out.println("Aktualny adres URL: " + driver.getCurrentUrl());

            CarPage car = new CarPage(driver);
            auctions.add(Pair.of(car.scrapeCar()));
            driver.close();
            driver.switchTo().window(oldWindowHandle);
        }

        return true;
    };
}