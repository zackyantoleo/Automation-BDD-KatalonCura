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


class LoginSteps {


	@Given("User Navigated to the page")
	def UserNavigatedtothe_page() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')
		WebUI.click(findTestObject('Page_CURA Healthcare Service/Button_Make Appointment'))
	}


	@When("User Input valid (.*) and (.*)")
	def UserInputvalidusername(String username, String password) {
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username_username'), username)
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_password'), password)
	}

	@When("User Input invalid (.*) and (.*)")
	def UserInputinvalidusername(String username, String password) {
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Username_username'), username)
		WebUI.setText(findTestObject('Page_CURA Healthcare Service/input_Password_password'), password)
	}

	@And("User click login button")
	def Userclickloginbutton() {
		WebUI.click(findTestObject('Page_CURA Healthcare Service/button_Login'))
	}

	@Then("Verify user successfully login")
	def Verifyusersuccessfullylogin() {

		String currentUrl = WebUI.getUrl()

		WebUI.verifyEqual(currentUrl, 'https://katalon-demo-cura.herokuapp.com/#appointment')
	}

	@Then("Verify user failed to login and error message showed")
	def Verifyuserfailedtologin() {

		String currentUrl = WebUI.getUrl()

		WebUI.verifyEqual(currentUrl, 'https://katalon-demo-cura.herokuapp.com/profile.php#login')

		WebUI.verifyElementPresent(findTestObject('Page_CURA Healthcare Service/Message_Login failed Please ensure the username and password are valid'),
				0)
	}
}