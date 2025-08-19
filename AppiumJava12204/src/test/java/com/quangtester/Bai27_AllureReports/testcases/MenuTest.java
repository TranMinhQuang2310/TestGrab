package com.quangtester.Bai27_AllureReports.testcases;

import com.quangtester.Bai27_AllureReports.pages.LoginPage;
import com.quangtester.Bai27_AllureReports.pages.MenuPage;
import com.quangtester.common.*;
import com.quangtester.keywords.MobileUI;
import io.qameta.allure.*;
import org.testng.annotations.Test;

//Gom nhiều @Feature liên quan như Login, Search, Profile…
@Epic("Web interface")
//Gom nhiều @Story liên quan lại với nhau như Authentication, Basic navigation…
@Feature("Essential features")
public class MenuTest extends BaseTest_Using_Log4j2_AllureReports {
    LoginPage loginPage;
    MenuPage menuPage;

    //Thực hiện chức năng tìm kiếm
    @Test
    //Test thuộc nhóm tính năng tìm kiếm
    @Story("Functional test of Menu Page")
    //Set độ ưu tiên cho testcase
    @Severity(SeverityLevel.CRITICAL)
    //Mô tả chức năng search thông tin hợp lệ.
    @Description("Test case to verify the search functionality in the menu page")
    //Trỏ đến đường link được truyền vào
    @Link(name = "Check Menu Page", url = "https://jira.com/anhtester/crm/menu/123")
    public void testSearchTable() {
        loginPage = new LoginPage();
        //Khởi tạo trang MenuPage thông qua việc Login nếu thành công
        menuPage = loginPage.login("admin", "admin");
        loginPage.verifyLoginSuccess();

        //Download data
        MobileUI.sleep(2);
        downloadDataFromServer(4);

        //Nhập dữ liệu vào thanh search
        //menuPage = new MenuPage(); //không cần khởi tạo trang Menu riêng
        menuPage.searchTable("Table 1");
        menuPage.checkTableResultTotal(1);
    }
}
