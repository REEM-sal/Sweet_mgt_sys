package najah.edu.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import najah.edu.Order;
import najah.edu.Owner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Orders {
    Owner owner = new Owner();
    Order order = new Order();

    @Given("customer view available products")
    public void customerViewAvailableProducts() {
    	   owner.displayAvailableProducts();
    }

    @Given("The Customer Name is {string}")
    public void theCustomerNameIs(String customerName) {
        order.setCustomerName(customerName);
    }

    @Given("The Customer Id is {string}")
    public void theCustomerIdIs(String customerId) {
        order.setIdCustomer(customerId);
    }

    @When("customer enter exist productsId to order {string}")
    public void customerEnterExistProductsIdToOrder(String productId) {
        assertTrue(order.checkProductExistence(productId));
        order.addProductToOrder();
    }

    @When("the quantities is allowed {string}")
    public void theQuantitiesIsAllowed(String quantity) {
        assertTrue(order.editProductQuantity());
    }

    @Then("the products should ordered")
    public void theProductsShouldOrdered() {
        assertTrue(order.isIfCustomerShowPendingOrder());
    }

    @Then("the order status is {string}")
    public void theOrderStatusIs(String status) {
        assertEquals(status, order.getStatusOrder());
    }

    @When("the quantities not allowed {string}")
    public void theQuantitiesNotAllowed(String quantity) {
        assertTrue(!order.editProductQuantity());
    }

    @Then("the customer should enter allowed quantity")
    public void theCustomerShouldEnterAllowedQuantity() {
        // Logic to ask customer to enter a valid quantity
    }

    @When("customer enter not exist product id to order {string}")
    public void customerEnterNotExistProductIdToOrder(String productId) {
        assertTrue(!order.checkProductExistence(productId));
    }

    @Then("the customer should add exist product")
    public void theCustomerShouldAddExistProduct() {
        // Logic to notify customer to add an existing product
    }

    @Given("customer view {string} orders")
    public void customerViewOrders(String status) {
        order.viewPendingOrder(status, order.getIdCustomer());
    }

    @When("customer enter exist order {string}")
    public void customerEnterExistOrder(String orderId) {
        order.searchAboutCustomer(order.getFileOrderName(), Long.parseLong(orderId));
        assertTrue(order.isIfOrderExist());
    }

    @Then("customer can cancel order")
    public void customerCanCancelOrder() {
        order.deleteOrder(order.getFileOrderName());
        assertTrue(order.isIfOrderExist());
    }

    @Then("customer can't cancel order")
    public void customerCanTCancelOrder() {
        assertTrue(!order.isIfOrderExist());
    }

    @When("customer enter exist  productId {string}")
    public void customerEnterExistProductId(String productId) {
        assertTrue(order.checkProductExistence(productId));
    }

    @Then("customer can edit Quantity To The Product")
    public void customerCanEditQuantityToTheProduct() {
        assertTrue(order.editProductQuantity());
    }

    @Then("customer can't edit  Quantity")
    public void customerCanTEditQuantity() {
        assertTrue(!order.editProductQuantity());
    }

    @Then("customer can't edit The Quantity of productId {int}")
    public void customerCanTEditTheQuantityOfProductId(Integer productId) {
        assertTrue(!order.editSpecificProductQuantity(productId));
    }

    @When("The Owner selects {string}")
    public void theOwnerSelects(String option) {
        assertTrue(order.makeOwnerSelection(option));
    }

    @Then("The Owner should be able to add, update, or cancel orders")
    public void theOwnerShouldBeAbleToAddUpdateOrCancelOrders() {
        assertTrue(order.performOwnerOperations());
    }

    @Then("The Owner should be able to send email notifications for shipping, delivery, and order cancellation")
    public void theOwnerShouldBeAbleToSendEmailNotifications() {
        assertTrue(order.sendEmailNotifications());
    }

    @Then("The Owner should be able to view all pending orders")
    public void theOwnerShouldBeAbleToViewAllPendingOrders() {
        order.viewPendingOrder("pending", order.getIdCustomer());
        assertTrue(order.isIfCustomerShowPendingOrder());
    }

    @Then("The Owner should be able to calculate the total cost of a specific order")
    public void theOwnerShouldBeAbleToCalculateTheTotalCostOfASpecificOrder() {
        double totalCost = order.calculateTotalCost();
        assertTrue(totalCost > 0);
    }

    @Then("The Owner should be prompted to enter a valid option \\({int}, {int}, {int}, or {int})")
    public void theOwnerShouldBePromptedToEnterAValidOptionOr(Integer option1, Integer option2, Integer option3, Integer option4) {
        assertTrue(order.validateOption(option1, option2, option3, option4));
    }
}
