Feature: Confluence Login Test
Description: This feature will test Login functionality to Confluence

#This is how background can be used to eliminate duplicate steps
Background:
   Given I navigate to the Confluence login page

#Scenario 1
Scenario: Login success
   When I enter username as "MICHAERE"
   And I enter password as "NewYork2016"
   Then Login will be successful

#Scenario 2
Scenario: Login failure
   When I enter username as "John"
   And I enter password as "Doe"
   Then Login will fail
   But Relogin option will be available