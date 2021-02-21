Feature: Test purchase of laptop

  Scenario Outline: Add laptops to cart and place order
    Given user is on home page
    And user clicks on laptop category
    And user selects <laptop>
    And user adds the laptop to cart
    And user goes to cart
    And user deletes one laptop
    And user clicks on place order
    And user fills the purchase form
    When user clicks on submit button
    Then the order is placed

    Examples: 
      | laptop       |
      | Sony vaio i5 |
      | Dell i7 8gb  |
