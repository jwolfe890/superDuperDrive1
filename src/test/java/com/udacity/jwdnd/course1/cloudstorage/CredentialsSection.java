package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsSection {

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTabField;

    @FindBy(css = "#add-credential")
    private WebElement addCredential;

    @FindBy(css = "#credential-url")
    private WebElement credentialUrlField;

    @FindBy(css = "#credential-username")
    private WebElement credentialUsernameField;

    @FindBy(css = "#credential-password")
    private WebElement credentialPasswordField;

//    @FindBy(css = ".note-title-displayed")
//    private WebElement displayedNoteTitle;

    @FindBy(id = "save-credential")
    private WebElement saveCredential;

    @FindBy(id = "edit-credential")
    private WebElement editCredential;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredential;

    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public CredentialsSection(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        wait = new WebDriverWait(webDriver, 1000);
        js = (JavascriptExecutor) webDriver;
    }

    public void openCredentialTab() {
        js.executeScript("arguments[0].click();", credentialsTabField);
    }

    public void openCredentialModal() {
        js.executeScript("arguments[0].click();", addCredential);
    }

    public void createCredential(String url, String username, String password) {
        js.executeScript("arguments[0].value='" + url + "';", credentialUrlField);
        js.executeScript("arguments[0].value='" + username + "';", credentialUsernameField);
        js.executeScript("arguments[0].value='" + password + "';", credentialPasswordField);
    }

    public void saveCredential() {
        js.executeScript("arguments[0].click();", saveCredential);
    }

    public void editCredential() {
        js.executeScript("arguments[0].click();", editCredential);
    }

    public void deleteCredential() {
        js.executeScript("arguments[0].click();", deleteCredential);
    }

}
