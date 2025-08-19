package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TablePage {
    public TablePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    //Nút "Add Table"
    @AndroidFindBy(accessibility = "Add Table")
    @iOSXCUITFindBy(accessibility = "Add Table")
    private WebElement buttonAddNewTable;

    //Thanh Search
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    private WebElement inputSearchTable;

    //Click nút "Add Table"
    public void addNewTable() {
        System.out.println("Add new table");
        buttonAddNewTable.click();
    }
}
