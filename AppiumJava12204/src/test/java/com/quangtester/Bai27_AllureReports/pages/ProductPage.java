package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

//Click nút "Config" ở dưới bottom tab => Click item "Product Management"
public class ProductPage extends BasePage{
    public ProductPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    //Nút "Add Product"
    @AndroidFindBy(accessibility = "Add Product")
    @iOSXCUITFindBy(accessibility = "Add Product")
    private WebElement buttonAddNewProduct;

    //Thanh Search
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    private WebElement inputSearchProduct;

    //Click nút "Add Product"
    public void addNewProduct() {
        System.out.println("Add new product");
        buttonAddNewProduct.click();
    }

}
