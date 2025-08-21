package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.AndroidDriverManager;
import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
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
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hủy\"]")
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

    //Click button Cho phép gửi thông báo
//    com.android.permissioncontroller:id/permission_allow_foreground_only_button
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    @iOSXCUITFindBy(accessibility = "clickButtonAcceptNotify")
    private WebElement clickButtonAcceptNotify;

    //Click button Bỏ qua
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bỏ qua\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonSkip")
    private WebElement clickButtonSkip;

    //----------------------

    //Click Nút "Tất cả"
    @AndroidFindBy(id = "com.grabtaxi.passenger:id/img_more_dots")
    @iOSXCUITFindBy(accessibility = "clickButtonAll")
    public WebElement clickButtonAll;

    //Click Nút "Đi chợ"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Đi chợ\"]")
    @iOSXCUITFindBy(accessibility = "clickMarket")
    public WebElement clickMarket;

    //Bia sữa nước ngọt
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Bia Sữa Nước Ngọt\"]")
    @iOSXCUITFindBy(accessibility = "clickBeerMilk")
    public WebElement clickBeerMilk;

    //Item đầu tiên
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.grabtaxi.passenger:id/recycler_view\"]/androidx.compose.ui.platform.ComposeView[1]")
    @iOSXCUITFindBy(accessibility = "clickItemFirst")
    public WebElement clickItemFirst;

    //Click Giao ngay
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.grabtaxi.passenger:id/gm_alert_dialog_negative_btn\"]")
    @iOSXCUITFindBy(accessibility = "clickDeliveredImmediately")
    public WebElement clickDeliveredImmediately;

    //Click icon ...
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.grabtaxi.passenger:id/btn_omit\"]")
    @iOSXCUITFindBy(accessibility = "clickIconExpand")
    public WebElement clickIconExpand;

    //Click detail store
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.grabtaxi.passenger:id/group_text\" and @text=\"Chi tiết cửa hàng\"]")
    @iOSXCUITFindBy(accessibility = "clickDetailStore")
    public WebElement clickDetailStore;

    //Click icon Back To HomePage shop
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"com.grabtaxi.passenger:id/gds_appbar_navigation_button\"]")
    @iOSXCUITFindBy(accessibility = "clickIconBackToHomePageShop")
    public WebElement clickIconBackToHomePageShop;

    //Click search bar
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.grabtaxi.passenger:id/mart_search_hint\"]")
    @iOSXCUITFindBy(accessibility = "clickSearchBar")
    public WebElement clickSearchBar;

    //Click search bar before type text
    @AndroidFindBy(id = "com.grabtaxi.passenger:id/et_search")
    @iOSXCUITFindBy(accessibility = "clickSearchBarBeforeTypeText")
    public WebElement clickSearchBarBeforeTypeText;

    //Field Data in Bar searcg
    public void fieldDataInBarSearch(String data) {
        MobileUI_Using_LogUtils_AllureReport.clickElement_UseWebElement_NoSetTimeout(clickSearchBarBeforeTypeText);
        MobileUI_Using_LogUtils_AllureReport.setText_UseWebElement_NoSetTimeout(clickSearchBarBeforeTypeText,data);

        // Nhấn Enter ngay sau khi điền
        AndroidDriverManager.getDriver().pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    //Click icon Back To List shop
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.grabtaxi.passenger:id/back\"]")
    @iOSXCUITFindBy(accessibility = "clickIconBackToListShop")
    public WebElement clickIconBackToListShop;



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
        clickMarket.click();
    }

    public void clickBeerMilk() {
        clickBeerMilk.click();
    }

    public void clickItemFirst() {
        clickItemFirst.click();
    }

    public void clickDeliveredImmediately() {
        clickDeliveredImmediately.click();
    }

    public void clickIconExpand() {
        clickIconExpand.click();
    }

    public void clickDetailStore() {
        clickDetailStore.click();
    }

    public void clickIconBackToHomePageShop() {
        clickIconBackToHomePageShop.click();
    }

    public void clickSearchBar() {
        clickSearchBar.click();
    }

    public void clickSearchBarBeforeTypeText() {
        clickSearchBarBeforeTypeText.click();
    }



}
