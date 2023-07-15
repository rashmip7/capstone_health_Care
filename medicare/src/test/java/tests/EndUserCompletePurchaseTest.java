package com.simplilearn.tests;

import com.simplilearn.pages.CheckoutPage;
import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.ViewProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class EndUserCompletePurchaseTest extends BaseTest {

    HomePage homePage;
    ViewProductsPage viewProductsPage;
    CheckoutPage checkoutPage;

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider(){
        return new Object[][]{
                {"as@gmail.com", "1234", "Acetaminophen"}
        };
    }

    @DataProvider(name = "paymentdetailsDataProvider")
    public Object[][] paymentdetailsDataProvider(){
        return new Object[][]{
                {"12345678956743", "10", "23", "123"}
        };
    }

    @Test(dataProvider = "loginDataProvider")
    public void endUserAddProductToCartTest(String username, String password, String product){

        viewProductsPage = new ViewProductsPage(driver);
        homePage = new HomePage(driver);

        userloginmethod(username,password);

        homePage.clickViewProducts();
        viewProductsPage.setSearch(product);
        viewProductsPage.clickaddproducttocart();

    }

    @Test(dependsOnMethods = "endUserAddProductToCartTest", dataProvider = "paymentdetailsDataProvider")
    public void endUserCompletePurchaseTest(String creditcardnumber, String expirymonth, String expiryyear, String cvvcode){

        checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickcheckoutbutton();
        checkoutPage.clickselectaddressbutton();
        checkoutPage.setCardnumber(creditcardnumber);
        checkoutPage.setExpiryMonth(expirymonth);
        checkoutPage.setexpiryyear(expiryyear);
        checkoutPage.setcvvcode(cvvcode);
        checkoutPage.clickpaybutton();
        String expectedResult = checkoutPage.getorderconfirmation();
        Assert.assertTrue(expectedResult.contains("Confirmed"));
    }

}
