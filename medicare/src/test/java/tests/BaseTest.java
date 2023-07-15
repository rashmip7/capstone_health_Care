package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import com.simplilearn.reports.ExtentTestManager;
import com.simplilearn.utils.ConfigurationFile;
import com.simplilearn.utils.SeleniumDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    String homePageUrl = ConfigurationFile.getProperty("HOME_PAGE_URL");

    @BeforeClass
    @Parameters({"browser"})
    public void startBrowser(String browser){
        driver = new SeleniumDriverManager().getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get(homePageUrl);
    }

    @AfterClass
    public void teardownBrowser(){
        driver.quit();
    }

    @BeforeSuite
    public void beforeSuite(){
        String projectpath = System.getProperty("user.dir");
        ExtentTestManager.setExtentReports(projectpath+"/target/reports/extent-report.html");
    }

    public void userloginmethod(String username, String password){
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        homePage.clickMedicare();
        homePage.clickLogin();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }
}
