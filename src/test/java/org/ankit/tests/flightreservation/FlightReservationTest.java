package org.ankit.tests.flightreservation;

import org.ankit.pages.flightreservation.*;
import org.ankit.tests.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goT0("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("delenium@docker.com", "docker");
        registrationPage.enterAddress("nirala green sector 2", "noida", "201307");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goToFlightSearchButton();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest(){
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassenger(noOfPassengers);
        flightSearchPage.searchFlight();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest(){
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();

    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
    }
}
