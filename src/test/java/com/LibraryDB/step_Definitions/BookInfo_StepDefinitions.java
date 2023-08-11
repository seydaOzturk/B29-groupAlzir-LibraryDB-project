package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.BasePage;
import com.LibraryDB.pages.BooksPage;
import com.LibraryDB.pages.LoginPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class BookInfo_StepDefinitions {
    BooksPage bookPage = new BooksPage();

    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        bookPage.search.sendKeys(bookName);
    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        BrowserUtil.waitFor(3);
        String bookName = "ABCDEF";
        bookPage.editBook(bookName).click();

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        BrowserUtil.waitFor(3);
        String actualBookName = bookPage.bookNameInput.getAttribute("value");
        String actualAuthor = bookPage.authorInput.getAttribute("value");
        String actualIsbn = bookPage.isbnInput.getAttribute("value");
        String actualYear = bookPage.yearInput.getAttribute("value");

        String query = "select name,author,isbn,year from books\n" +
                "where name ='ABCDEF'";
        DB_Util.runQuery(query);
        Map<String, String> bookInfo = DB_Util.getRowMap(1);
        String expectedBookName = bookInfo.get("name");
        String expectedAuthor  = bookInfo.get("author");
        String expectedIsbn = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");

        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedAuthor, actualAuthor);
        Assert.assertEquals(expectedIsbn, actualIsbn);
        Assert.assertEquals(expectedYear,actualYear);

    }

}
