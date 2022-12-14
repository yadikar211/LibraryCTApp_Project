package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.pages.BookPage;
import com.SkyBlue.LibraryCT.pages.LoginPage;
import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.util.List;

public class US07 {


    LoginPage loginPage = new LoginPage();
    BookPage bookPage = new BookPage();
    com.SkyBlue.LibraryCT.pages.US07 ka_us07_page = new com.SkyBlue.LibraryCT.pages.US07();

//    @Given("the {string} on the home page")
//    public void the_on_the_home_page(String username) {
//        loginPage.login(username);
//    }

//    @And("the user navigates to {string} page")
//    public void theUserNavigatesToPage(String arg0) {
//        bookPage.navigateModule(arg0);
//    }
    String bookNameFromReq;

    @And("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        bookPage.search.sendKeys(bookName + Keys.ENTER);
        bookNameFromReq = bookName;
    }
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        ka_us07_page.borrowBook(bookNameFromReq).click();

    }
    @Then("verify that book is shown in {string} page")
    public void verifyThatBookIsShownInPage(String arg0) {
        bookPage.navigateModule(arg0);
        Assert.assertTrue(BrowserUtil.getElementsText(ka_us07_page.allBorrowedBooksName).contains(bookNameFromReq));
    }

    @And("verify logged student has same book in database")
    public void verifyLoggedStudentHasSameBookInDatabase() {
        String query = "select b.name from books b join book_borrow bb on" +
                " b.id = bb.book_id join users u on bb.user_id = u.id where b.name = '"+bookNameFromReq+"'";
        DB_Util.runQuery(query);

        List<String> actualListFromDb = DB_Util.getColumnDataAsList(1);
        Assert.assertTrue(actualListFromDb.contains(bookNameFromReq));



    }

}


