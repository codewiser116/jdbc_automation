Feature: product price

  @priceVerification
  Scenario: verify product prices are within range of 1 to 10000
    Given I set up connection to database
    And I retrieve all product prices
    Then verify prices are between 1 and 10000

@productCreation
    Scenario: create product using API and verify in DB
      Given use RequestBody and create new product "/api/myaccount/products"
      Then verify product is in database
      When get same product by id and validate data
      Then delete same product by id
      And verify product is not in database
