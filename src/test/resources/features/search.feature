Feature: As a possible buyer
  I want to search and filter articles
  So I can find what I want to buy

  Scenario: search by category and a keyword and ordering results by price
    Given the user is in the olx page
    When the user searches for "Video juegos" category
    And the user searches for "Mario Bros" keyword
    And the user sorts results in ascendant price
    Then 5 first results should include the keyword "Mario Bros"
    And 5 first results should be in ascendant price order
