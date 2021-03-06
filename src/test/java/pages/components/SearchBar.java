package pages.components;

import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;

import static org.awaitility.Awaitility.await;

public class SearchBar extends AbstractPage {

    @FindBy(css = "form[role='search'] input")
    private WebElement searchInput;
    @FindBy(xpath = "//*[@data-aut-id='allCategories']")
    private WebElement allCategoriesMenu;
    @FindBy(xpath = "//*[@data-aut-id='btnSearch']//span")
    private WebElement searchButton;

    private final By searchInputLocator = By.xpath("//form[@role='search']//input");
    private final String categoryXpath = "//a[@class='_2fitb']//span[contains(text(),'%s')]";

    public void selectCategory(String category){
        waitForSearchBarToBeReady();
        wait.until(ExpectedConditions.visibilityOf(allCategoriesMenu));
        allCategoriesMenu.click();
        getDriver().findElement(By.xpath(String.format(categoryXpath,category))).click();
    }

    public void searchByKeyword(String keyword){
        waitForSearchBarToBeReady();
        searchInput.sendKeys(keyword);
        new Actions(getDriver()).click(searchButton).build().perform();
    }

    public void waitForSearchBarToBeReady(){
        await().atMost(Duration.ONE_MINUTE)
                .pollInterval(Duration.ONE_SECOND)
                .ignoreException(NoSuchElementException.class)
                .until(() -> getDriver().findElement(searchInputLocator)
                        .isDisplayed());
    }
}
