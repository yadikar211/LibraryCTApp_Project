package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.pages.Anaris_US6_Page;
import com.SkyBlue.LibraryCT.pages.BasePage;
import com.SkyBlue.LibraryCT.pages.BookPage;
import com.SkyBlue.LibraryCT.pages.LoginPage;
import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.ConfigurationReader;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class anaris_US6_StepDefs {

    BookPage bookPage = new BookPage();
    LoginPage loginPage = new LoginPage();

    Anaris_US6_Page anaris_us6_page = new Anaris_US6_Page();
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String username) {

        loginPage.login(username);
    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {

        bookPage.topModules(module);
    }
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        anaris_us6_page.addBooks.click();

    }
    String expected = "";
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        anaris_us6_page.bookName.sendKeys(bookName);
        expected += bookName;

    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        anaris_us6_page.isbnMsg.sendKeys(isbn);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        anaris_us6_page.yearMsg.sendKeys(year);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        anaris_us6_page.authorMsg.sendKeys(author);

    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategoryStr) {
        Select bookCategory = new Select(anaris_us6_page.bookCategory);
        bookCategory.selectByVisibleText(bookCategoryStr);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        anaris_us6_page.saveChangesBtn.click();
        BrowserUtil.waitFor(1);
        anaris_us6_page.searchBox.sendKeys(expected);
        BrowserUtil.waitFor(1);

    }
    @Then("verify {string} message is displayed")
    public void verifyMessageIsDisplayed(String toastMsg) {
        Assert.assertEquals(toastMsg,anaris_us6_page.toastMessage.getText());
    }


    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String bookNameFromReq) {

        String actualBookName = anaris_us6_page.verifyFileName(anaris_us6_page.verifyBookName, bookNameFromReq);
        System.out.println("From Frontend "+actualBookName);

        // get data from Database
        DB_Util.runQuery("select b.name,isbn,author from books b where b.name = '"+bookNameFromReq+"'");



        String expectedBookName = DB_Util.getFirstRowFirstColumn();
        System.out.println("From Backend "+expectedBookName);
        Assert.assertEquals(expectedBookName,actualBookName);

    }


}
