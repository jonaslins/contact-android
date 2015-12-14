Feature: View a contact
  As an app User
  I want to view a contact
  So I can view the details of the selected contact

  Scenario: View an existing contact
    Given I have a MainActivity
    When I click on the contact "Test, Tester"
    Then I see the contacts details
