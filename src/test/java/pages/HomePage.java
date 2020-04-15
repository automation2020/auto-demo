package pages;

import helpers.DriverSetUp;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.components.SearchBar;

@DefaultUrl("https://www.olx.com.co/")
public class HomePage extends AbstractPage {

    @FindBy(xpath = "//*[@data-aut-id='homeImage']")
    private WebElement homeImage;

    public SearchBar searchBar;

    @Override
    public void openApplication() {
        this.setDriver(DriverSetUp.getWebDriver());
        open();
    }
}
