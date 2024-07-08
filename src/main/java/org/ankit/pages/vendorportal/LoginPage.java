package org.ankit.pages.vendorportal;

import org.ankit.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(id="username")
    private WebElement usernameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(id="login")
    private WebElement loginButton;

    public void goTo(String url){
        this.driver.get(url);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.loginButton));
        return this.loginButton.isDisplayed();
    }

    public void enterUserCredentials(String username, String password){
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
    }

    public void login(){
        this.loginButton.click();
    }
}
