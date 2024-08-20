package najah.edu.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import najah.edu.Admin;
import najah.edu.BeneficiaryUser;
import najah.edu.Owner;

public class Roles {
	 private Owner owner;
	    private BeneficiaryUser beneficiaryUser = new BeneficiaryUser();
    private static final Logger logger = Logger.getLogger(Roles.class.getName());
    private Admin admin = new Admin();
   
    @Before
    public void setUp() {
        owner = new Owner();
        logger.setLevel(Level.INFO);
    }

    @Given("The admin is logged into the system")
    public void theAdminIsLoggedIntoTheSystem() {
    	 
    }

    @When("The admin enters {string}")
    public void theAdminEnters(String string) {
    	
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
   	
        
    }

    @When("The Beneficiary User enters {string}")
    public void theBeneficiaryUserEnters(String choice) {
      
    }

    @Then("The Beneficiary User should be able to browse sweets")
    public void theBeneficiaryUserShouldBeAbleToBrowseSweets() {
    	
    }
	 @When("The Beneficiary User provides feedback")
    public void theBeneficiaryUserProvidesFeedback() {
        
    }

    @Then("The Beneficiary User feedback flag should be set")
    public void theBeneficiaryUserFeedbackFlagShouldBeSet() {
       
    }
	
	 @Then("The Beneficiary User should be able can manage personal account")
    public void theBeneficiaryUserShouldBeAbleCanManagePersonalAccount() {
    
    }

    @Then("The Beneficiary User should be able to make purchases")
    public void theBeneficiaryUserShouldBeAbleToMakePurchases() {
    	
    	 
    }

    @Then("The Beneficiary User should be told to enter one, two, or three")
    public void theBeneficiaryUserShouldBeToldToEnterOneTwoOrThree() {
    	 
      
    }
	 @Then("The Beneficiary User address should be updated to {string}")
    public void theBeneficiaryUserAddressShouldBeUpdatedTo(String expectedAddress) {
     
    }
	@Given("The Owner is logged into the system")
	public void theOwnerIsLoggedIntoTheSystem() {
		  owner.login("Yara@gmail.com", "121"); 
		    assertTrue("Owner should be logged in", owner.isOwnerLogin());
	}

	@When("The Owner enters {string}")
    public void theOwnerEnters(String string) {
		owner.whatAdminEnter(string);
       
    }

    @Then("The Owner should be able to add, edit, or delete products")
    public void theOwnerShouldBeAbleToAddEditOrDeleteProducts() {
    	  assertTrue(owner.isProductsFlag());
       
    }

    @Then("The Owner should be able to monitor sales")
    public void theOwnerShouldBeAbleToMonitorSales() {
    	 owner.setMonitorSalesFlag(true); 
    	 assertTrue(owner.isMonitorSalesFlag());
    	    owner.monitorSales();
    }


    @Then("The Owner should be able to apply discounts")
    public void theOwnerShouldBeAbleToApplyDiscounts() {
        assertTrue(owner.isDynamicDiscountFlag());
    }

   

    @Then("The Owner should be able to send email notifications for shipping, delivery")
    public void theOwnerShouldBeAbleToSendEmailNotificationsForShippingDelivery() {
    	 owner.setNotificationsFlag(true);
    	    assertTrue(owner.isNotificationsFlag());

    	    owner.receiveNotifications("shipped");
    	    
    	    List<String> emailMessages = owner.getEmailMessages();
    	    assertTrue(emailMessages.contains("The order status has changed to shipped."));
      	    owner.receiveNotifications("delivered");
    	    assertTrue(emailMessages.contains("The order has been delivered."));  
    }

    @Then("The Owner should be able to identifiy best selling")
    public void theOwnerShouldBeAbleToIdentifiyBestSelling() {
    	 assertFalse(owner.isBestSellingProductsFlag());
         owner.identifyBestSellingProducts();
         assertTrue(owner.isBestSellingProductsFlag());
       
    }

   
}
