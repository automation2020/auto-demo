package pages;

import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchBar extends AbstractPage {

    @FindBy(xpath = "//form[@role='search']//input")
    private WebElement searchInput;
    @FindBy(xpath = "//*[@data-aut-id='allCategories']")
    private WebElement allCategoriesMenu;
    @FindBy(xpath = "//*[@data-aut-id='btnSearch']")
    private WebElement searchButton;

    private String categoryXpath = "//a[@class='_2fitb']//span[contains(text(),'%s')]";

    public void selectCategory(String category){
        allCategoriesMenu.click();
        getDriver().findElement(By.xpath(String.format(categoryXpath,category))).click();
    }

    public void searchByKeyword(String keyword){
        searchInput.sendKeys(keyword);
        searchButton.click();
    }

    @WhenPageOpens
    protected void waitForComponentToBeReady() {
        element(searchButton).waitUntilVisible();
    }
}
