@US08
Feature: As a user I should be able to return the book
  @ui @db
  Scenario: Student return book
    Given the "student" on the home page
    And the user navigates to "Borrowing Books" page
    Then the user return "The Lion" book
    Then verify the book is returned to Library database

