package com.quangtester.TestGrab.pages;

import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public SignInPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    public void signIn() {

        MobileUI.sleep(3);
        clickPermission();

        MobileUI.sleep(3);
        clickButtonSignIn();

        MobileUI.sleep(5);
        clickIconCancelSignInWithOther();

        //MobileUI.sleep(12);

        //clickIconCancelChoosePhoneNumberOther();

        //clickIconBack();

        MobileUI.sleep(3);

        clickButtonContinueWithPhoneNumber();

        //MobileUI.sleep(3);

        //clickIconCancelSignInWithOther();

        MobileUI.sleep(3);
        signIn("0398982068");

        MobileUI.sleep(3);

        clickButtonContinue();

        MobileUI.sleep(8);

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


    }
}
