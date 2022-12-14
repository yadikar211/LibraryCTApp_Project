package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.pages.BookPage;
import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US03 {

    BookPage bookPage = new BookPage();
    List<String> actualBookCategories;
    List<String> expectedBookCategories;

//    @Given("the {string} on the home page")
//    public void the_on_the_home_page(String userType) {
//        new LoginPage().login(userType);
//    }
//
//    @When("the user navigates to {string} page")
//    public void the_user_navigates_to_page(String module) {
//        bookPage.navigateModule(module);
//    }

    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        bookPage.mainCategoryElement.click();
        actualBookCategories = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCategories.remove(0);
    }

    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");
        expectedBookCategories = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(expectedBookCategories,actualBookCategories);
    }
}
