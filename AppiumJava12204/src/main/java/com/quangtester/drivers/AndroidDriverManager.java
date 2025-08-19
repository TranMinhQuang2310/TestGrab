package com.quangtester.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverManager {
    //ThreadLocal => hỗ trợ chạy đa luồng
    //AndroidDriver => dùng để hỗ trợ test chức năng xem notify
    private static ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    //Set driver khi khởi tạo thiết bị
    public static void setDriver(AndroidDriver driverInstance) {
        driver.set(driverInstance);
    }

    //Get driver ra để dùng
    public static AndroidDriver getDriver() {
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
