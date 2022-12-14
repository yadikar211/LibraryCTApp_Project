package com.SkyBlue.LibraryCT.pages;

import com.SkyBlue.LibraryCT.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class US06 {

    public US06(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy (xpath = "//a[@href='#books']//span[.='Books']")
    public WebElement topModule;

    public void topModules(String module){
        String locator = "//a[@href='#books']//span[.='"+module+"']";
        Driver.getDriver().findElement(By.xpath(locator)).click();
    }

    @FindBy (css = "a[href='tpl/add-book.html']")
    public WebElement addBooks;

    @FindBy (xpath = "//div[@class='modal-body']//form[@id='add_book_form']//input[@placeholder='Book Name']") 
    public WebElement bookName;

    @FindBy (xpath = "//div[@class='modal-body']//form[@id='add_book_form']//input[@placeholder='ISBN']")
    public WebElement isbnMsg;

    @FindBy (xpath = "//div[@class='modal-body']//form[@id='add_book_form']//input[@placeholder='Year']")
    public WebElement yearMsg;

    @FindBy (xpath = "//div[@class='modal-body']//form[@id='add_book_form']//input[@placeholder='Author']")
    public WebElement authorMsg;

    @FindBy (xpath = "//select[@id='book_group_id']")
    public WebElement bookCategory;

    @FindBy (xpath = "//form[@id='add_book_form']//button[@type='submit']")
    public WebElement saveChangesBtn;

    @FindBy(css = " .toast-message")
    public WebElement toastMessage;

    @FindBy (xpath = "//div[@id='tbl_books_filter']//input[@type='search']")
    public WebElement searchBox;

    @FindBy (xpath = "//table[@id='tbl_books']/tbody/tr/td[3]")
    public List<WebElement> verifyBookName;

    public  String verifyFileName(List<WebElement> listOfElements, String expected){
        String actual = "";
        for (WebElement each : listOfElements){
            if(each.getText().equalsIgnoreCase(expected)){
                actual += "" + each.getText();
                break;
            }
        }
        return actual;
        //System.out.println(actual);
        //Assert.assertEquals(expected,actual);
    }

    public static String actualBookName(List<WebElement> listOfElements, String expected){
        String actual = "";
        for (WebElement each : listOfElements){
            if(each.getText().equalsIgnoreCase(expected)){
                actual += "" + each.getText();
                break;
            }
        }
        return actual;
        //System.out.println(actual);
        //Assert.assertEquals(expected,actual);
    }






}
