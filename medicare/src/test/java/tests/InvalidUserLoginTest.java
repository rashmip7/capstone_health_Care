package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class InvalidUserLoginTest extends BaseTest{

    HomePage homePage;
    LoginPage loginPage;

    @DataProvider(name = "invalidloginDataProvider")
    public Object[][] invalidloginDataProvider(){
        return new Object[][]{
                {"kn@gmail.com", "345"}
        };
    }

    @Test(dataProvider = "invalidloginDataProvider")
    public void invalidUserLoginTest(String username, String password){
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        System.out.println("Into the @Test");
        System.out.println(" Username - " +username + " Password - " +password);
        homePage.clickLogin();

        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        String actualresult = loginPage.getinvalidmessage();
        System.out.println(actualresult);
        Assert.assertTrue(actualresult.contains("invalid"));

    }
}
