package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

    @Test(groups= {"sanity","master"})
    public void verifyLogin() {
        logger.info("******** Starting TC_002_LoginTest ********");

        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on MyAccount link");
            homePage.clickLogin(); // Clicking the Login Link under MyAccount
            logger.info("Clicked on Login link");

            LoginPage loginPage = new LoginPage(driver);
            logger.info("Entering valid email and password...");
            loginPage.setEmail(p.getProperty("email"));
            loginPage.setPassword(p.getProperty("password"));
            loginPage.clickLogin(); // Clicking the Login button
            logger.info("Clicked on login button");

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean isMyAccountPageDisplayed = myAccountPage.isMyAccountPageDisplayed();

            Assert.assertTrue(isMyAccountPageDisplayed, "My Account page is not displayed"); // Asserting My Account page display

            logger.info("Login test passed");
        } catch (Exception e) {
            logger.error("An exception occurred: " + e.getMessage());
            Assert.fail("Login test failed");
        }

        logger.info("******** Finished TC_002_LoginTest ********");
    }
}
