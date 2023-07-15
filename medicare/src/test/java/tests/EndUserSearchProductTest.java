package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.ViewProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class EndUserSearchProductTest extends BaseTest {

    @DataProvider(name="searchDataProvider")
    public Object[][] searchDataProvider(){
        return new Object[][]{{"Paracetamol"}};
    }

    @Test(dataProvider = "searchDataProvider")
    public void endUserSearchProductTest(String productname){

        HomePage homePage = new HomePage(driver);;
        ViewProductsPage viewProductsPage = new ViewProductsPage(driver);;

        homePage.clickViewProducts();
        viewProductsPage.setSearch(productname);
        String expectedResult = viewProductsPage.getproductname();
        Assert.assertEquals(productname, expectedResult);
    }
}
