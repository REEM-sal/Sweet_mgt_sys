Feature: User Roles

  Scenario: The Admin can generate financial reports
    Given The admin is logged into the system
    When The admin enters "1"
    Then The admin should be able to view and generate financial reports 

  Scenario: The Admin can manage contents
    Given The admin is logged into the system
    When The admin enters "2"
    Then The Admin should be able to create, edit, or delete contents

  Scenario: The Admin can manage recipes
    Given The admin is logged into the system
    When The admin enters "3"
    Then The Admin should be able to create, edit, or delete recipes

  Scenario: The Admin can gather and display statistics on registered users by city
    Given The admin is logged into the system
    When The admin enters "4"
    Then The admin should be able to view statistics on registered users categorized by city 
 
  Scenario: The Admin enters a number not found
    Given The admin is logged into the system
    When The admin enters "6"
    Then The Admin should be told to enter one, two, three, or four

 Scenario: The Beneficiary User can browse sweets
    Given The Beneficiary User is logged into the system
    When The Beneficiary User enters "1"
    Then The Beneficiary User should be able to browse sweets

  Scenario: The Beneficiary User can make purchases
    Given The Beneficiary User is logged into the system
    When The Beneficiary User enters "2"
    Then The Beneficiary User should be able to make purchases

 Scenario: The Beneficiary User can manage view orders
    Given The Beneficiary User is logged into the system
    When The Beneficiary User enters "3"
    Then The Beneficiary User should be able to view orders

 Scenario: The Beneficiary User enters a number not found
    Given The Beneficiary User is logged into the system
    When The Beneficiary User enters "6"
    Then The Beneficiary User should be told to enter one, two, or three

 Scenario: The Owner can manage orders
    Given The Owner is logged into the system
    When The Owner selects "1"
    Then The Owner should be able to add, update, or cancel orders
    
 Scenario: The Owner can send email notifications
    Given The Owner is logged into the system
    When The Owner selects "2"
    Then The Owner should be able to send email notifications for shipping, delivery, and order cancellation
    
 Scenario: The Owner can view pending orders
    Given The Owner is logged into the system
    When The Owner selects "3"
    Then The Owner should be able to view all pending orders

 Scenario: The Owner can calculate the total cost of orders
    Given The Owner is logged into the system
    When The Owner selects "4"
    Then The Owner should be able to calculate the total cost of a specific order

  Scenario: The Owner enters an invalid selection
    Given The Owner is logged into the system
    When The Owner selects "5"
    Then The Owner should be prompted to enter a valid option (1, 2, 3, or 4)