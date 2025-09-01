package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.DriverManager;
import com.quangtester.helpers.CaptureHelpers;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import com.quangtester.reports.AllureManager;
import com.quangtester.utils.LogUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StoreTwoPage extends BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public StoreTwoPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    //Click Store thứ hai
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.grabtaxi.passenger:id/recycler_view\"]/androidx.compose.ui.platform.ComposeView[2]")
    @iOSXCUITFindBy(accessibility = "clickStoreTwo")
    public WebElement clickStoreTwo;

    //Click chi nhánh đầu tiên
    @AndroidFindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.grabtaxi.passenger:id/merchant_desc\"])[1]")
    @iOSXCUITFindBy(accessibility = "clickFirstBranchStoreTwo")
    public WebElement clickFirstBranchStoreTwo;

    //Click button OK trên popup "Lưu món vào quán yêu thích"
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.grabtaxi.passenger:id/gm_alert_dialog_positive_btn\"]")
    @iOSXCUITFindBy(accessibility = "clickButtonOKPopupFavoriteStore")
    public WebElement clickButtonOKPopupFavoriteStore;

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

    //Các hàm xử lý chung cho nhiều trang đều có
    public void clickStoreTwo() {
        clickStoreTwo.click();
    }

    public void clickFirstBranchStoreTwo() {
        clickFirstBranchStoreTwo.click();
    }

    public void clickButtonOKPopupFavoriteStore() {
        clickButtonOKPopupFavoriteStore.click();
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

    public void clickIconBackToHomePageCategoryShop() {
        clickIconBackToHomePageCategoryShop.click();
    }

    public void clickIconBackToListStore() {
        clickIconBackToListStore.click();
    }


    //------------------------------------------------------------------------------------------------
    public void handleStoreTwo(){
        //Click Store thứ hai
        MobileUI.sleep(4);
        clickStoreTwo();

        //Click chi nhánh đầu tiên
        MobileUI.sleep(4);
        clickFirstBranchStoreTwo();

        //Click button OK trên popup "Lưu món vào quán yêu thích"
        MobileUI.sleep(4);
        clickButtonOKPopupFavoriteStore();

        //Click icon ...
        MobileUI.sleep(4);
        clickIconExpand();

        //Click detail store
        MobileUI.sleep(4);
        clickDetailStore();

        //Lăn xuống xem thông tin cửa hàng và chụp hình
        MobileUI.sleep(4);
        MobileUI_Using_LogUtils_AllureReport.scroll(479,2319,498,1704,1000);

        //Click icon Back To HomePage shop
        MobileUI.sleep(4);
        clickIconBackToHomePageShop();

        //Click search bar
        MobileUI.sleep(4);
        clickSearchBar();

        //Click search bar before type text
        MobileUI.sleep(4);
        clickSearchBarBeforeTypeText();

        //Nhập dữ liệu vào thanh search bar và nhấn Enter
        MobileUI.sleep(4);
        fieldDataInBarSearch("Coca");

        //Xử lý kết quả search + scroll
        MobileUI.sleep(4);
        searchAndScroll(
                By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.grabtaxi.passenger:id/recycler_view\"]/androidx.compose.ui.platform.ComposeView[1]"),
                By.xpath("//android.widget.TextView[@resource-id=\"com.grabtaxi.passenger:id/empty_view_title\"]")
        );

        //Click icon Back To HomePage Category Shop
        MobileUI.sleep(5);
        clickIconBackToHomePageCategoryShop();

        //Click icon Back To List Store
        MobileUI.sleep(4);
        clickIconBackToListStore();

    }

    /**
     * Hàm xử lý kết quả search + scroll
     */
    public void searchAndScroll(By resultLocator, By noResultLocator) {
        try {
            if (MobileUI_Using_LogUtils_AllureReport.isElementEnabled_UseByLocator(resultLocator)) {
                LogUtils.info("✅ Tìm thấy kết quả search, thực hiện scroll...");
                MobileUI_Using_LogUtils_AllureReport.scroll(529, 2257, 543, 1705, 1000);
            }
            else if (MobileUI_Using_LogUtils_AllureReport.isElementEnabled_UseByLocator(noResultLocator)) {
                LogUtils.warn("⚠️ Không có kết quả phù hợp. Chụp screenshot để báo cáo.");

                //Chụp màn hình lưu vào folder Screenshot
                String screenshotName = "Search_No_Displayed_Item_" + System.currentTimeMillis();
                CaptureHelpers.captureScreenshot(screenshotName);

                //Chụp màn hình lưu vào Allure report
                AllureManager.saveScreenshotPNG();
                LogUtils.info("📸 Screenshot captured after not displayed Item.");

            }
            else {
                LogUtils.warn("❓ Không xác định được trạng thái kết quả search.");
            }
        } catch (Exception e) {
            LogUtils.error("❌ Lỗi khi xử lý searchAndScroll: " + e.getMessage());
            //Chụp màn hình lưu vào folder Screenshot
            String screenshotName = "Error_Search_And_Scroll_" + System.currentTimeMillis();
            CaptureHelpers.captureScreenshot(screenshotName);

            //Chụp màn hình lưu vào Allure report
            AllureManager.saveScreenshotPNG();
        }
    }


}
