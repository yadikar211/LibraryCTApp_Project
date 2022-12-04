@us02
Feature: As a librarian, I want to know borrowed books number


  @us2Yasmin @db
  Scenario: verify the total amount of borrowed books
    Given the "librarian" on the home page
    When  librarian gets borrowed books number.
    Then the borrowed books number information must match with DB.