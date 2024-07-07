package org.ankit.pages.flightreservation;

import org.ankit.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {

    @FindBy(id="passengers")
    private WebElement passengerSelect;

    @FindBy(id="search-flights")
    private WebElement searchFlightsButton;

    public FlightSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return this.passengerSelect.isDisplayed();
    }

    public void selectPassenger(String noOfPassengers){
        Select passengerSelect = new Select(this.passengerSelect);
        passengerSelect.selectByValue(noOfPassengers);
    }

    public void searchFlight(){
        this.searchFlightsButton.click();
    }
}
