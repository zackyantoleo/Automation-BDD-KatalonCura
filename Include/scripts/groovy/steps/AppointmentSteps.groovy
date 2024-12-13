package steps
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class AppointmentSteps {
	String Facility
	String readmission
	String HealthcarePrograms
	String VisitDate
	String Comment


	@Given("User arleady logged in")
	def Userarleadyloggedin() {
		WebUI.callTestCase(findTestCase('Login/TC_1.2'), [('expectedUrl') : ''], FailureHandling.STOP_ON_FAILURE)
	}


	@When("User input (.*), (.*), (.*), (.*) and (.*)")
	def UserinputFacilityreadmissionHealthcareProgramsVisitDateandComment(String Facility, String readmission, String HealthcarePrograms, String VisitDate, String Comment) {
		this.Facility = Facility
		this.readmission = readmission
		this.HealthcarePrograms = HealthcarePrograms
		this.VisitDate = VisitDate
		this.Comment = Comment

		WebUI.selectOptionByValue(findTestObject('Page_CURA Healthcare Service/Dropdown_Facility'), Facility, false)

		if (readmission == 'Yes') {
			WebUI.click(findTestObject('Page_CURA Healthcare Service/Checkbox_Readmission'))
		}

		WebUI.click(findTestObject('Page_CURA Healthcare Service/Radio_Healthcare_Programs', [('program') : HealthcarePrograms]))

		WebUI.setText(findTestObject('Page_CURA Healthcare Service/Input_Visit Date'), VisitDate)

		WebUI.setText(findTestObject('Page_CURA Healthcare Service/textarea_Comment'), Comment)
	}

	@When("User not input all the mandatory field")
	def Usernotinptallthemandatoryfield() {
		WebUI.selectOptionByValue(findTestObject('Page_CURA Healthcare Service/Dropdown_Facility'), Facility, false)

		if (readmission == 'Yes') {
			WebUI.click(findTestObject('Page_CURA Healthcare Service/Checkbox_Readmission'))
		}

		WebUI.click(findTestObject('Page_CURA Healthcare Service/Radio_Healthcare_Programs', [('program') : HealthcarePrograms]))

		WebUI.setText(findTestObject('Page_CURA Healthcare Service/textarea_Comment'), Comment)
	}

	@And("User click Book Appointment button")
	def UserclickBookAppointmentbutton() {
		WebUI.click(findTestObject('Page_CURA Healthcare Service/button_Book Appointment'))
	}

	@Then("Verify user successfully book appointment with correct data")
	def Verifyusersuccessfullybookappointmentwithcorrectdata() {
		WebUI.verifyElementText(findTestObject('Page_CURA Healthcare Service/Verification_Hongkong CURA Healthcare Center'), Facility)

		WebUI.verifyElementText(findTestObject('Page_CURA Healthcare Service/Verify_Readmission'), readmission)

		WebUI.verifyElementText(findTestObject('Page_CURA Healthcare Service/Verify_Medicare'), HealthcarePrograms)

		WebUI.verifyElementText(findTestObject('Page_CURA Healthcare Service/Verify_Date'), VisitDate)

		WebUI.verifyElementText(findTestObject('Page_CURA Healthcare Service/Verify_Comment'), Comment)
	}

	@Then("Verify user failed to book appointment")
	def Verifyuserfailedtobookappointment() {
		GlobalVariable.CurrentUrl = WebUI.getUrl()

		WebUI.verifyEqual(GlobalVariable.CurrentUrl, 'https://katalon-demo-cura.herokuapp.com/#appointment')
	}
}