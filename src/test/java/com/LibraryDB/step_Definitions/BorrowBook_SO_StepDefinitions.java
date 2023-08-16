package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.BooksPage;
import com.LibraryDB.pages.BorrowedBooksPage;
import com.LibraryDB.pages.LoginPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BorrowBook_SO_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();

    @Given("the {string} on the home page_SO")
    public void the_on_the_home_page_so(String userType) {

        loginPage.login(userType);
    }
    @Given("the user navigates to {string} page_SO")
    public void the_user_navigates_to_page_so(String module) {
        BrowserUtil.waitFor(3);
        loginPage.navigateModule(module);
    }
    @Given("the user searches for {string} book_SO")
    public void the_user_searches_for_book_so(String bookName) {
       booksPage.search.sendKeys(bookName);

    }
    @When("the user clicks Borrow Book_SO")
    public void the_user_clicks_borrow_book_so() {
        BrowserUtil.waitFor(5);
      borrowedBooksPage.borrowBookButton.click();
    }
    @Then("verify that book is shown in {string} page_SO")
    public void verify_that_book_is_shown_in_page_so(String module) {
        BrowserUtil.waitFor(3);
        borrowedBooksPage.navigateModule(module);
        Assert.assertTrue(BrowserUtil.getElementsText(borrowedBooksPage.allBorrowedBooksName).contains("ABCDEF"));
    }
    @Then("verify logged student has same book in database_SO")
    public void verify_logged_student_has_same_book_in_database_so() throws SQLException {
        BrowserUtil.waitFor(5);
        String query = "select full_name,b.name,bb.borrowed_date\n" +
                "from users u inner join book_borrow bb\n" +
                "    on u.id=bb.user_id inner join books b\n" +
                "        on bb.book_id=b.id where full_name='Test Student 5'\n" +
                "                             and b.name='ABCDEF' order by 3 desc;";


        ResultSet rs = DB_Util.runQuery(query);
        rs.next();
        String actualValue = rs.getString("name");

        String expectedValue = "ABCDEF";

        Assert.assertEquals(expectedValue,actualValue);
    }
}
