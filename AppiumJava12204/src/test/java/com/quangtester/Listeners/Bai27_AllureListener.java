package com.quangtester.Listeners;

import com.quangtester.drivers.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

//Quản lý chụp màn hình khi mở Alllure Report
public class Bai27_AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestSchedule(TestResult result) {
    }

    @Override
    public void afterTestSchedule(TestResult result) {
    }

    @Override
    public void beforeTestUpdate(TestResult result) {
    }

    @Override
    public void afterTestUpdate(TestResult result) {
    }

    @Override
    public void beforeTestStart(TestResult result) {
    }

    @Override
    public void afterTestStart(TestResult result) {
    }

    //Trước khi @Test dừng lại => Kiểm tra status là Pass/Fail
    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus().equals(Status.PASSED)) {
            //Kiểm tra xem @Test đã dừng lại chưa
            if (DriverManager.getDriver() != null) {
                //Nếu dừng lại rồi => Chụp màn hình case Passed
                Allure.addAttachment(result.getName() + "_Passed_Screenshot", new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }
        if (result.getStatus().equals(Status.FAILED)) {
            if (DriverManager.getDriver() != null) {
                //Nếu dừng lại rồi => Chụp màn hình case Failed
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
            }
        }
    }

    @Override
    public void afterTestStop(TestResult result) {
    }

    @Override
    public void beforeTestWrite(TestResult result) {
    }

    @Override
    public void afterTestWrite(TestResult result) {
    }

}