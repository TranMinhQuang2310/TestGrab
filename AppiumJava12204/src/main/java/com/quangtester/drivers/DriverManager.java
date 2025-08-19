package com.quangtester.drivers;

import io.appium.java_client.AppiumDriver;

//Quản lý driver => Thiết lập chạy đa luồng
public class DriverManager {
    //ThreadLocal => hỗ trợ chạy đa luồng
    //<AppiumDriver> => Cho phép chạy multi platform
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    //Set driver khi khởi tạo thiết bị
    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    //Get driver ra để dùng
    public static AppiumDriver getDriver() {
        return driver.get();
    }

    //Tắt driver
    public static void quitDriver() {
        if(driver.get() != null) {
            //Tắt driver
            getDriver().quit();
            //Xoá vùng bộ nhớ để máy không bị tốn ram
            driver.remove();
        }
    }
}
