package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ConfigPage extends BasePage {
    public ConfigPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    //Item Product Management
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Product management\")]")
    @iOSXCUITFindBy(xpath = "")
    private WebElement itemProductManagement;

    //Item Tables Management
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"Tables management\")]")
    @iOSXCUITFindBy(xpath = "")
    private WebElement itemTableManagement;

    //Item Server database
    @AndroidFindBy(accessibility = "Server database")
    @iOSXCUITFindBy(accessibility = "Server database")
    private WebElement itemServerDatabase;

    //Item Logout
    @AndroidFindBy(accessibility = "Logout")
    @iOSXCUITFindBy(accessibility = "Logout")
    private WebElement itemLogout;

    //Các hàm xử lý chung cho nhiều trang đều có

    //Click Item Product Management
    public ProductPage openProductManagement() {
        itemProductManagement.click();

        return new ProductPage();
    }

    //Click item Server Database
    public ServerPage openServerDatabase() {
        itemServerDatabase.click();

        return new ServerPage();
    }

    //Click item Table Management
    public TablePage openTableManagement() {
        itemTableManagement.click();

        return new TablePage();
    }

    //Click item Logout
    public LoginPage logout() {
        itemLogout.click();

        return new LoginPage();
    }

}
