package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.BooksPage;
import com.LibraryDB.pages.LoginPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import com.LibraryDB.utility.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class US_06_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    BooksPage booksPage = new BooksPage();

    String expectedBookName;
    String expectedISBN;
    String expectedBookYear;
    String expectedAuthorName;
    String expectedBookCategory;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {

        loginPage.login(userType);
    }

    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String booksModule) {
        loginPage.navigateModule(booksModule);
    }

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        BrowserUtil.waitFor(2);
        booksPage.addBooksButton.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        BrowserUtil.waitFor(2);
        booksPage.bookNameInput.sendKeys(bookName);
        expectedBookName = bookName;
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        BrowserUtil.waitFor(2);
        booksPage.isbnInput.sendKeys(isbn);
        expectedISBN = isbn;
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        BrowserUtil.waitFor(2);
        booksPage.yearInput.sendKeys(year);
        expectedBookYear = year;
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        BrowserUtil.waitFor(2);
        booksPage.authorInput.sendKeys(author);
        expectedAuthorName = author;
        BrowserUtil.waitFor(2);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategory) {
        Select select = new Select(booksPage.bookCategory);
        for (WebElement categories : select.getOptions()) {
            if (categories.getText().equals(bookCategory)) {
                categories.click();
            }
        }
        expectedBookCategory = bookCategory;
        BrowserUtil.waitFor(2);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        booksPage.saveChangesButton.click();
    }

    @Then("verify “The book has been created\" message is displayed")
    public void verify_the_book_has_been_created_message_is_displayed() {
        BrowserUtil.waitFor(2);
        Assert.assertTrue(booksPage.bookIsSavedMessage.isDisplayed());
    }


    @And("verify {string} information must match with DB")
    public void verifyInformationMustMatchWithDB(String bookName) {
        String query = "select name,author from books where name='"+bookName+"' and author='"+expectedAuthorName+"' order by id desc";

        DB_Util.runQuery(query);
        String actualAuthorName = DB_Util.getCellValue(1, 2);
        String actualBookName = DB_Util.getFirstRowFirstColumn();

        System.out.println("actualAuthorName = " + actualAuthorName);
        System.out.println("actualBookName = " + actualBookName);

        Assert.assertEquals(expectedBookName, actualBookName);
        Assert.assertEquals(expectedAuthorName,actualAuthorName);



    }



}





