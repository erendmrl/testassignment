package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ReturnHTPPResponseCode;


/**
 * In this class, PageFactory (initialized inside TestBase class) and @FindBy annotation is used
 */
public class LoginPOTest extends TestBase {


    @FindBy(xpath = "//strong[contains(.,'4. Login')]")
    private WebElement loginPageTitleText;

    @FindBy(xpath = "//b[contains(.,'**No login attempted**')]")
    private WebElement loginPageStatusText;

    @FindBy(xpath = "(//p[@align='center'])[6]")
    private WebElement loginPageInfoText;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "FormsButton2")
    private WebElement submitButton;

    @FindBy(xpath = "//b[contains(.,'**Successful Login**')]")
    private WebElement successText;

    @FindBy(xpath = "//b[contains(.,'**Failed Login**')]")
    private WebElement failText;

    public LoginPOTest(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() throws Exception {
        driver.get(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginUrl"));
    }

    public int returnResponseHttpCode() throws Exception {
        ReturnHTPPResponseCode returnHTPPResponseCode = new ReturnHTPPResponseCode();
        return returnHTPPResponseCode.checkLink(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginUrl"));

    }

    public String retreiveTitle() {
        return driver.getTitle();
    }

    public String retreivePageTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginPageTitleText));
        return loginPageTitleText.getText();
    }

    public String retreivePageStatusText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginPageStatusText));
        return loginPageStatusText.getText();
    }

    public String retreivePageInfoText() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(loginPageInfoText));
        return loginPageInfoText.getText();
    }

    public void enterUserName(String username) throws Exception {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) throws Exception {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        submitButton.click();
    }

    public String isSuccessText(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(successText));
        return successText.getText();
    }

    public String isFailText(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(failText));
        return failText.getText();
    }
}