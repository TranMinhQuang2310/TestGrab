package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
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
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hủy\"]")
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

    //Click button Bỏ qua
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bỏ qua\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonSkip")
    private WebElement clickButtonSkip;

    //Click button Cho phép gửi thông báo
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    @iOSXCUITFindBy(accessibility = "clickButtonAcceptNotify")
    private WebElement clickButtonAcceptNotify;

    //----------------------

    //Click Nút "Tất cả"
    @AndroidFindBy(id = "com.grabtaxi.passenger:id/img_more_dots")
    @iOSXCUITFindBy(accessibility = "clickButtonAll")
    public WebElement clickButtonAll;

    //Click Nút "Đi chợ"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Đi chợ\"]")
    @iOSXCUITFindBy(accessibility = "market")
    public WebElement market;


    //Các hàm xử lý chung cho nhiều trang đều có
    public void clickPermission() {
        clickPermission.click();
    }

    public void clickButtonSignIn() {
        clickButtonSignIn.click();
    }

    public void clickIconCancelSignInWithOther() {
        clickIconCancelSignInWithOther.click();
    }

    public void clickButtonContinueWithPhoneNumber() {
        clickButtonContinueWithPhoneNumber.click();
    }

    public void clickIconCancelChoosePhoneNumberOther() {
        clickIconCancelChoosePhoneNumberOther.click();
    }

    public void clickIconBack() {
        clickIconBack.click();
    }

    public void clickButtonContinue() {
        clickButtonContinue.click();
    }

    public void clickButtonAcceptNotify() {
        clickButtonAcceptNotify.click();
    }

    public void clickButtonSkip() {
        clickButtonSkip.click();
    }

    //---------------------------------

    public void clickButtonAll() {
        clickButtonAll.click();
    }


    public void clickMarket() {
        market.click();
    }

}
