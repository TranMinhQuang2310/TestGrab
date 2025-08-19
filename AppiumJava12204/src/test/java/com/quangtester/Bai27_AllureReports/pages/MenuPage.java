package com.quangtester.Bai27_AllureReports.pages;

import com.quangtester.drivers.DriverManager;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

//Dùng app Taurus
public class MenuPage extends BasePage {
    // Constructor (Cấu hình mặc định của appium)
    public MenuPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()),this);
    }

    //Element/Locators thuộc chính trang này (màn hình này)
    //Nút Menu ở dưới thanh bottom tab
    @AndroidFindBy(accessibility = "Menu")
    @iOSXCUITFindBy(accessibility = "Menu")
    private WebElement menuHome;

    //Thanh search
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement inputSearch;

    //Item đầu tiên trong danh sách
    @AndroidFindBy(xpath = "(//android.view.View[contains(@content-desc,\"Table\")])[1]")
    @iOSXCUITFindBy(accessibility = "")
    private WebElement firstItemTable;

    //Danh sách item
    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Table\")]")
    @iOSXCUITFindBy(xpath = "")
    private List<WebElement> listItemTable;

    //Các hàm xử lý trong chính nội bộ trang này (màn hình này)
    //Thực hiện nhập dữ liệu vào thanh search
    public void searchTable(String tableName) {
        //Click vô tab "Menu" ở dưới bottom tab
        clickMenuMenu();
        //Click vào thanh search
        MobileUI_Using_LogUtils_AllureReport.clickElement_UseWebElement_NoSetTimeout(inputSearch);
        //Nhập dữ liệu vào thanh search
        MobileUI_Using_LogUtils_AllureReport.setText_UseWebElement_NoSetTimeout(inputSearch,tableName);
    }

    //Kiểm tra số lượng item hiển thị sau khi search
    public void checkTableResultTotal(int expectedTotal) {
        List<WebElement> listTables = listItemTable;
        System.out.println("Table total: " + listTables.size());
        //Assert.assertTrue(listTables.size() >= expectedTotal);
        MobileUI_Using_LogUtils_AllureReport.assertTrueCondition(listTables.size() >= expectedTotal,"Total item is not correct");
    }
}
