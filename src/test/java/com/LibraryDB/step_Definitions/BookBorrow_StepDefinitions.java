package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.BasePage;
import com.LibraryDB.pages.BookBorrowPage;
import com.LibraryDB.pages.BooksPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.jsoup.Connection;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.swing.text.Utilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookBorrow_StepDefinitions {
    BooksPage booksPage = new BooksPage();
    BookBorrowPage bookBorrowPage = new BookBorrowPage();
    BasePage basePage ;

    @When("the user searches book name called {string}")
    public void the_user_searches_book_name_called(String string) {
    booksPage.search.sendKeys(string);
    }
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        BrowserUtil.waitFor(5);
        bookBorrowPage.borrowBookBtn.click();

    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String string) {

        BrowserUtil.waitFor(5);
        booksPage.navigateModule(string);

        Assert.assertTrue(BrowserUtil.getElementsText(bookBorrowPage.allBorrowedBooksName).contains("The Lion"));


    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() throws SQLException {
        BrowserUtil.waitFor(5);
        String query = "select full_name,b.name,bb.borrowed_date\n" +
                "from users u inner join book_borrow bb\n" +
                "    on u.id=bb.user_id inner join books b\n" +
                "        on bb.book_id=b.id where full_name='Test Student 5'\n" +
                "                             and b.name='The Lion' order by 3 desc;";


        ResultSet rs = DB_Util.runQuery(query);
        rs.next();
        String actualValue = rs.getString("name");

        String expectedValue = "The Lion";

        Assert.assertEquals(expectedValue,actualValue);



    }

}
