@Amazon
Feature: Amazon Product Search and Cart Functionality
  As a user
  I want to search for products on Amazon and add them to cart
  So that I can purchase the desired items

  Background:
    Given I am on the Amazon homepage

  @SmokeTest
  Scenario: Search for Samsung S24 Ultra 5G mobile and add to cart
    When I search for "samsung s24 ultra 5g mobile"
    Then I should see search results for Galaxy S24 Ultra 5G
    When I click on the first product from search results
    Then I should be on the product details page
    When I click on "Add to Cart" button
    Then I should see "Added to Cart" confirmation message
    And the product should be added to my cart

  @RegressionTest
  Scenario: Verify product search functionality
    When I search for "samsung s24 ultra 5g mobile"
    Then I should see search results containing "Samsung"
    And I should see search results containing "S24"
    And I should see search results containing "Ultra"

  @RegressionTest
  Scenario: Verify cart functionality
    When I search for "samsung s24 ultra 5g mobile"
    And I click on the first product from search results
    And I click on "Add to Cart" button
    When I navigate to cart
    Then I should see the added product in my cart
    And the product quantity should be "1"