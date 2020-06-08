package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * In this class, PageFactory and @FindBy annotation is not used, By is used to locate elements
 */
public class AddUserPO extends TestBase {

    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By saveButton = By.name("FormsButton2");

    public AddUserPO(WebDriver driver) {
        super(driver);
    }

    public void navigateToAddAUser() {
        driver.get("http://thedemosite.co.uk/addauser.php");
    }

    public void enterUsername() throws Exception {
        driver.findElement(usernameField).sendKeys(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginUsername"));
    }

    public void enterPassword() throws Exception {
        driver.findElement(passwordField).sendKeys(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPassword"));
    }

    public void clickSave() {
        driver.findElement(saveButton).click();
    }
}