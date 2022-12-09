Feature: As a data consumer, I want UI and DB book information are match.
  @run@db
  Scenario: Verify book information with DB
    Given I login as a librarian
    And I navigate to "Books" page
    When I open book "Chordeiles minor"
    Then book information must match the Database
Feature: As a data consumer, I want UI and DB book information are match.

  Scenario: Verify book information with DB
    Given the "librarian" on the home page
    And the user navigates to "Books" page
    When the user searches for "Clean Code" book
    And  the user clicks edit book button
    Then book information must match the Database