package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.AndroidDriverManager;
import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import com.quangtester.reports.AllureManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SignInPage extends BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public SignInPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    public void signIn() {

        MobileUI.sleep(5);
        clickPermission();

        MobileUI.sleep(5);
        clickButtonSignIn();

        MobileUI.sleep(5);
        clickIconCancelSignInWithOther();


        //clickIconBack();

        MobileUI.sleep(3);
        clickButtonContinueWithPhoneNumber();

        MobileUI.sleep(6);

        clickIconCancelChoosePhoneNumberOther();


        MobileUI.sleep(6);
        signIn("0398982068");

        MobileUI.sleep(10);

        clickButtonContinue();

        MobileUI.sleep(10);

        clickButtonAcceptNotify();

        MobileUI.sleep(5);
        clickButtonSkip();

        //----------------------
        MobileUI.sleep(5);
        clickButtonAll();

        MobileUI.sleep(8);
        clickMarket();

        MobileUI.sleep(5);
        MobileUI_Using_LogUtils_AllureReport.scrollLeft();

        MobileUI.sleep(5);
        clickBeerMilk();

        MobileUI.sleep(5);
        clickItemFirst();

//        MobileUI.sleep(3);
//        MobileUI_Using_LogUtils_AllureReport.scrollGestureCommand();

        MobileUI.sleep(8);
        clickDeliveredImmediately();

        MobileUI.sleep(5);
        clickIconExpand();

        MobileUI.sleep(5);
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

        MobileUI.sleep(5);
        MobileUI_Using_LogUtils_AllureReport.scroll(534,2361,552,1686,1000);
        MobileUI.sleep(5);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(5);
        clickIconBackToHomePageShop();

        MobileUI.sleep(5);
        clickSearchBar();

        MobileUI.sleep(5);
        clickSearchBarBeforeTypeText();

        MobileUI.sleep(5);
        fieldDataInBarSearch("Coca");

        MobileUI.sleep(8);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(529,2257,543,1705,1000);
        MobileUI.sleep(3);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(543,2186,543,1365,1000);
        MobileUI.sleep(3);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(562,2205,562,1539,1000);
        MobileUI.sleep(3);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scroll(548,2144,557,1587,1000);
        MobileUI.sleep(3);
        //Add screenshot to Allure report
        AllureManager.saveScreenshotPNG();

        MobileUI.sleep(5);

    }
}
