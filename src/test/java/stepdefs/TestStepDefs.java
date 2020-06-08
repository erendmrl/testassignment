package stepdefs;


import dataProvider.ConfigFileReader;
import dataProvider.JsonFileParser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opentest4j.AssertionFailedError;
import pageobjects.LoginPOTest;
import pageobjects.AddUserPO;
import restapitests.APITests;
import util.WriteToFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TestStepDefs {

    protected static final Logger logger = Logger.getLogger(TestStepDefs.class);

    private static WebDriver driver = null;
    private AddUserPO addUserPO;
    private LoginPOTest loginPagePO;
    ConfigFileReader configFileReader = new ConfigFileReader();
    JsonFileParser jsonFileParser = new JsonFileParser();
    WriteToFile writeToFile = new WriteToFile();
    APITests APITests = new APITests();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());


    @Before
    public void before(Scenario scenario) throws Exception {
        logger.warn("Test started..");

        //TODO: Move these to the TestBase class
        switch (configFileReader.getBrowser()) {
            case ("firefox"):
                System.setProperty("webdriver.gecko.driver", configFileReader.getDriverPathFirefox());
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
                break;
            case ("chrome"):
                System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);
                break;
        }

        addUserPO = new AddUserPO(driver);
        loginPagePO = new LoginPOTest(driver);
        addUserPO.navigateToAddAUser();
        addUserPO.enterUsername();
        addUserPO.enterPassword();
        addUserPO.clickSave();
        addUserPO.navigateToAddAUser();
        addUserPO.enterUsername();
        addUserPO.enterPassword();
        addUserPO.clickSave();
    }

    @After
    public void tearDown() {
        logger.warn("Test finished..");
        driver.close();
    }


    @When("I navigated to thedemosite login page")
    public void iNavigatedToThedemositeLoginPage() throws Exception {
        logger.info("Navigating to the login page..");
        loginPagePO.navigateToLoginPage();
    }

    @Then("the response HTTP status code should be {int}")
    public void theResponseHTTPStatusCodeShouldBe(int arg0) throws Exception {
        logger.info("Checking response HTTP code..");
        try {
            Assertions.assertEquals(200, loginPagePO.returnResponseHttpCode());
        } catch (AssertionFailedError e) {
            logger.error("Can not reach the login page: ", e);
            throw e;
        }

    }

    @And("the page title should be {string}")
    public void thePageTitleShouldBe(String arg0) throws Exception {
        try {
            logger.info("Checking page title..");
            Assertions.assertEquals(loginPagePO.retreiveTitle(), jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPageTitle"));
        } catch (AssertionFailedError e) {
            logger.error("Can not reach the login page: ", e);
            throw e;
        }
    }

    @And("the page title text should be {string}")
    public void thePageTitleTextShouldBe(String arg0) throws Exception {
        try {
            logger.info("Checking page title text..");
            Assertions.assertEquals(loginPagePO.retreivePageTitleText(), jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPageTitleText"));
        } catch (AssertionFailedError e) {
            logger.error("Can not reach login page: ", e);
            throw e;
        }
    }

    @And("the page info text should be correct")
    public void thePageInfoTextShouldBeCorrect() throws Exception {
        try {
            logger.info("Checking page info text..");
            Assertions.assertEquals(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPageInfoText"), loginPagePO.retreivePageInfoText());
        } catch (AssertionFailedError e) {
            logger.error("Page info text is not correct: ", e);
            throw e;
        }
    }

    @Then("I enter a proper username")
    public void iEnterAProperUsername() throws Exception {
        logger.info("Entering a proper username..");
        loginPagePO.enterUserName(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginUsername"));

    }

    @And("I enter a proper password")
    public void iEnterAProperPassword() throws Exception {
        logger.info("Entering a proper password..");
        loginPagePO.enterPassword(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPassword"));
    }

    @When("I click login")
    public void iClickLogin() throws Exception {
        logger.info("Clicking login..");
        loginPagePO.clickLogin();
    }

    @And("the page status text should be correct")
    public void thePageStatusTextShouldBeCorrect() throws Exception {
        try {
            logger.info("Checking page status text..");
            Assertions.assertEquals(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "loginPageStatusText"), loginPagePO.retreivePageStatusText());
        } catch (AssertionFailedError e) {
            logger.error("Page status text is not correct: ", e);
            throw e;
        }
    }

    @Then("the info message must be success")
    public void theInfoMessageMustBeSuccess() throws Exception {
        try {
            logger.info("Checking log in info message..");
            Assertions.assertEquals(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "succesfulLoginMessage"), loginPagePO.isSuccessText());
        } catch (AssertionFailedError e) {
            logger.error("Login failed: ", e);
            throw e;
        }
    }

    @But("when I enter a wrong username and password")
    public void whenIEnterAWrongUsernameOrPassword() throws Exception {
        logger.info("Entering a wrong username..");
        loginPagePO.enterUserName("dF/]%%ad#ZA");
        loginPagePO.enterPassword("ghjrjv");
    }


    @And("I check for username-password minimum boundary conditions, when I try to enter an invalid username {string} or password {string}")
    public void iCheckForUsernamePasswordMinimumBoundaryConditionsWhenITryToEnterAnInvalidUsernameOrPassword(String arg0, String arg1) throws Exception {
        loginPagePO.navigateToLoginPage();
        loginPagePO.enterUserName(arg0);
        loginPagePO.enterPassword(arg1);
        loginPagePO.clickLogin();

    }


    @When("I request to login with correct parameters via HTTP POST")
    public void iRequestToLoginWithCorrectParameters() {
        //both steps will be executed Then statement
    }

    @Then("Response xml should contain {string}")
    public void loginWithCorrectParametersResponseHtmlShouldContain(String arg0) {
        APITests.shouldReturnHttp200WhenSuccesfullLogin();
    }

    @Then("Response title should be {string}")
    public void responseTitleMustBe(String arg0) {
        APITests.shouldReturnTitleAsExpectedWhenSuccesfullLogin();
    }

    @Then("Response HTTP status code should be {int}")
    public void responseHTTPStatusCodeShouldBe(int arg0) {
        APITests.shouldReturnHttp200WhenSuccesfullLogin();
    }

    @When("I request to add user with correct parameters")
    public void iRequestToAddUserWithCorrectParameters() {
        //both steps will be executed Then statement
    }

    @Then("the response title should be correct and response should contain the entered username")
    public void theResponseTitleShouldBeCorrectAndResponseShouldContainTheEnteredUsername() {
        APITests.addUserShouldReturn200AndShouldContainUsername();
    }

    @Then("Response html should contain {string}")
    public void responseHtmlShouldContain(String arg0) {
        APITests.ShouldContainFailedWhenWrongParametersLogin();

    }

    @When("I request to login with incorrect parameters")
    public void iRequestToLoginWithIncorrectParameters() {
        //both steps will be executed Then statement
    }

    @Then("the info message must fail, I write the time to the file")
    public void theInfoMessageMustFailIWriteTheTimeToTheFile() throws Exception {
        try {
            logger.info("Checking log in info message..");
            Assertions.assertEquals(jsonFileParser.parseJsonFileAndReturnRequestedDAta("thedemosite.co.uk", "failedLoginMessage"), loginPagePO.isFailText());
            writeToFile.writeStr("Failed login at: " + String.valueOf(date));
        } catch (AssertionFailedError e) {
            logger.error("Login failed: ", e);
            throw e;
        }
    }

    @Then("alert window must appear, I write the time to the file")
    public void alertWindowMustAppearIWriteTheTimeToTheFile() {
        logger.info("Checking whether there will be an alert when invalid a username or password entered..");
        Boolean alertFlag = false;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
            logger.info("Alert was not present");
        } else {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            logger.info("Alert was present and accepted");
            alertFlag = true;
        }
        Assertions.assertTrue(alertFlag);
        writeToFile.writeStr("Failed login at: " + String.valueOf(date));
    }
}

