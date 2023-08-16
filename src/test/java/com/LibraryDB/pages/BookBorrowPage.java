package com.LibraryDB.pages;

import com.LibraryDB.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BookBorrowPage {


    public BookBorrowPage(){PageFactory.initElements(Driver.getDriver(),this);}


    @FindBy(xpath = "//td[text()='The Lion']//parent::tr//td//a")
    public WebElement borrowBookBtn;

    //it works for all books, dynamic
    @FindBy(xpath = "//*[@id=\"tbl_books\"]/tbody/tr/td[1]")
    public WebElement borrowBookButton;


    @FindBy(xpath = "//tr//td[2]")
    public List<WebElement> allBorrowedBooksName;

    @FindBy(xpath = "//td[text()='The Lion']//parent::tr//td[6]")
    public WebElement returnCell;

    @FindBy(xpath = "//*[@id=\"menu_item\"]/li[2]/a")
    public WebElement borrowingBooks;

    @FindBy(xpath = "//td[text()='The Lion']/parent::tr//td//a")
    public List<WebElement> returnBookBtns;





}
