package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.pages.DashBoardPage;
import com.SkyBlue.LibraryCT.pages.LoginPage;
import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.hc.core5.util.Asserts;
import org.junit.Assert;

public class Yadikar_StepDefs {

    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String actualBookNum;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String string) {
        BrowserUtil.waitFor(1);
    loginPage.login(string);

    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        actualBookNum = dashBoardPage.borrowedBooksNumber.getText();
    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) as borrowedBooks from users u  \n" +
                "inner join book_borrow b on u.id = b.user_id where is_returned = 0");

        String expectedBookNum=DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBookNum, actualBookNum);


    }



}
