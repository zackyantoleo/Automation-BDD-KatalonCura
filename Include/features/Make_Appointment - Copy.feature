Feature: Make Appointment

  Scenario Outline: Make Appointment - Success
    Given User arleady logged in
    When User input <Facility>, <readmission>, <HealthcarePrograms>, <VisitDate> and <Comment>
    And User click Book Appointment button
    Then Verify user successfully book appointment with correct data

    Examples: 
      | Facility 											 	 | readmission | HealthcarePrograms | VisitDate  | Comment | 
      | Hongkong CURA Healthcare Center  | Yes         | None					      | 12/12/2012 | Test    | 
      
      
      
  Scenario Outline: Make Appointment - without input mandatory field
    Given User arleady logged in
    When User not input all the mandatory field
    And User click Book Appointment button
    Then Verify user failed to book appointment 