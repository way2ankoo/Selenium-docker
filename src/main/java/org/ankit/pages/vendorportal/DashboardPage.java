package org.ankit.pages.vendorportal;

import org.ankit.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id="monthly-earning")
    private WebElement monthlyEarningsElement;

    @FindBy(id="annual-earning")
    private WebElement annualEarningsElement;

    @FindBy(id="profit-margin")
    private WebElement profitMarginElement;

    @FindBy(id="available-inventory")
    private WebElement availableInventoryElement;

    @FindBy(css="#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id="dataTable_info")
    private WebElement searchResultsCountElement;

    @FindBy(css="img[class='img-profile rounded-circle']")
    private WebElement userProfilePictureElement;

    @FindBy(linkText="Logout")
    private WebElement logoutLink;

    @FindBy(css="#logoutModal a")
    private WebElement modalLogoutButton;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarningsElement));
        return this.monthlyEarningsElement.isDisplayed();
    }

    public String getMonthlyEarning(){
        return this.monthlyEarningsElement.getText();
    }

    public String getAnnualEarning(){
        return this.annualEarningsElement.getText();
    }

    public String getProfitMargin(){
        return this.profitMarginElement.getText();
    }

    public String getAvailableInventory(){
        return this.availableInventoryElement.getText();
    }

    public void searchOrderHistory(String keyword){
        this.searchInput.sendKeys(keyword);
    }

    public int getResultsCount(){
        String resultsText = this.searchResultsCountElement.getText();
        String[] arr = resultsText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }

    public void logout(){
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.modalLogoutButton));
        this.modalLogoutButton.click();
    }
}
