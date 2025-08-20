package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.AndroidDriverManager;
import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import io.appium.java_client.AppiumBy;
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

        MobileUI.sleep(10);
        clickPermission();

        MobileUI.sleep(10);
        clickButtonSignIn();

        MobileUI.sleep(10);
        clickIconCancelSignInWithOther();


        //clickIconBack();

        MobileUI.sleep(3);
        clickButtonContinueWithPhoneNumber();

        MobileUI.sleep(12);

        clickIconCancelChoosePhoneNumberOther();


        MobileUI.sleep(10);
        signIn("0398982068");

        MobileUI.sleep(20);

        clickButtonContinue();

        MobileUI.sleep(20);

        clickButtonAcceptNotify();

        MobileUI.sleep(3);
        clickButtonSkip();

        //----------------------
        MobileUI.sleep(3);
        clickButtonAll();

        MobileUI.sleep(3);
        clickMarket();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scrollLeft();

        MobileUI.sleep(3);
        clickBeerMilk();

        MobileUI.sleep(3);
        clickItemFirst();

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scrollGestureCommand();

        MobileUI.sleep(3);
        clickDeliveredImmediately();

        MobileUI.sleep(3);
        clickIconExpand();

        MobileUI.sleep(3);
        clickDetailStore();

        MobileUI.sleep(3);
        List<WebElement> descriptions = AndroidDriverManager.getDriver().findElements(AppiumBy.xpath("//androidx.compose.ui.platform.ComposeView[@resource-id=\"com.grabtaxi.passenger:id/compose_view_merchant_info\"]/android.view.View/android.widget.TextView"));
        System.out.println("Detail Information Shop: ");

        for(WebElement description : descriptions) {
            try {
                String descriptionText = description.getText();
                System.out.println(descriptionText);
            } catch (NoSuchElementException e) {
                System.out.println("*** Không tìm thấy phần mô tả cho thông báo này (hoặc không có mô tả).");
            }
        }

        MobileUI.sleep(3);
        MobileUI_Using_LogUtils_AllureReport.scrollGestureCommand();


    }
}
