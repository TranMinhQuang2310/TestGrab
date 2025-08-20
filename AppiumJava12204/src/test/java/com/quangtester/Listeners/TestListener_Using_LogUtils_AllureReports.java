package com.quangtester.Listeners;

import com.quangtester.constants.ConfigData;
import com.quangtester.helpers.CaptureHelpers;
import com.quangtester.helpers.SystemHelpers;
import com.quangtester.keywords.MobileUI_Using_LogUtils_AllureReport;
import com.quangtester.reports.AllureManager;
import com.quangtester.utils.DateUtils;
import com.quangtester.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener_Using_LogUtils_AllureReports implements ITestListener {

    //onStart: chạy trước khi thực thi @Test
    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("♻\uFE0F Setup môi trường: " + result.getStartDate());
    }

    //onFinish : chạy sau khi đã chạy xong toàn bộ @Test
    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("\uD83D\uDD06 Kết thúc chạy test: " + result.getEndDate());
    }

    //onTestStart: chạy trong khi bắt đầu thực thi @Test
    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("➡\uFE0F Bắt đầu chạy test case: " + result.getName());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        LogUtils.info("Thời gian hiện tại: " + formattedDateTime);

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            CaptureHelpers.startRecording();
        }
    }

    //onTestSuccess: chạy trong khi @Test chạy thành công
    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("✅ Test case " + result.getName() + " is passed.");
        LogUtils.info("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        LogUtils.info("Thời gian hiện tại: " + formattedDateTime);

        //Xuất tên ảnh màn hình đã được chụp khi Testcase chạy Passed
        CaptureHelpers.captureScreenshot(result.getName());

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH);

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH + "recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI_Using_LogUtils_AllureReport.sleep(5);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }

        //Add screenshot to Allure report => Đính kèm ảnh chụp màn hình khi testcase chạy failed
        AllureManager.saveScreenshotPNG();
    }

    //onTestFailure: chạy trong khi @Test chạy thất bại
    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("❌ Test case " + result.getName() + " is failed.");
        LogUtils.error("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        LogUtils.error("Thời gian phát hiện lỗi : " + formattedDateTime);

        LogUtils.error("Nguyên nhân lỗi :" + result.getThrowable());

        //Xuất tên ảnh màn hình đã được chụp khi Testcase chạy Failured
        CaptureHelpers.captureScreenshot(result.getName());

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH);

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH + "recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI_Using_LogUtils_AllureReport.sleep(5);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }

        //Add screenshot to Allure report => Đính kèm ảnh chụp màn hình khi testcase chạy failed
        AllureManager.saveScreenshotPNG();
    }

    //onTestSkipped: chạy khi @Test bị bỏ qua
    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.info("⛔\uFE0F Test case " + result.getName() + " is skipped.");
        LogUtils.info("==> Status: " + result.getStatus());

        LocalDateTime now = LocalDateTime.now(); //Lấy ngày giờ hiện tại
        // Định dạng ngày giờ theo mẫu
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // In ra kết quả
        String formattedDateTime = now.format(formatter);
        LogUtils.info("Thời gian hiện tại: " + formattedDateTime);

        //Nơi set up Record Video
        // Tạo tên file video duy nhất dựa trên device và thread
        SystemHelpers.createFolder(SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH);

        //getCurrentDir => Lấy đường dẫn hiện tại từ trong ổ đĩa trỏ đến file project để lưu file log vào đường dẫn này
        // Thread.currentThread().getId() => số luồng chạy
        String videoFileName = SystemHelpers.getCurrentDir() + ConfigData.RECORD_VIDEO_PATH + "recording_" + result.getName() + "_" + Thread.currentThread().getId() + "_" + SystemHelpers.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";

        //Nếu set biến "RECORD_VIDEO" = true => Bắt đầu Record Video
        if(ConfigData.RECORD_VIDEO.equalsIgnoreCase("true")) {
            MobileUI_Using_LogUtils_AllureReport.sleep(5);
            //Xuât tên ảnh màn hình đã được chụp khi Stop Record Video
            CaptureHelpers.stopRecording(videoFileName);
        }
    }
}
