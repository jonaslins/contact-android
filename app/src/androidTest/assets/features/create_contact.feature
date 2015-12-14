Feature: Create a contact
  As an app User
  I want to create a contact
  So I can store and lookup contactdetails later

  Scenario: The create contact button is invisible by default
    Given I have a MainActivity
    When the search bar is empty
    Then the create contact button is invisible

  Scenario: The create contact button becomes visible when typing
    Given I have a MainActivity
    When I search for "0634571085"
    Then the create contact button is visible

  Scenario: I can add a contact
    Given I have a MainActivity
    And I search for "0634571085"
    When I create a new contact
    And I enter the firstname "Timo"
    And I enter the lastname "Benkhard"
    And I save the contact
    Then the contact "Benkhard, Timo" is displayed in the list
