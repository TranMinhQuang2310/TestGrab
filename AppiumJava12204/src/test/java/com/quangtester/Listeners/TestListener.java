package com.quangtester.Listeners;

import com.quangtester.constants.ConfigData;
import com.quangtester.helpers.CaptureHelpers;
import com.quangtester.helpers.SystemHelpers;
import com.quangtester.keywords.MobileUI;
import com.quangtester.reports.AllureManager;
import com.quangtester.utils.DateUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    //onStart: chạy trước khi thực thi @Test
    @Override
    public void onStart(ITestContext result) {
        System.out.println("♻\uFE0F Setup môi trường: " + result.getStartDate());
    }

    //onFinish : chạy sau khi đã chạy xong toàn bộ @Test
    @Override
    public void onFinish(ITestContext result) {
        System.out.println("\uD83D\uDD06 Kết thúc chạy test: " + result.getEndDate());
    }

    //onTestStart: chạy trong khi bắt đầu thực thi @Test
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("➡\uFE0F Bắt đầu chạy test case: " + result.getName());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        System.out.println("Thời gian hiện tại: " + formattedDateTime);

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            CaptureHelpers.startRecording();
        }
    }

    //onTestSuccess: chạy trong khi @Test chạy thành công
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Test case " + result.getName() + " is passed.");
        System.out.println("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        System.out.println("Thời gian hiện tại: " + formattedDateTime);

        //Nếu set biến "SCREENSHOT_PASS" = true => Xuất tên ảnh màn hình đã được chụp khi Testcase chạy Passed
        if(ConfigData.SCREENSHOT_PASS.equalsIgnoreCase("true")) {
            CaptureHelpers.captureScreenshot(result.getName());
        }

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + "exports/videos");

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + "exports/videos/recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI.sleep(3);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }
    }

    //onTestFailure: chạy trong khi @Test chạy thất bại
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Test case " + result.getName() + " is failed.");
        System.out.println("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        System.out.println("Thời gian hiện tại: " + formattedDateTime);

        System.out.println("Nguyên nhân :" + result.getThrowable());

        //Nếu set biến "SCREENSHOT_FAIL" = true => Xuất tên ảnh màn hình đã được chụp khi Testcase chạy Failed
        if(ConfigData.SCREENSHOT_FAIL.equalsIgnoreCase("true")) {
            CaptureHelpers.captureScreenshot(result.getName());
        }

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + "exports/videos");

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + "exports/videos/recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI.sleep(3);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }

        //Xuât tên ảnh màn hình đã được chụp trên Allure Report
        AllureManager.saveScreenshotPNG();
    }

    //onTestSkipped: chạy khi @Test bị bỏ qua
    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⛔\uFE0F Test case " + result.getName() + " is skipped.");
        System.out.println("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        System.out.println("Thời gian hiện tại: " + formattedDateTime);

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + "exports/videos");

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + "exports/videos/recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI.sleep(3);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }
    }
}
