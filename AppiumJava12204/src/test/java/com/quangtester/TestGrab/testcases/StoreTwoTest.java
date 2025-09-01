package com.quangtester.TestGrab.testcases;

import com.quangtester.TestGrab.pages.SignInPage;
import com.quangtester.TestGrab.pages.StoreTwoPage;
import com.quangtester.common.BaseTest_Using_Log4j2_AllureReports;
import io.qameta.allure.*;
import org.testng.annotations.Test;

//Gom nhiều @Feature liên quan như Login, Search, Profile…
@Epic("Web interface")
//Gom nhiều @Story liên quan lại với nhau như Authentication, Basic navigation…
@Feature("Essential features")
public class StoreTwoTest extends BaseTest_Using_Log4j2_AllureReports {
    SignInPage signInPage;
    StoreTwoPage storeTwoPage;

    @Test
    //Test thuộc nhóm tính năng tìm kiếm
    @Story("Functional test of Page")
    //Set độ ưu tiên cho testcase
    @Severity(SeverityLevel.CRITICAL)
    //Mô tả chức năng search thông tin hợp lệ.
    @Description("Test case to verify the search functionality in the Signin page")
    public void testStoreTwo() {
        signInPage = new SignInPage();
        //Khởi tạo trang StoreTwoPage khi đăng nhập thành công
        storeTwoPage = signInPage.signIn();

        //Xử lý trang StoreTwoPage
        storeTwoPage.handleStoreTwo();


    }
}
