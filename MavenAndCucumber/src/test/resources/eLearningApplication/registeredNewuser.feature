Feature: Add Registeration service to e-Learning website

  Scenario: Register a new user to the website
    Given Home page is displayed and Sign up link is enable
    When Click on Sign up 
    And Enter user details in the registration form
    And click on Register button
    Then User is registered sucessfully
    And  User can access Inbox 
    And  Send a new mail

  
