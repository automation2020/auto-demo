package steps;

import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.ResultsPage;

public class SearchSteps {

    private HomePage homePage;
    private ResultsPage resultsPage;

    @Given("the user is in the olx page")
    public void theUserIsInTheOlxPage() {
        homePage.openApplication();
    }

    @When("^the user searches for \"([^\"]*)\" category$")
    public void theUserSearchesForCategory(String category) {
        homePage.searchBar.openApplication();
        homePage.searchBar.selectCategory(category);
    }

}
