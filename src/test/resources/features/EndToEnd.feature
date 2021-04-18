@wip
Feature: End To End Test

  Scenario: Admin able to add new spartan, get, put, patch, delete and verify

    Given user add new spartan status code 201
    Then user get the added spartan and status code is 200
    Then user update the added spartan 204
    And user get and verify the update was done status code 200