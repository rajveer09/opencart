package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void verifyLoginDDT(String email, String password, String exp) {
        logger.info("**** Starting TC_003_LoginDDT *****");

        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clickLogin();

            LoginPage lp = new LoginPage(driver);
            lp.setEmail(email);
            lp.setPassword(password);
            lp.clickLogin();

            MyAccountPage macc = new MyAccountPage(driver);
            boolean targetPage = macc.isMyAccountPageDisplayed();

            if (exp.equalsIgnoreCase("Valid")) {
                if (targetPage) {
                    macc.clickLogout();
                    Assert.assertTrue(true, "Login successful as expected.");
                } else {
                    Assert.fail("Login failed unexpectedly.");
                }
            } else if (exp.equalsIgnoreCase("Invalid")) {
                if (targetPage) {
                    Assert.fail("Login successful unexpectedly.");
                } else {
                    Assert.assertTrue(true, "Login failed as expected.");
                }
            } else {
                Assert.fail("Invalid expected result provided.");
            }
        } catch (Exception e) {
            logger.error("An exception occurred: " + e.getMessage());
            Assert.fail("An exception occurred: " + e.getMessage());
        }

        logger.info("**** Finished TC_003_LoginDDT *****");
    }
}
