package com.simplilearn.tests;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.ManageProductPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdminUserNewCategoryTest extends BaseTest{

    HomePage homePage;
    ManageProductPage manageProductPage;

    @DataProvider(name = "categoryDataProvider")
    public Object[][] categoryDataProvider() {
        return new Object[][]{
            {"vk@gmail.com", "admin", "Pain Reliever", "Fever Reducer & Pain reliever"}
        };
    }

    @Test(dataProvider = "categoryDataProvider")
    public void AdminUserAddNewCategoryTest(String username, String password, String categoryname, String categorydescription) {

        homePage = new HomePage(driver);
        manageProductPage = new ManageProductPage(driver);

        userloginmethod(username,password);

        homePage.clickManageProduct();
        manageProductPage.clickAddNewCategory();
        manageProductPage.setCategoryName(categoryname);
        manageProductPage.setCategoryDescription(categorydescription);
        manageProductPage.clickSaveCategory();
        String categorymessage = manageProductPage.getCategoryAddedmessage();
        System.out.println(categorymessage);
        Assert.assertTrue(categorymessage.contains("Category submitted successfully"));
    }

}
