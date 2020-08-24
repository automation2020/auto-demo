package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.hamcrest.CoreMatchers;
import pages.HomePage;
import pages.ResultsPage;
import static org.junit.Assert.assertThat;

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
        resultsPage.openApplication();
    }

    @And("^the user searches for \"([^\"]*)\" keyword$")
    public void theUserSearchesForKeyword(String keyword) {
        resultsPage.searchBar.openApplication();
        resultsPage.searchBar.searchByKeyword(keyword);
    }

    @And("^the user sorts results in ascendant price$")
    public void theUserSortResultsInAscendantPrice() {
        resultsPage.sortByAscendantPrice();
    }

    @Then("^(\\d+) first results should include the keyword \"([^\"]*)\"$")
    public void firstResultsShouldIncludeTheKeyword(int resultsToAssert, String keyword) {
        SoftAssertions softly = new SoftAssertions();
        for(int i=0;i<resultsToAssert;i++){
            softly.assertThat(resultsPage.getResultTitleByIndex(i).toLowerCase()
                    .contains(keyword.toLowerCase()))
                    .as(String.format("Result: \"%s\" should include \"%s\" in the title",
                            resultsPage.getResultTitleByIndex(i).toLowerCase(),
                            keyword))
                    .isEqualTo(true);
        }
        softly.assertAll();
    }

    @And("^(\\d+) first results should be in ascendant price order$")
    public void firstResultsShouldBeInAscendantPriceOrder(int resultsToAssert) {
        for(int i=0;i<resultsToAssert-1;i++){
            assertThat("Results should be in ascendant price",
                    resultsPage.getResultPriceByIndex(i)<=resultsPage.getResultPriceByIndex(i+1),
                    CoreMatchers.is(true));
        }
    }
}
