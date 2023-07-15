package com.simplilearn.tests;

import com.simplilearn.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class AdminUserAddNewProductTest extends BaseTest {

    HomePage homePage;
    ManageProductPage manageProductPage;
    LoginPage loginPage;


    @DataProvider(name = "productdetailsDataProvider")
    public Object[][] productdetailsDataProvider(){
        return new Object[][]{
                {"vk@gmail.com", "admin", "Acetaminophen", "Tylenol", "Pain Reliever", "10", "10",
                        "C:\\Data\\SoftwareDevelopmentProjects\\intellij_workspace\\Simplilearn-Capstone-HealthCare-Project\\images\\tylenol.png", "Pain Reliever"},
                {"vk@gmail.com", "admin","Ibuprofen", "Ibuprofen", "Pain Reliever", "20", "10",
                        "C:\\Data\\SoftwareDevelopmentProjects\\intellij_workspace\\Simplilearn-Capstone-HealthCare-Project\\images\\ibuprofen.png", "Pain Reliever"}
        };
    }

    @Test(dataProvider = "productdetailsDataProvider")
    public void adminUserAddNewProductTest(String useremail, String password, String product, String brand, String description, String unitprice,
                                           String quantity, String filepath, String category){

        homePage = new HomePage(driver);
        manageProductPage = new ManageProductPage(driver);
        loginPage = new LoginPage(driver);

        userloginmethod(useremail,password);

        homePage.clickManageProduct();
        manageProductPage.setProductName(product);
        manageProductPage.setBrandname(brand);
        manageProductPage.setBranddescription(description);
        manageProductPage.setUnitprice(unitprice);
        manageProductPage.setQuantity(quantity);
        manageProductPage.setUploadfile(filepath);
        manageProductPage.setCategory(category);
        manageProductPage.clickSave();
        String message = manageProductPage.getAlertMessage();  //not working
        System.out.println("Message--> " +message);
        Assert.assertTrue(message.contains("Product submitted successfully"));
        loginPage.clickdropdown();
        loginPage.clicklogout();
    }

}
