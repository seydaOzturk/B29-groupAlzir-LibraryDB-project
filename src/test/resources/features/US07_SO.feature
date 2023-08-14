@seyda @ui @db
Feature: Books module
  As a students, I should be able to borrow book

  Scenario: Student borrow new book
    Given the "student" on the home page_SO
    And the user navigates to "Books" page_SO
    And the user searches for "ABCDEF" book_SO
    When the user clicks Borrow Book_SO
    Then verify that book is shown in "Borrowing Books" page_SO
    And  verify logged student has same book in database_SO