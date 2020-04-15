package pages;

import org.awaitility.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SearchBar;

import java.util.List;

import static org.awaitility.Awaitility.await;

public class ResultsPage extends AbstractPage {

    @FindBy(xpath = "//*[@class='rui-26lOJ _3N02I ']//*[@class='_3Da4N']")
    private WebElement sortOptionButton;
    @FindBy(id = "adsResultsIn")
    private WebElement totalResultsNumber;
    @FindBy(xpath = "//*[@data-aut-id='itemsList']//li[@class='EIR5N']")
    private List<WebElement> resultCards;

    private final By sortOptionButtonLocator = By.xpath("//*[@class='rui-26lOJ _3N02I ']//*[@class='_3Da4N']");
    private final By cardTitleLocator = By.xpath(".//*[@data-aut-id='itemTitle']");
    private final By cardPriceLocator = By.xpath(".//*[@data-aut-id='itemPrice']");
    private final String sortOptionXpath = "//*[@class='rui-26lOJ _3N02I ']//span[contains(text(),'%s')]";

    public SearchBar searchBar;

    public void sortByAscendantPrice(){
        sortBy("Precio: más bajo");
    }

    protected void waitForPageToBeReady() {
        await().atMost(Duration.TWO_MINUTES)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> {
                    try{
                        return getDriver().findElement(sortOptionButtonLocator).isDisplayed();
                    } catch (NoSuchElementException e){
                        return false;
                    }
                });
    }

    public String getResultTitleByIndex(int index){
        waitForSearchToBeCompleted();
        return resultCards.get(index).findElement(cardTitleLocator).getText();
    }

    public int getResultPriceByIndex(int index){
        return Integer.parseInt(resultCards.get(index)
                .findElement(cardPriceLocator)
                .getText().split(" ")[1].replace(".",""));
    }

    private void waitForSearchToBeCompleted(){
        await().atMost(Duration.TEN_SECONDS)
                .pollInterval(Duration.ONE_SECOND)
                .until(() -> Integer
                        .parseInt(totalResultsNumber.getText().split(" ")[0]
                                .replace(".",""))>0);
    }

    private void sortBy(String sortOption){
        waitForPageToBeReady();
        sortOptionButton.click();
        getDriver().findElement(By.xpath(String.format(sortOptionXpath,sortOption))).click();
    }
}
