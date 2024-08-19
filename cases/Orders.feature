Feature: Order Management

  Scenario: Admin views all orders
    Given the admin is logged in
    When the admin views all orders
    Then the orders should be displayed

  Scenario: Customer creates a new order
    Given the customer has selected products
    And the products are available in stock
    When the customer creates a new order
    Then the order should be saved with status "pending"

  Scenario: Customer views their pending order
    Given the customer has a pending order
    When the customer views their orders
    Then the pending order should be displayed

  Scenario: Admin updates an order status
    Given the admin is logged in
    And the order exists
    When the admin updates the order status to "shipped"
    Then the order status should be updated

  Scenario: Customer cancels their order
    Given the customer has a pending order
    When the customer cancels the order
    Then the order should be removed from the system