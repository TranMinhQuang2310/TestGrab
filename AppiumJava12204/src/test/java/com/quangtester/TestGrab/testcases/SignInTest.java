package com.quangtester.TestGrab.testcases;

import com.quangtester.TestGrab.pages.SignInPage;
import com.quangtester.common.BaseTest_Using_Log4j2_AllureReports;
import io.qameta.allure.*;
import org.testng.annotations.Test;

//Gom nhiều @Feature liên quan như Login, Search, Profile…
@Epic("Web interface")
//Gom nhiều @Story liên quan lại với nhau như Authentication, Basic navigation…
@Feature("Essential features")
public class SignInTest extends BaseTest_Using_Log4j2_AllureReports {
    SignInPage signInPage ;

    //Thực hiện chức năng tìm kiếm
    @Test
    //Test thuộc nhóm tính năng tìm kiếm
    @Story("Functional test of Page")
    //Set độ ưu tiên cho testcase
    @Severity(SeverityLevel.CRITICAL)
    //Mô tả chức năng search thông tin hợp lệ.
    @Description("Test case to verify the search functionality in the menu page")
    public void signInSuccess() {
        signInPage = new SignInPage();
        signInPage.signIn();
    }

}
