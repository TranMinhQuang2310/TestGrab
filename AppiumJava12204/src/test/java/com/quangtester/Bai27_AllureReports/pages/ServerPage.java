package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

//Click nút "Config" ở dưới bottom tab => Click item "Server Database"
public class ServerPage extends BasePage {
    public ServerPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    //Icon tải xuống
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Data 1')]/android.widget.Button")
    @iOSXCUITFindBy(xpath = "//android.view.View[contains(@content-desc,'Data 1')]/android.widget.Button")
    private WebElement itemDownloadServerDatabase;

    //Click icon tải xuống
    public void downloadServerDatabase() {
        System.out.println("Download Server Database");
        itemDownloadServerDatabase.click();
    }

}
