
  Feature: Test Sellers

# /api/myaccount/sellers   ?  isArchived=true  &page=1   &size=10
    @seller
    Scenario: get all sellers and validate phone numbers
      Given get all sellers with api path "/api/myaccount/sellers" and parameters: isArchived "false" , page 1, size 10
      Then  user gets all phone numbers and validate is not null



    @homework
    Scenario: create seller, get id and then hit api and validate
      Given I create all variables in class level, create seller and get seller_id
      Then I get same seller by id and validate

