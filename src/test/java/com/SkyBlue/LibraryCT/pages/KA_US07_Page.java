package com.SkyBlue.LibraryCT.pages;

import com.SkyBlue.LibraryCT.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class KA_US07_Page extends BasePage {


//    @FindBy(xpath = "//tbody//td[2]")
//    public List<WebElement> allBorrowedBooksName;

public WebElement borrowBook(String book) {
    String xpath = "//td[3][.='" + book + "']/../td/a";
    return Driver.getDriver().findElement(By.xpath(xpath));
}

    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

}

