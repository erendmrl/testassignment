package pageobjects;

import dataProvider.ConfigFileReader;
import dataProvider.JsonFileParser;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class TestBase {

    protected static final Logger logger = Logger.getLogger(TestBase.class);

    protected WebDriver driver;
    ConfigFileReader configFileReader = new ConfigFileReader();
    JsonFileParser jsonFileParser = new JsonFileParser();

    public TestBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
