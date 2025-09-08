package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.AndroidDriverManager;
import com.quangtester.drivers.DriverManager;
import com.quangtester.helpers.CaptureHelpers;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import com.quangtester.reports.AllureManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignInPage extends BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public SignInPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    //Click Store đầu tiên
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.grabtaxi.passenger:id/recycler_view\"]/androidx.compose.ui.platform.ComposeView[1]")
    @iOSXCUITFindBy(accessibility = "clickStoreFirst")
    public WebElement clickStoreFirst;

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

    //Nhập dữ liệu vào thanh search bar và nhấn Enter
    public void fieldDataInBarSearch(String data) {
        MobileUI_Using_LogUtils_AllureReport.clickElement_UseWebElement_NoSetTimeout(clickSearchBarBeforeTypeText);
        MobileUI_Using_LogUtils_AllureReport.setText_UseWebElement_NoSetTimeout(clickSearchBarBeforeTypeText,data);

        MobileUI.sleep(2);
        // Ẩn bàn phím ngay sau khi điền
        if(DriverManager.getDriver() instanceof AndroidDriver) {
            ((AndroidDriver) DriverManager.getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
        }else {
            System.out.println("Driver không phải là AndroidDriver, không thể nhấn Enter.");
        }

    }

    //Click icon Back To HomePage Category Shop
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.grabtaxi.passenger:id/iv_close\"]")
    @iOSXCUITFindBy(accessibility = "clickIconBackToHomePageCategoryShop")
    public WebElement clickIconBackToHomePageCategoryShop;

    ////Click icon Back To List Store
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.grabtaxi.passenger:id/back\"]")
    @iOSXCUITFindBy(accessibility = "clickIconBackToListStore")
    public WebElement clickIconBackToListStore;

    //------------------------------------------------------------------------------------------------
    public void clickStoreFirst() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickStoreFirst);
    }

    public void clickDeliveredImmediately() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickDeliveredImmediately);
    }

    public void clickIconExpand() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconExpand);
    }

    public void clickDetailStore() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickDetailStore);
    }

    public void clickIconBackToHomePageShop() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconBackToHomePageShop);
    }

    public void clickSearchBar() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickSearchBar);
    }

    public void clickSearchBarBeforeTypeText() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickSearchBarBeforeTypeText);
    }

    public void clickIconBackToHomePageCategoryShop() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconBackToHomePageCategoryShop);
    }

    public void clickIconBackToListStore() {
        MobileUI.clickElement_UseWebElement_NoSetTimeout(clickIconBackToListStore);
    }

    //------------------------------------------------------------------------------------------------

    public StoreTwoPage signIn() {
        //Click Nút permission
        //MobileUI.sleep(4);
        //clickPermission();

        //Click button "Đăng nhập"
        MobileUI.sleep(6);
        clickButtonSignIn();

        //Click icon "X" tắt popup Đăng nhập = cách khác
        MobileUI.sleep(7);
        clickIconCancelSignInWithOther();


        //clickIconBack();

        //Click tiếp tục với số điện thoại
        MobileUI.sleep(3);
        clickButtonContinueWithPhoneNumber();

        //Click icon "X" tắt popup chọn 1 số điện thoại
        MobileUI.sleep(5);
        clickIconCancelChoosePhoneNumberOther();

        //Click vào ô nhập số điện thoại
        //Nhập số điện thoại
        MobileUI.sleep(5);
        signIn("0398982068");

        //Click button Tiếp tục
        MobileUI.sleep(7);
        clickButtonContinue();

        //Click button Cho phép gửi thông báo
        MobileUI.sleep(7);
        clickButtonAcceptNotify();

        //Click button Bỏ qua
        //MobileUI.sleep(5);
        //clickButtonSkip();

        //----------------------

        //Click Nút "Tất cả" trên thanh menu
        MobileUI.sleep(4);
        clickButtonAll();

        //Click Nút "Đi chợ"
        MobileUI.sleep(6);
        clickMarket();

        //Click vào thanh search giao tới địa chỉ
        MobileUI.sleep(4);
        clickBarSearchDeliveryAddress();

        //Click vào thanh search địa chỉ giao tới
        MobileUI.sleep(4);
        clickBarSearchAddressDeliveryTo();

        //Nhập địa chỉ giao tới
        MobileUI.sleep(4);
        TypeAddressDeliveryTo("Toà nhà GB");

        //Chọn item địa chỉ đầu tiên trong danh sách gợi ý
        MobileUI.sleep(4);
        clickItemFirstAddress();

        //Scroll sang trái
        MobileUI.sleep(4);
        MobileUI_Using_LogUtils_AllureReport.scrollLeftToaDo();

        //Click Nút "Bia sữa nước ngọt"
        MobileUI.sleep(4);
        clickBeerMilk();

        //Click Store đầu tiên
        MobileUI.sleep(4);
        clickStoreFirst();

//        MobileUI.sleep(3);
//        MobileUI_Using_LogUtils_AllureReport.scrollGestureCommand();

        //Click Giao ngay
        MobileUI.sleep(6);
        clickDeliveredImmediately();

        //Click icon ...
        MobileUI.sleep(4);
        clickIconExpand();

        //Click Detail Store
        MobileUI.sleep(4);
        clickDetailStore();

//        MobileUI.sleep(3);
//        List<WebElement> descriptions = AndroidDriverManager.getDriver().findElements(AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.grabtaxi.passenger:id/compose_view_merchant_info\"]/android.view.View/android.widget.TextView"));
//        System.out.println("Detail Information Shop: ");
//
//        for(WebElement description : descriptions) {
//            try {
//                String descriptionText = description.getText();
//                System.out.println(descriptionText);
//            } catch (NoSuchElementException e) {
//                System.out.println("*** Không tìm thấy phần mô tả cho thông báo này (hoặc không có mô tả).");
//            }
//        }

        //Lăn xuống xem thông tin cửa hàng và chụp hình
        MobileUI.sleep(4);
        MobileUI_Using_LogUtils_AllureReport.scroll(512,2071,521,1388,1000);

        //Click icon Back To HomePage shop
        MobileUI.sleep(4);
        clickIconBackToHomePageShop();

        //Tìm kiếm sản phẩm
        MobileUI.sleep(4);
        clickSearchBar();

        //Click search bar before type text
        MobileUI.sleep(4);
        clickSearchBarBeforeTypeText();

        //Nhập dữ liệu vào thanh search bar và nhấn Enter
        MobileUI.sleep(4);
        fieldDataInBarSearch("Coca");

        //Chụp màn hình khi mới vào
        CaptureHelpers.captureScreenshot("Screenshot Coca First");

        //Lăn xuống xem thông tin sản phẩm
        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(543,2012,548,1213,1000);
        MobileUI.sleep(3);

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(539,1868,543,1276,1000);
        MobileUI.sleep(3);

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(526,1954,530,1276,1000);
        MobileUI.sleep(3);

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(561,2160,570,1168,1000);
        MobileUI.sleep(3);

        //Click icon Back To HomePage Category Shop
        clickIconBackToHomePageCategoryShop();

        MobileUI.sleep(3);
        //Click icon Back To List Store
        clickIconBackToListStore();

        return new StoreTwoPage();

    }
}
