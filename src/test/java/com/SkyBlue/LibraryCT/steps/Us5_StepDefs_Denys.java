package com.SkyBlue.LibraryCT.steps;

import com.SkyBlue.LibraryCT.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Us5_StepDefs_Denys extends DB_Util {
    private ResultSet rs = null;

    @Given("Establish the database connection")
    public void establishTheDatabaseConnection() {
        createConnection();
    }

    @When("I execute query to find most popular book genre")
    public void iExecuteQueryToFindMostPopularBookGenre() {
        String query = "select bc.name,count(*) from book_borrow bb " +
                "inner  join books b on bb.book_id = b.id " +
                "inner join book_categories bc on b.book_category_id=bc.id " +
                "group by name order by 2 desc;";
        rs = runQuery(query);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verifyIsTheMostPopularBookGenre(String genre) {
        int targetRowCount = 0;
        List<Integer> counts = new ArrayList<>();

        try {
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(genre)) {
                    targetRowCount = Integer.parseInt(rs.getString("count(*)"));
                } else {
                    counts.add(Integer.parseInt(rs.getString("count(*)")));
                }
            }
        } catch (Exception e) {
            System.out.println("We caught exception " + e.getMessage());
        }
        findMax(genre, targetRowCount, counts);
    }

    private void findMax(String genre, int assumedMaxCount, List<Integer> nums) {
        for (int each : nums) {
            Assert.assertTrue("Target Genre: '" + genre + "' is not the most popular!!!!", assumedMaxCount > each);
        }
    }
}
