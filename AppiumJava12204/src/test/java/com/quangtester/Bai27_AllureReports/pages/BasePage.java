package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public BasePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    //Element/Locators thuộc chung cho nhiều trang

    //Nút "Date" ở dưới bottom tab
    @AndroidFindBy(accessibility = "Date")
    @iOSXCUITFindBy(accessibility = "Date")
    public WebElement menuDate;

    //Nút "Wallet" ở dưới bottom tab
    @AndroidFindBy(accessibility = "Wallet")
    @iOSXCUITFindBy(accessibility = "Wallet")
    public WebElement menuWallet;

    //Nút "Menu" ở dưới bottom tab
    @AndroidFindBy(accessibility = "Menu")
    @iOSXCUITFindBy(accessibility = "Menu")
    public WebElement menuMenu;

    //Nút "Profile" ở dưới bottom tab
    @AndroidFindBy(accessibility = "Profile")
    @iOSXCUITFindBy(accessibility = "Profile")
    public WebElement menuProfile;

    //Nút "Config" ở dưới bottom tab
    @AndroidFindBy(accessibility = "Config")
    @iOSXCUITFindBy(accessibility = "Config")
    public WebElement menuConfig;

    //Navigaiton Menu (Left menu)
    @AndroidFindBy(accessibility = "Open navigation menu")
    @iOSXCUITFindBy(accessibility = "Open navigation menu")
    public WebElement openNavigationLeftMenu;

    // NÚt Webview trên Left menu
    @AndroidFindBy(accessibility = "Web view")
    @iOSXCUITFindBy(accessibility = "Web view")
    public WebElement itemWebView;

    //Nút Back trên Left menu
    @AndroidFindBy(accessibility = "Back")
    @iOSXCUITFindBy(accessibility = "Back")
    public WebElement buttonBack;

    //Các hàm xử lý chung cho nhiều trang đều có
    public void clickMenuDate() {
        menuDate.click();
    }

    public void clickMenuWallet() {
        menuWallet.click();
    }

    public void clickMenuMenu() {
        menuMenu.click();
    }



    public void clickMenuConfig() {
        menuConfig.click();
    }

    public void clickOpenNavigationLeftMenu() {
        openNavigationLeftMenu.click();
    }

    public void clickItemWebView() {
        itemWebView.click();
    }

    public void clickButtonBack() {
        buttonBack.click();
    }
}
