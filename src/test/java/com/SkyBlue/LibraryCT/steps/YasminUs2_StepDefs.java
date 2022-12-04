package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.pages.DashBoardPage;
import com.SkyBlue.LibraryCT.pages.LoginPage;
import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class YasminUs2_StepDefs {

    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String actualBookNum;

    @When("librarian gets borrowed books number.")
    public void librarianGetsBorrowedBooksNumber() {
        actualBookNum = dashBoardPage.borrowedBooksNumber.getText();

    }

    @Then("the borrowed books number information must match with DB.")
    public void theBorrowedBooksNumberInformationMustMatchWithDB() {

        DB_Util.runQuery("select count(*) as borrowedBooks from users u  \n" +
                "inner join book_borrow b on u.id = b.user_id where is_returned = 0");

        String expectedBookNum=DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBookNum, actualBookNum);
    }




}
