package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.BookBorrowPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReturnBook_StepDefinitions {
    BookBorrowPage bookBorrowPage = new BookBorrowPage();
    @Then("the user return {string} book")
    public void the_user_return_book(String string) {
        BrowserUtil.waitFor(5);
        int n = bookBorrowPage.returnBookBtns.size();
        bookBorrowPage.returnBookBtns.get(n-1).click();

    }

    @Then("verify the book is returned to Library database")
    public void verify_the_book_is_returned_to_library_database() throws SQLException {
        BrowserUtil.waitFor(5);
        String query = "select name,is_returned from books\n" +
                "inner join book_borrow on books.id=book_borrow.book_id\n" +
                "where name='The Lion';";
        ResultSet rs = DB_Util.runQuery(query);
        //ResultSetMetaData resultSetMetaData = rs.getMetaData();
        rs.last();
        String actual = rs.getString(2);
        String expected = "1";

        Assert.assertEquals(expected,actual);



    }
}
