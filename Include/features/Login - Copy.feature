Feature: Login

  Scenario Outline: Login with valid data
    Given User Navigated to the page
    When User Input valid <username> and <password>
    And User click login button
    Then Verify user successfully login

    Examples: 
      | username 			 | password 								| 
      | John Doe       | ThisIsNotAPassword       | 
      
      
      
  Scenario Outline: Login with invalid data
    Given User Navigated to the page	
    When User Input invalid <username> and <password>
    And User click login button
    Then Verify user failed to login and error message showed

    Examples: 
      | username | password | 
      | 27       | 20       | 