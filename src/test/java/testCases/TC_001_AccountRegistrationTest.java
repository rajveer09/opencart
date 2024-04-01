package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{
	
	// Test classes
	
	@Test(groups= {"regression","master"})
	public void verify_account_registration()
	{
		logger.info("Starting ******** TC_001_AccountRegistrationTest ********");
		
		logger.debug("application logs started....");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on registration link");
		
		
		logger.info("Entering customer details");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
		
		
		String password=randomAlphaNumeric();
		
		regpage.setPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		logger.info("clicked on continue");
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("Validating expected messege");
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
		
		catch(Exception e) {
			logger.error("test failed..");
			Assert.fail();
		}
		
		logger.debug("application logs finished....");
		logger.info("Finished ******** TC_001_AccountRegistrationTest ********");
		
	}
	
	
	
	
}








