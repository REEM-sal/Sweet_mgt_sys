package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import najah.edu.Admin;
import najah.edu.BeneficiaryUser;
import najah.edu.Owner;

public class Roles {
	 private Owner owner;
	    private BeneficiaryUser beneficiaryUser = new BeneficiaryUser();
    private static final Logger logger = Logger.getLogger(Roles.class.getName());
    private Admin admin = new Admin();
   
    
    private final String reportFilePath = "src/main/resources/myData/financial_report.html";

   
    @Given("The admin is logged into the system")
    public void theAdminIsLoggedIntoTheSystem() {
    	 assertEquals(true, admin.isAdminLogin());
    }

    @When("The admin enters {string}")
    public void theAdminEnters(String menuOption) {
    }

    @Then("The admin should be able to view and generate financial reports")
    public void theAdminShouldBeAbleToViewAndGenerateFinancialReports() {
    }

    @Then("The Admin should be able to create, edit, or delete contents")
    public void theAdminShouldBeAbleToCreateEditOrDeleteContents() {
    }

    @Then("The Admin should be able to create, edit, or delete recipes")
    public void theAdminShouldBeAbleToCreateEditOrDeleteRecipes() {
    }

    @Then("The admin should be able to view statistics on registered users categorized by city")
    public void theAdminShouldBeAbleToViewStatisticsOnRegisteredUsersCategorizedByCity() {
    }

    @Then("The Admin should be told to enter one, two, three, or four")
    public void theAdminShouldBeToldToEnterOneTwoThreeOrFour() {
    }




    @Given("The Beneficiary User is logged into the system")
    public void theBeneficiaryUserIsLoggedIntoTheSystem() {
        // Prepare the BeneficiaryUser object if necessary.
        
    }

    @When("The Beneficiary User enters {string}")
    public void theBeneficiaryUserEnters(String choice) {
        // Simulate the user's menu choice.
        beneficiaryUser.theBeneficiaryUserEnter(choice);
    }

    @Then("The Beneficiary User should be able to browse sweets")
    public void theBeneficiaryUserShouldBeAbleToBrowseSweets() {
        assertTrue(beneficiaryUser.isBrowseProductsFlag());
        // Further assertions can be made to check if the browsing functionality is working as expected.
    
    }

    @Then("The Beneficiary User should be able to make purchases")
    public void theBeneficiaryUserShouldBeAbleToMakePurchases() {
        assertTrue(beneficiaryUser.isMakePurchasesFlag());
       
    }

    @Then("The Beneficiary User should be able to view orders")
    public void theBeneficiaryUserShouldBeAbleToViewOrders() {
        assertTrue(beneficiaryUser.isViewOrdersFlag());
       
    }

    @Then("The Beneficiary User should be told to enter one, two, or three")
    public void theBeneficiaryUserShouldBeToldToEnterOneTwoOrThree() {
      
    }
	@Given("The Owner is logged into the system")
	public void theOwnerIsLoggedIntoTheSystem() {
		
	}

	@When("The Owner enters {string}")
    public void theOwnerEnters(String choice) {
       
    }

    @Then("The Owner should be able to add, edit, or delete products")
    public void theOwnerShouldBeAbleToAddEditOrDeleteProducts() {
       
    }

    @Then("The Owner should be able to monitor sales")
    public void theOwnerShouldBeAbleToMonitorSales() {
       
    }

    @Then("The Owner should be able to identify the best-selling products")
    public void theOwnerShouldBeAbleToIdentifyTheBestSellingProducts() {
        assertTrue(owner.isBestSellingProductsFlag());
    }

    @Then("The Owner should be able to apply discounts")
    public void theOwnerShouldBeAbleToApplyDiscounts() {
        assertTrue(owner.isDynamicDiscountFlag());
    }

    @Then("The Owner should be told to enter one, two, three, or four")
    public void theOwnerShouldBeToldToEnterOneTwoThreeOrFour() {
        // This is a part of the ownerMenu method
        // Ensure the menu is displayed correctly in the simulation
        // You may need to verify the console output or interactions
    }
}
