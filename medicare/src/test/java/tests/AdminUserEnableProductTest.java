package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.ManageProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdminUserEnableProductTest extends BaseTest {

    HomePage homePage;
    ManageProductPage manageProductPage;

    @DataProvider(name = "productnameDataProvider")
    public Object[][] productnameDataProvider(){
        return new Object[][]{
                {"vk@gmail.com", "admin", "Acetaminophen"}
        };
    }

    @Test(dataProvider = "productnameDataProvider")
    public void adminUserEnableProductTest(String username, String password, String product){

        manageProductPage = new ManageProductPage(driver);
        homePage = new HomePage(driver);

        userloginmethod(username, password);
        homePage.clickManageProduct();

        manageProductPage.setSearch(product);
        manageProductPage.clickActivatebutton();
        String activationmessage = manageProductPage.getactivationmessage();
        System.out.println("Activation message - " +activationmessage);
        if(activationmessage.equals("You want to activate the Product?")) {
            manageProductPage.clickactivatedeactivatebutton();
            String actualresult = manageProductPage.getactivationmessage2();
            System.out.println("Activation Message 2 -" + actualresult);
            manageProductPage.clickactivationOK();
            Assert.assertTrue(actualresult.contains("Product Activated Successfully"));

        }else{
            manageProductPage.clickactivatedeactivatecancelbutton();
            System.out.println("Product is already Enabled!!");
        }
    }
}
