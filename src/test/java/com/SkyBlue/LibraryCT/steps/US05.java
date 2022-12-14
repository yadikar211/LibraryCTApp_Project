package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.utility.BrowserUtil;
import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05 {
    String actualGenre;




    @When("I execute query to find most popular book genre.")
    public void iExecuteQueryToFindMostPopularBookGenre() {
        BrowserUtil.waitFor(4);
        String query = "select bc.name,count(*) borrowed from book_categories bc inner join books b on bc.id = b.book_category_id\n" +
                "inner join book_borrow bb on b.id = bb.book_id group by name order by borrowed desc";
        DB_Util.runQuery(query);
        actualGenre = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualGenre);

        BrowserUtil.waitFor(4);
    }

    @Then("verify {string} is the most popular book genre")
    public void verifyIsTheMostPopularBookGenre(String expectedGenre) {
        Assert.assertEquals(expectedGenre, actualGenre);
    }
}

