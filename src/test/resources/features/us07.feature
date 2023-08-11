@US07
Feature: Books module
  US: As a students, I should be able to borrow book.

  Scenario: Student borrow new book
    Given the "student" on the home page
    And the user navigates to "Books" page
    When the user searches book name called "Head First Java"
    And the user clicks Borrow Book
    Then verify that book is shown in "Borrowing Books" page
    And verify logged student has same book in database
