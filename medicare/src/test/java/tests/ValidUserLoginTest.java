package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidUserLoginTest extends BaseTest{
    HomePage homePage;
    LoginPage loginPage;

    @DataProvider(name = "validloginDataProvider")
    public Object[][] validloginDataProvider(){
        return new Object[][]{
                {"kn@gmail.com", "12345", "Kavita Nigam"}
        };
    }

    @Test(dataProvider = "validloginDataProvider")
    public void validUserLoginTest(String username, String password, String expectedresult_name) {
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        System.out.println("Into the @Test");
        System.out.println(" Username - " +username + " Password - " +password);
        homePage.clickLogin();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        String actualresult = loginPage.getusername();
        System.out.println(actualresult);
        Assert.assertEquals(actualresult, expectedresult_name);
        loginPage.clickdropdown();
        loginPage.clicklogout();
    }



}
