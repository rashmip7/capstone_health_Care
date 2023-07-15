package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class EndUserRegisterTest extends BaseTest {

    @DataProvider(name = "signUpDataProvider")
    public Object[][] signUpDataProvider(){
        return new Object[][]{
                {"Adam", "Smith", "as@gmail.com", "2323232323", "1234", "USER",
                         "Apt 1234", "Beach St", "Los Angeles",
                "123445", "California", "USA"}
        };
    }

    @Test(dataProvider = "signUpDataProvider")
    public void endUserRegisterTest(String firstname, String lastname, String email, String contact, String password, String role,
                                    String addressline1, String addressline2, String city, String postalcode, String state, String country){
        HomePage homePage = new HomePage(driver);;
        SignupPage signupPage = new SignupPage(driver);

        homePage.clickSignup();
        signupPage.setFirstname(firstname);
        signupPage.setLastname(lastname);
        signupPage.setEmail(email);
        signupPage.setContactnumber(contact);
        signupPage.setPassword(password);
        signupPage.setConfirmpassword(password);
        signupPage.setrole(role);
        signupPage.clicknextbillingbutton();
        signupPage.setAddresslineone(addressline1);
        signupPage.setAddresslinetwo(addressline2);
        signupPage.setCity(city);
        signupPage.setPostalcode(postalcode);
        signupPage.setState(state);
        signupPage.setCountry(country);
        signupPage.clickNextconfirmbutton();
        signupPage.clickconfirmbutton();

        String actualresult = signupPage.getWelcomemesage();
        System.out.println(actualresult);
        Assert.assertTrue(actualresult.contains("Welcome"));

    }

}
