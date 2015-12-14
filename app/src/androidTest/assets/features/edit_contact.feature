Feature: Edit a contact
  As an app User
  I want to edit a contact
  So I can remove or change mistakes that I made, or update a contact with new details

  Scenario: A context menu is shown when holding a contact
    Given I have a MainActivity
    When I longpress contact "Test, Tester"
    Then the actions context menu is displayed for "Test, Tester"

  Scenario: I can cancel deleting a contact
    Given I have a MainActivity
    And the contact "Test, Tester" is displayed in the list
    When I longpress contact "Test, Tester"
    And I delete the contact
    Then a confirmation window is displayed

  Scenario: I can edit a contact
    Given I have a MainActivity
    When I longpress contact "Test, Tester"
    And I edit the contact
    Then I see the update contact screen

  Scenario: I can change a contacts name
    Given I have a MainActivity
    And a contact with named "Hendrak", "Piet"
    When I longpress contact "Hendrak, Piet"
    And I edit the contact
    And I enter the firstname "Pieter"
    And I enter the lastname "Hendriks"
    And I save the contact
    Then the contact "Hendriks, Pieter" is displayed in the list
    And the contact "Hendrak, Piet" is not displayed in the list

  Scenario: I can delete a contact
    Given I have a MainActivity
    And the contact "Test, Tester" is displayed in the list
    When I longpress contact "Test, Tester"
    And I delete the contact
    And I confirm the delete action
    Then the contact "Test, Tester" is not displayed in the list

