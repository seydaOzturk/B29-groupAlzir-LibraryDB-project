package com.LibraryDB.pages;

import com.LibraryDB.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BooksPage {

    public BooksPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//a[contains(@class, 'btn-sm add_book_btn')]")
    public WebElement addBooksButton;

    @FindBy(xpath = "//input[@placeholder='Book Name']")
    public WebElement bookNameInput;

    @FindBy(xpath = "//input[@placeholder='ISBN']")
    public WebElement isbnInput;

    @FindBy(xpath = "//input[@placeholder='Year']")
    public WebElement yearInput;

    @FindBy(xpath = "//input[@placeholder='Author']")
    public WebElement authorInput;

    @FindBy(id = "book_group_id")
    public WebElement bookCategory;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveChangesButton;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement bookIsSavedMessage;


}
