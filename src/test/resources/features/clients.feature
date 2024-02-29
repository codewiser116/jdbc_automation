Feature: user should be able to create a client

  @dbTest1
  Scenario: verify client is created
    Given new client is created using API
    Then verify client exists in database
    And delete client in database
    Then verify client does not exist using API