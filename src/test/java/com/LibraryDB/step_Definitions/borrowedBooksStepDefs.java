package com.LibraryDB.step_Definitions;

import com.LibraryDB.pages.DashboardPage;
import com.LibraryDB.pages.LoginPage;
import com.LibraryDB.utility.BrowserUtil;
import com.LibraryDB.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class borrowedBooksStepDefs {
    // US02
    LoginPage loginPage=new LoginPage();
    DashboardPage dashBoardPage=new DashboardPage();

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {

        loginPage.login(user);
        BrowserUtil.waitFor(3);

    }

    String actualBorrowedBook;
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        actualBorrowedBook = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBook = " + actualBorrowedBook);

    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        String query="SELECT COUNT(*) FROM book_borrow\n" +
                "WHERE is_returned=0";

        DB_Util.runQuery(query);

        String expectedBorrowedBook = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBorrowedBook = " + expectedBorrowedBook);

        Assert.assertEquals(expectedBorrowedBook,actualBorrowedBook);
    }
}
