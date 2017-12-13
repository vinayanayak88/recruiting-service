Feature: User can successfully get offer

  Scenario: User gets an existing offer
   Given an offer exists
    When the user gets the created offer
    Then the user receives status code of 200
     And the retrieved offer is correct
    