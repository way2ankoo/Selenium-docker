package org.ankit.tests.vendorportal;

import org.ankit.pages.vendorportal.DashboardPage;
import org.ankit.pages.vendorportal.LoginPage;
import org.ankit.tests.AbstractTest;
import org.ankit.tests.vendorportal.model.VendorPortalTestData;
import org.ankit.utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void initializePages(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");

        Assert.assertTrue(loginPage.isAt());

        loginPage.enterUserCredentials(testData.username(), testData.password());
        loginPage.login();
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        // assert financials
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarnings());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarnings());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        //search history
        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        this.dashboardPage.logout();
        Assert.assertTrue(this.loginPage.isAt());
    }

}
