package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.ManageProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdminUserEditProductDetailsTest extends BaseTest {

    HomePage homePage;
    ManageProductPage manageProductPage;

    @DataProvider(name = "productPriceQuantityDataProvider")
    public Object[][] productPriceQuantityDataProvider(){
        return new Object[][]{
                {"vk@gmail.com", "admin", "Acetaminophen", "20", "15"}
                };
    }

    @Test(dataProvider = "productPriceQuantityDataProvider")
    public void adminUserEditProductDetailsTest(String username, String password, String product, String unitprice, String quantity){
        manageProductPage = new ManageProductPage(driver);
        homePage = new HomePage(driver);

        userloginmethod(username, password);

        homePage.clickManageProduct();

        manageProductPage.setSearch(product);
        manageProductPage.clickEditbutton();
        manageProductPage.setUnitprice(unitprice);
        manageProductPage.setQuantity(quantity);
        manageProductPage.clickSave();
        String message = manageProductPage.getAlertMessage();
        Assert.assertTrue(message.contains("Product submitted successfully"));
    }
}
