package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.AndroidDriverManager;
import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
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

    //Click Nút permission
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    @iOSXCUITFindBy(accessibility = "clickPermission")
    public WebElement clickPermission;

    //Click button "Đăng nhập"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Đăng nhập\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonSignIn")
    public WebElement clickButtonSignIn;

    //Click icon "X" tắt popup Đăng nhập = cách khác
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Thoát\"]")
    @iOSXCUITFindBy(accessibility = "clickIconCancelSignInWithOther")
    public WebElement clickIconCancelSignInWithOther;

    //Click tiếp tục với số điện thoại
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Tiếp tục với số điện thoại\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonContinueWithPhoneNumber")
    public WebElement clickButtonContinueWithPhoneNumber;

    //Click icon "X" tắt popup chọn 1 số điện thoại
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Thoát\"]")
    @iOSXCUITFindBy(accessibility = "clickIconCancelChoosePhoneNumberOther")
    public WebElement clickIconCancelChoosePhoneNumberOther;

    //Click icon Back
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.grabtaxi.passenger:id/btn_back_verify_number\"]")
    @iOSXCUITFindBy(accessibility = "clickIconBack")
    public WebElement clickIconBack;

    //Click vào ô nhập số điện thoại
    @AndroidFindBy(id = "com.grabtaxi.passenger:id/verify_number_edit_number")
    @iOSXCUITFindBy(accessibility = "clickTypePhoneNumber")
    private WebElement clickTypePhoneNumber;


    //Nhập số điện thoại
    public void signIn(String phoneNumber) {
        //Click vào field nhập sdt
        MobileUI_Using_LogUtils_AllureReport.clickElement_UseWebElement_NoSetTimeout(clickTypePhoneNumber);
        //Nhập sdt
        MobileUI_Using_LogUtils_AllureReport.setText_UseWebElement_NoSetTimeout(clickTypePhoneNumber,phoneNumber);
    }

    //Click button Tiếp tục
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.grabtaxi.passenger:id/gds_button_content_layout\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonContinue")
    private WebElement clickButtonContinue;

    //Click button Cho phép gửi thông báo
//    com.android.permissioncontroller:id/permission_allow_foreground_only_button
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    @iOSXCUITFindBy(accessibility = "clickButtonAcceptNotify")
    private WebElement clickButtonAcceptNotify;

    //Click button Bỏ qua
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bỏ qua\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonSkip")
    private WebElement clickButtonSkip;

    //----------------------

    //Click Nút "Tất cả" trên thanh menu
    @AndroidFindBy(id = "com.grabtaxi.passenger:id/img_more_dots")
    @iOSXCUITFindBy(accessibility = "clickButtonAll")
    public WebElement clickButtonAll;

    //Click Nút "Đi chợ"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Đi chợ\"]")
    @iOSXCUITFindBy(accessibility = "clickMarket")
    public WebElement clickMarket;

    //Click vào thanh search giao tới địa chỉ
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.grabtaxi.passenger:id/mart_delivery_to_address\"]")
    @iOSXCUITFindBy(accessibility = "clickBarSearchDeliveryAddress")
    public WebElement clickBarSearchDeliveryAddress;


    //Click vào thanh search địa chỉ giao tới
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\"com.grabtaxi.passenger:id/poi_first_search\"]")
    @iOSXCUITFindBy(accessibility = "clickBarSearchAddressDeliveryTo")
    public WebElement clickBarSearchAddressDeliveryTo;

    //Nhập địa chỉ giao tới
    public void TypeAddressDeliveryTo(String address) {
        //Click vào thanh search địa chỉ giao tới
        MobileUI_Using_LogUtils_AllureReport.clickElement_UseWebElement_NoSetTimeout(clickBarSearchAddressDeliveryTo);
        //Nhập địa chỉ giao tới
        MobileUI_Using_LogUtils_AllureReport.setText_UseWebElement_NoSetTimeout(clickBarSearchAddressDeliveryTo,address);
    }

    //Chọn item địa chỉ đầu tiên trong danh sách gợi ý
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.grabtaxi.passenger:id/list_item_heading\" and @text=\"GB Building\"]")
    @iOSXCUITFindBy(accessibility = "clickItemFirstAddress")
    public WebElement clickItemFirstAddress;


    //Bia sữa nước ngọt
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bia Sữa Nước Ngọt\"]")
    @iOSXCUITFindBy(accessibility = "clickBeerMilk")
    public WebElement clickBeerMilk;




    //------------------------------------------------------------------------------------------------

    //Các hàm xử lý chung cho nhiều trang đều có
    public void clickPermission() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickPermission);
    }

    public void clickButtonSignIn() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonSignIn);
    }

    public void clickIconCancelSignInWithOther() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconCancelSignInWithOther);
    }

    public void clickButtonContinueWithPhoneNumber() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonContinueWithPhoneNumber);
    }

    public void clickIconCancelChoosePhoneNumberOther() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconCancelChoosePhoneNumberOther);
    }

    public void clickIconBack() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconBack);
    }

    public void clickButtonContinue() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonContinue);
    }

    public void clickButtonAcceptNotify() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonAcceptNotify);
    }

    public void clickButtonSkip() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonSkip);
    }

    //---------------------------------

    public void clickButtonAll() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickButtonAll);
    }

    public void clickMarket() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickMarket);
    }

    public void clickBarSearchDeliveryAddress() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickBarSearchDeliveryAddress);
    }

    public void clickBarSearchAddressDeliveryTo() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickBarSearchAddressDeliveryTo);
    }

    public void clickItemFirstAddress() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickItemFirstAddress);
    }

    public void clickBeerMilk() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickBeerMilk);
    }
}
