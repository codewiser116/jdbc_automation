@regression
Feature: Retrieve Sellers Information

  #//https://backend.cashwise.us/api/myaccount/sellers/all

  @sellersAll @api
  Scenario: get list of Sellers, print their names, validate they are not Null

    Given a path "/api/myaccount/sellers/all"
    When get their names, print and validate they are not Null