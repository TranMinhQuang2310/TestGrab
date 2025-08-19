package com.quangtester.Bai27_AllureReports.testcases;

import com.quangtester.Bai27_AllureReports.pages.LoginPage;
import com.quangtester.common.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;

//Gom nhiều @Feature liên quan như Login, Search, Profile…
@Epic("Web interface")
//Gom nhiều @Story liên quan lại với nhau như Authentication, Basic navigation…
@Feature("Essential features")
public class LoginTest extends BaseTest_Using_Log4j2_AllureReports {
    //Khai báo các đối tượng Page class liên quan
    private LoginPage loginPage;

    //Thực hiện chức năng đăng nhập thành công
    @Test
    //Test thuộc nhóm tính năng xác thực đăng nhập.
    @Story("Authentication")
    //Set độ ưu tiên cho testcase
    @Severity(SeverityLevel.CRITICAL)
    //Mô tả chức năng đăng nhập với thông tin đăng nhập hợp lệ.
    @Description("Test case to verify the login functionality with valid credentials")
    //Trỏ đến đường link được truyền vào
    @Link(name = "Check Menu Page", url = "https://jira.com/anhtester/crm/menu/123")
    public void testLoginSuccess() {
        //Khởi tạo đối tượng Page class
        loginPage = new LoginPage();

        //Gọi hàm từ Page class sử dụng
        loginPage.login("admin", "admin");

        loginPage.verifyLoginSuccess();
    }

    //Thực hiện chức năng đăng nhập thất bại
    @Test
    //Test thuộc nhóm tính năng xác thực đăng nhập.
    @Story("Authentication")
    //Set độ ưu tiên cho testcase
    @Severity(SeverityLevel.CRITICAL)
    //Mô tả chức năng đăng nhập với thông tin đăng nhập hợp lệ.
    @Description("Test case to verify the login functionality with invalid credentials")
    //Trỏ đến đường link được truyền vào
    @Link(name = "Check Menu Page", url = "https://jira.com/anhtester/crm/menu/123")
    public void testLoginFailWithUsernameInvalid() {
        //Khởi tạo đối tượng Page class
        loginPage = new LoginPage();

        //Gọi hàm từ Page class sử dụng
        //loginPage.login("admin123", "admin");
        loginPage.login("admin", "admin");

        loginPage.verifyLoginFail();
    }
}
