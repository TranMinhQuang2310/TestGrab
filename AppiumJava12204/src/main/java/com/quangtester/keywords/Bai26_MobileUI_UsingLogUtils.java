package com.quangtester.keywords;

import com.quangtester.constants.ConfigData;
import com.quangtester.drivers.DriverManager;
import com.quangtester.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.*;

public class Bai26_MobileUI_UsingLogUtils {
    //Tạo hàm chung để tái sử dụng

    //Tạo biến để set timeout mặc định
    //private static final int DEFAULT_TIMEOUT = 10;

    //Lấy giá trị "DEFAULT_TIMEOUT" từ file : ConfigData
    //Integer.parseInt => Chuyển về dạng số
    private static final int DEFAULT_TIMEOUT = Integer.parseInt(ConfigData.TIMEOUT_EXPLICIT_DEFAULT);

    //Tạo biến để set thời gian nghỉ ... giây
    private static final double STEP_ACTION_TIMEOUT = Double.parseDouble(ConfigData.STEP_ACTION_TIMEOUT);

    //Viết hàm nghỉ ...giây
    public static void sleep(double second) {
        LogUtils.info("[MobileUI] Sleeping for " + second + " seconds.");
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Scroll từ dưới lên trên (Dùng W3C Actions API)
    public static void scroll(int startX , int startY , int endX , int endY , int durationMillis) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing swipe from (" + startX + "," + startY + ") to (" + endX + "," + endY + ") with duration " + durationMillis + "ms.");
        //PointerInput: Đại diện cho một thiết bị đầu vào, trong trường hợp này là ngón tay (PointerInput.Kind.TOUCH)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        //Sequence: Tập hợp các hành động được thực hiện bởi một PointerInput (1 ngón tay)
        Sequence swipe = new Sequence(finger, 1);
        //createPointerMove(): Di chuyển con trỏ đến vị trí của phần tử
        //PointerInput.Origin.viewport() xác định hệ tọa độ dựa trên viewport (khung nhìn) của ứng dụng.
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
        //createPointerDown(): Mô phỏng việc chạm vào màn hình.
        swipe.addAction(finger.createPointerDown(0));
        //durationMillis : tốc độ vuốt
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(durationMillis), PointerInput.Origin.viewport(), endX, endY));
        //createPointerUp(): Mô phỏng việc nhấc ngón tay khỏi màn hình
        swipe.addAction(finger.createPointerUp(0));
        //DriverManager.perform(): Thực hiện chuỗi hành động được định nghĩa trong Sequence.
        DriverManager.getDriver().perform(Collections.singletonList(swipe));
    }

    //Scroll từ dưới lên trên (Dùng Getsure) => Chỉ aáp dụng cho Native Apps
    public static void scrollGestureCommand() {
        // Scroll gesture cho Android
        /*
            Cách xác định left , top , width , heìght :
                B1 : Dùng Appium Inspector để xác định toạ độ
                B2 : Sau khi record ra được toạ độ :
                    var start = new Point(563, 1729);
                    var end = new Point (547, 1072);
                B3 : Áp dụng công thức để tính thành hình chữ nhật :
                    left : 547
                    top : 1072
                    width : 563 - 547 = 16
                    height : 1729 - 1072 = 657
        */
        sleep(STEP_ACTION_TIMEOUT);
        Map<String, Object> scrollParams = new HashMap<>();
        scrollParams.put("left", 547); //vị trí mép trái vùng cuộn cách mép trái màn hình
        scrollParams.put("top", 1072); //xác định mép trên của vùng cuộn
        scrollParams.put("width", 16); //chiều ngang của vùng kéo
        scrollParams.put("height", 657); //chiều dài của vùng kéo
        scrollParams.put("direction", "down"); //Scroll theo chiều từ trên xuống dưới (up, down, left, right)
        scrollParams.put("percent", 1); //Scroll 100% của vùng kéo được chỉ định (width, height)

        LogUtils.info("[MobileUI] Executing scrollGesture command with params: " + scrollParams);
        // Thực hiện scroll gesture
        DriverManager.getDriver().executeScript("mobile: scrollGesture", scrollParams);
    }

    //Scroll qua bên trái
    public void scrollLeft() {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing scrollLeft.");
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        //int startY = size.height / 2; // Ở chính giữa màn hình
        int startY = (int) (size.height * 0.3); // 1/3 bên trên màn hình
        int endX = (int) (size.width * 0.2);
        int endY = startY; // Giữ nguyên độ cao
        int duration = 200;

        scroll(startX, startY, endX, endY, duration);
    }

    //Scroll qua bên phải
    public void scrollRight() {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing scrollRight.");
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        //int startY = size.height / 2; // Ở chính giữa màn hình
        int startY = (int) (size.height * 0.3); // 1/3 bên trên màn hình
        int endX = (int) (size.width * 0.8);
        int endY = startY; // Giữ nguyên độ cao
        int duration = 200;

        scroll(startX, startY, endX, endY, duration);
    }

    //Khai báo Point để chia màn hình làm 1/2
    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(
                location.getX() + size.getWidth() / 2 ,
                location.getY() + size.getHeight() / 2
        );
    }

    //Tap vào element
    public void tapElement(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing tap on element: " + element);
        //Lấy tọa độ của phần tử trên màn hình
        Point locaiton = element.getLocation();
        //Lấy kích thước màn hình
        Dimension size = element.getSize();
        //Áp dụng công thức ở hàm "getCenterOfElement" để lấy được vị trí chính giữa màn hình
        Point centerOfElement = getCenterOfElement(locaiton,size);
        //PointerInput: Đại diện cho một thiết bị đầu vào, trong trường hợp này là ngón tay (PointerInput.Kind.TOUCH)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        //Sequence: Tập hợp các hành động được thực hiện bởi một PointerInput (1 ngón tay)
        Sequence sequence = new Sequence(finger,1)
                //createPointerMove(): Di chuyển con trỏ đến vị trí của phần tử
                //PointerInput.Origin.viewport() xác định hệ tọa độ dựa trên viewport (khung nhìn) của ứng dụng.
                .addAction(finger.createPointerMove(Duration.ZERO , PointerInput.Origin.viewport(), centerOfElement))
                //createPointerDown(): Mô phỏng việc chạm vào màn hình.
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                //Cho ngón tay dừng tầm 0.5s
                .addAction(new Pause(finger,Duration.ofMillis(500)))
                //createPointerUp(): Mô phỏng việc nhấc ngón tay khỏi màn hình
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        //DriverManager.perform(): Thực hiện chuỗi hành động được định nghĩa trong Sequence.
        DriverManager.getDriver().perform(Collections.singletonList(sequence));
    }

    //Tap vào toạ độ
    public void tapToaDo(int x, int y) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing tap at coordinates (" + x + "," + y + ") with 200ms pause.");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        //Cho ngón tay dừng tầm 0.2s
        tap.addAction(new Pause(finger, Duration.ofMillis(200)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(tap));
    }

    //Tap vào toạ độ với thời gian chỉ định
    public void tapToaDoTime(int x, int y, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing tap at coordinates (" + x + "," + y + ") with pause " + second + "ms.");
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        //Chạm vào với thời gian chỉ định
        tap.addAction(new Pause(finger, Duration.ofMillis(second)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        DriverManager.getDriver().perform(Arrays.asList(tap));
    }

    //Hàm phóng to / thu nhỏ
    public void zoom(WebElement element, double scale) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Executing zoom on element: " + element + " with approximate scale factor: " + scale + " (Note: Implementation may need review for accurate scaling)");
        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;
        int distance = 100; // Khoảng cách giữa hai ngón tay

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        Sequence zoom = new Sequence(finger1, 1);
        zoom.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX - distance, centerY));
        zoom.addAction(finger1.createPointerDown(0));

        Sequence zoom2 = new Sequence(finger2, 1);
        zoom2.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX + distance, centerY));
        zoom2.addAction(finger2.createPointerDown(0));

        if (scale > 1) { // Phóng to
            for (int i = 0; i < 10; i++) {
                LogUtils.info("[MobileUI] Zooming In");
                zoom.addAction(finger1.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), centerX - distance / 2, centerY));
                zoom2.addAction(finger2.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), centerX + distance / 2, centerY));
            }
        } else { // Thu nhỏ
            for (int i = 0; i < 10; i++) {
                LogUtils.info("[MobileUI] Zooming Out");
                zoom.addAction(finger1.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), centerX - distance * 2, centerY));
                zoom2.addAction(finger2.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), centerX + distance * 2, centerY));
            }
        }

        zoom.addAction(finger1.createPointerUp(0));
        zoom2.addAction(finger2.createPointerUp(0));

        DriverManager.getDriver().perform(Arrays.asList(zoom, zoom2));
    }

    // ------------------------------------------- Wait Methods -----------------------------------------------------------

    //1. Chờ phần tử có thể click
    //Chờ phần tử có thể click (Dùng By locator) => Có set timeout
    public static WebElement waitForElementToBeClickable_UseByLocator_setTimeout(By locator , int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be clickable: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Chờ phần tử có thể click (Dùng By locator) => Không set timeout
    public static WebElement waitForElementToBeClickable_UseByLocator_NoSetTimeout(By locator) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be clickable: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Chờ phần tử có thể click (Dùng WebElement) => Có set timeout
    public static WebElement waitForElementToBeClickable_UseWebElement_setTimeout(WebElement element , int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be clickable: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //Chờ phần tử có thể click (Dùng WebElement) => Không set timeout
    public static WebElement waitForElementToBeClickable_UseWebElement_NoSetTimeout(WebElement element) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be clickable: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //-----------------------------------------------------

    //2. Chờ phần tử xuất hiện trên màn hình

    //Chờ phần tử xuất hiện trên màn hình (Dùng By locator) => Có set timeout
    public static WebElement waitForElementVisible_UseByLocator_setTimeout(By locator , int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be visible: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Chờ phần tử xuất hiện trên màn hình (Dùng By locator) => Không set timeout
    public static WebElement waitForElementVisible_UseByLocator_NoSetTimeout(By locator) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be visible: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Chờ phần tử xuất hiện trên màn hình (Dùng WebElement) => Có set timeout
    public static WebElement waitForElementVisible_UseWebElement_setTimeout(WebElement element , int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be visible: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //Chờ phần tử xuất hiện trên màn hình (Dùng WebElement) => Không set timeout
    public static WebElement waitForElementVisible_UseWebElement_NoSetTimeout(WebElement element) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be visible: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //-----------------------------------------------------

    //3. Chờ phần tử không còn hiển thị trên màn hình

    //Chờ phần tử không còn hiển thị trên màn hình (Dùng By locator) => Trả về true/false => Có set timeout
    public static boolean waitForElementInvisibe_Boolean_UseByLocator_setTimeout(By locator, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be invisible: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //Chờ phần tử không còn hiển thị trên màn hình (Dùng By locator) => Trả về true/false => Không set timeout
    public static boolean waitForElementInvisibe_Boolean_UseByLocator_NoSetTimeout(By locator) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be invisible: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    //Chờ phần tử không còn hiển thị trên màn hình (Dùng WebElement) => Trả về true/false => Có set timeout
    public static boolean waitForElementInvisibe_Boolean_UseWebElement_setTimeout(WebElement element, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be invisible: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //Chờ phần tử không còn hiển thị trên màn hình (Dùng WebElement) => Trả về true/false => Không set timeout
    public static boolean waitForElementInvisibe_Boolean_UseWebElement_NoSetTimeout(WebElement element) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be invisible: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    //-----------------------------------------------------

    //4. Chờ đến khi phần tử xuất hiện trong DOM (Document Object Model)"
    // — chứ không nhất thiết là đã hiển thị (visible) trên giao diện.

    //Chờ phần tử xuất hiện trong DOM (Dùng By locator) => Có set timeout
    public static WebElement waitForElementPresent_UseByLocator_setTimeout(By locator, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for element to be present in DOM: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    //Chờ phần tử xuất hiện trong DOM (Dùng By locator) => Không set timeout
    public static WebElement waitForElementPresent_UseByLocator_NoSetTimeout(By locator) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for element to be present in DOM: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    //-----------------------------------------------------

    //5. Chờ đến khi một đoạn text cụ thể xuất hiện trong DOM (và thường là hiển thị trên UI).

    //Chờ đến khi một đoạn text cụ thể xuất hiện trong DOM (Dùng By locator) => Có set timeout
    public static boolean waitForTextToBePresent_Boolean_UseByLocator_setTimeout(By locator, String text, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for text '" + text + "' to be present in element: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    //Chờ đến khi một đoạn text cụ thể xuất hiện trong DOM (Dùng By locator) => Không set timeout
    public static boolean waitForTextToBePresent_Boolean_UseByLocator_NoSetTimeout(By locator, String text) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for text '" + text + "' to be present in element: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    //Chờ đến khi một đoạn text cụ thể xuất hiện trong DOM (Dùng WebElement) => Có set timeout
    public static boolean waitForTextToBePresent_Boolean_UseWebElement_setTimeout(WebElement element, String text, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for text '" + text + "' to be present in element: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    //Chờ đến khi một đoạn text cụ thể xuất hiện trong DOM (Dùng WebElement) => Không set timeout
    public static boolean waitForTextToBePresent_Boolean_UseWebElement_NoSetTimeout(WebElement element, String text) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for text '" + text + "' to be present in element: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    //-----------------------------------------------------

    //6. Chờ đến khi một thuộc tính HTML của phần tử có giá trị BẰNG với giá trị bạn mong muốn

    //Chờ đến khi một thuộc tính HTML của phần tử có giá trị BẰNG với giá trị bạn mong muốn (Dùng By locator) => Có set timeout
    public static boolean waitForAttributeToBe_Boolean_UseByLocator_setTimeout(By locator, String attribute, String value, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for attribute '" + attribute + "' to be '" + value + "' in element: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    //Chờ đến khi một thuộc tính HTML của phần tử có giá trị BẰNG với giá trị bạn mong muốn (Dùng By locator) => Không set timeout
    public static boolean waitForAttributeToBe_Boolean_UseByLocator_NoSetTimeout(By locator, String attribute, String value) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for attribute '" + attribute + "' to be '" + value + "' in element: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.attributeToBe(locator, attribute, value));
    }

    //Chờ đến khi một thuộc tính HTML của phần tử có giá trị BẰNG với giá trị bạn mong muốn (Dùng WebElement) => Có set timeout
    public static boolean waitForAttributeToBe_Boolean_UseWebElement_setTimeout(WebElement element, String attribute, String value, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for attribute '" + attribute + "' to be '" + value + "' in element: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    //Chờ đến khi một thuộc tính HTML của phần tử có giá trị BẰNG với giá trị bạn mong muốn (Dùng WebElement) => Không set timeout
    public static boolean waitForAttributeToBe_Boolean_UseWebElement_NoSetTimeout(WebElement element, String attribute, String value) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for attribute '" + attribute + "' to be '" + value + "' in element: " + element);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    //-----------------------------------------------------

    //7. Chờ đến khi số lượng phần tử (elements) trên trang đạt đúng con số mong muốn
    // – ví dụ như chờ danh sách sản phẩm, kết quả tìm kiếm, hàng trong bảng… được tải xong.

    //Chờ đến khi số lượng phần tử (elements) trên trang đạt đúng con số mong muốn (Dùng By locator) => Có set timeout
    public static List<WebElement> waitForNumberOfElements_UseByLocator_setTimeout(By locator, int expectedCount, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for number of elements to be " + expectedCount + " for locator: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
    }

    //Chờ đến khi số lượng phần tử (elements) trên trang đạt đúng con số mong muốn (Dùng By locator) => Không set timeout
    public static List<WebElement> waitForNumberOfElements_UseByLocator_NoSetTimeout(By locator, int expectedCount) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for number of elements to be " + expectedCount + " for locator: " + locator);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.numberOfElementsToBe(locator, expectedCount));
    }

    //-----------------------------------------------------

    //8. Chờ đến khi URL hiện tại của trình duyệt chứa một đoạn chuỗi cụ thể
    // VD :  Chờ tối đa 10 giây cho đến khi URL hiện tại chứa /dashboard

    public static boolean waitForUrlContains_UseByLocator_setTimeout(String text, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for URL to contain: '" + text + "'");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.urlContains(text));
    }

    public static boolean waitForUrlContains_UseByLocator_NoSetTimeout(String text) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for URL to contain: '" + text + "'");
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.urlContains(text));
    }

    //-----------------------------------------------------

    //9. Chờ đến khi URL hiện tại của trình duyệt chứa một đoạn chuỗi cụ thể.
    // (*) Thường áp dụng cho Web nhưng note vô luôn

    public static boolean waitForNumberOfWindows(int expectedWindows, int timeout) {
        LogUtils.info("[MobileUI] Waiting up to " + timeout + "s for number of windows to be: " + expectedWindows);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindows));
    }

    public static boolean waitForNumberOfWindows(int expectedWindows) {
        LogUtils.info("[MobileUI] Waiting up to " + DEFAULT_TIMEOUT + "s (default) for number of windows to be: " + expectedWindows);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedWindows));
    }


    // ------------------------------------------- Hàm click dùng chung -----------------------------------------------------------
    //Set thời gian trước khi click => (Dùng By locator)
    public static void clickElement_UseByLocator_setTimeout(By locator , int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clicking element located by: " + locator + " within " + second + "s.");
        waitForElementToBeClickable_UseByLocator_setTimeout(locator, second).click();
    }

    //Click không cần set thời gian => (Dùng By locator)
    public static void clickElement_UseByLocator_NoSetTimeout(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clicking element located by: " + locator + " within default timeout (" + DEFAULT_TIMEOUT + "s).");
        waitForElementToBeClickable_UseByLocator_NoSetTimeout(locator).click();
    }

    //Set thời gian trước khi click => (Dùng By WebElement)
    public static void clickElement_UseWebElement_setTimeout(WebElement element, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clicking element: " + element + " within " + second + "s.");
        waitForElementToBeClickable_UseWebElement_setTimeout(element, second).click();
    }

    //Click không cần set thời gian => (Dùng By WebElement)
    public static void clickElement_UseWebElement_NoSetTimeout(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clicking element: " + element + " within default timeout (" + DEFAULT_TIMEOUT + "s).");
        waitForElementToBeClickable_UseWebElement_NoSetTimeout(element).click();
    }

    // ------------------------------------------- Hàm nhập text vào ô input -----------------------------------------------------------
    //Hàm nhập text vào ô input (Dùng By locator) => Có set timeout
    public static void setText_UseByLocator_setTimeout(By locator, String text, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Setting text '" + text + "' on element located by: " + locator + " with timeout " + second + "s.");
        WebElement element = waitForElementVisible_UseByLocator_setTimeout(locator, second);
        element.click();
        element.clear();
        element.sendKeys(text);
        LogUtils.info("[MobileUI] Set text completed for locator: " + locator);
    }

    //Hàm nhập text vào ô input (Dùng By locator) => Không set timeout
    public static void setText_UseByLocator_NoSetTimeout(By locator, String text) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Setting text '" + text + "' on element located by: " + locator + " with default timeout.");
        WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator);
        element.click(); // Often needed before clear/sendKeys
        element.clear();
        element.sendKeys(text);
        LogUtils.info("[MobileUI] Set text completed for locator: " + locator);
    }

    //Hàm nhập text vào ô input (Dùng WebElement) => Có set timeout
    public static void setText_UseWebElement_setTimeout(WebElement element, String text, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Setting text '" + text + "' on element: " + element + " with timeout " + second + "s.");
        WebElement elm = waitForElementVisible_UseWebElement_setTimeout(element, second);
        elm.click();
        elm.clear();
        elm.sendKeys(text);
        LogUtils.info("[MobileUI] Set text completed for element: " + element);
    }

    //Hàm nhập text vào ô input (Dùng WebElement) => Không set timeout
    public static void setText_UseWebElement_NoSetTimeout(WebElement element, String text) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Setting text '" + text + "' on element: " + element + " with default timeout.");
        WebElement elm = waitForElementVisible_UseWebElement_NoSetTimeout(element);
        elm.click();
        elm.clear();
        elm.sendKeys(text);
        LogUtils.info("[MobileUI] Set text completed for element: " + element);

    }

    // ------------------------------------------- Hàm xoá sạch dữ liệu trong ô input -----------------------------------------------------------
    //Hàm xoá sạch dữ liệu trong ô input (Dùng By locator) => Có set timeout
    public static void clearText_UseByLocator_setTimeout(By locator, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clearing text on element located by: " + locator + " with timeout " + second + "s.");
        WebElement element = waitForElementVisible_UseByLocator_setTimeout(locator, second);
        element.click();
        element.clear();
        LogUtils.info("[MobileUI] Clear text completed for locator: " + locator);
    }

    //Hàm xoá sạch dữ liệu trong ô input (Dùng By locator) => Không set timeout
    public static void clearText_UseByLocator_NoSetTimeout(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clearing text on element located by: " + locator + " with default timeout.");
        WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator);
        element.click();
        element.clear();
        LogUtils.info("[MobileUI] Clear text completed for locator: " + locator);
    }

    //Hàm xoá sạch dữ liệu trong ô input (Dùng By WebElement) => Có set timeout
    public static void clearText_UseWebElement_setTimeout(WebElement element, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clearing text on element: " + element + " with timeout " + second + "s.");
        WebElement elm = waitForElementVisible_UseWebElement_setTimeout(element, second);
        elm.click();
        elm.clear();
        LogUtils.info("[MobileUI] Clear text completed for element: " + element);
    }

    //Hàm xoá sạch dữ liệu trong ô input (Dùng By WebElement) => Không set timeout
    public static void clearText_UseWebElement_NoSetTimeout(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Clearing text on element: " + element + " with default timeout.");
        WebElement elm = waitForElementVisible_UseWebElement_NoSetTimeout(element);
        elm.click();
        elm.clear();
        LogUtils.info("[MobileUI] Clear text completed for element: " + element);
    }

    // ------------------------------------ Hàm hiển thị một đoạn text cụ thể-----------------------------------------------------------
    //Hàm hiển thị một đoạn text cụ thể (Dùng By locator) => Có set timeout
    public static String getElementText_UseByLocator_setTimeout(By locator, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Getting text from element located by: " + locator + " with timeout " + second + "s.");
        WebElement element = waitForElementVisible_UseByLocator_setTimeout(locator, second);
        String text = element.getText();
        LogUtils.info("[MobileUI] Retrieved text: '" + text + "'");
        return text;
    }

    //Hàm hiển thị một đoạn text cụ thể (Dùng By locator) => Không set timeout
    public static String getElementText_UseByLocator_NoSetTimeout(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Getting text from element located by: " + locator + " with default timeout.");
        WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator);
        String text = element.getText();
        LogUtils.info("[MobileUI] Retrieved text: '" + text + "'");
        return text;
    }

    //Hàm hiển thị một đoạn text cụ thể (Dùng WebElement) => Có set timeout
    public static String getElementText_UseWebElement_setTimeout(WebElement element, int second) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Getting text from element: " + element + " with timeout " + second + "s.");
        WebElement elm = waitForElementVisible_UseWebElement_setTimeout(element, second);
        String text = elm.getText();
        LogUtils.info("[MobileUI] Retrieved text: '" + text + "'");
        return text;
    }

    //Hàm hiển thị một đoạn text cụ thể (Dùng WebElement) => Không set timeout
    public static String getElementText_UseWebElement_NoSetTimeout(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Getting text from element: " + element + " with default timeout.");
        WebElement elm = waitForElementVisible_UseWebElement_NoSetTimeout(element);
        String text = elm.getText();
        LogUtils.info("[MobileUI] Retrieved text: '" + text + "'");
        return text;
    }

    // ------------------------------------- Hàm lấy giá trị của một thuộc tính (attribute) cụ thể -----------------------------------------------------------
    //Hàm lấy giá trị của một thuộc tính (attribute) cụ thể (Dùng By locator) => Có set timeout
    public static String getElementAttribute_UseByLocator_setTimeout(By locator, String attribute, int second) {
        LogUtils.info("[MobileUI] Getting attribute '" + attribute + "' from element located by: " + locator + " with timeout " + second + "s.");
        WebElement element = waitForElementVisible_UseByLocator_setTimeout(locator, second);
        String value = element.getAttribute(attribute);
        LogUtils.info("[MobileUI] Retrieved attribute value: '" + value + "'");
        return value;
    }

    //Hàm lấy giá trị của một thuộc tính (attribute) cụ thể (Dùng By locator) => Không set timeout
    public static String getElementAttribute_UseByLocator_NoSetTimeout(By locator, String attribute) {
        LogUtils.info("[MobileUI] Getting attribute '" + attribute + "' from element located by: " + locator + " with default timeout.");
        WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator);
        String value = element.getAttribute(attribute);
        LogUtils.info("[MobileUI] Retrieved attribute value: '" + value + "'");
        return value;
    }

    //Hàm lấy giá trị của một thuộc tính (attribute) cụ thể (Dùng WebElement) => Có set timeout
    public static String getElementAttribute_UseWebElement_setTimeout(WebElement element, String attribute, int second) {
        LogUtils.info("[MobileUI] Getting attribute '" + attribute + "' from element: " + element + " with timeout " + second + "s.");
        WebElement elm = waitForElementVisible_UseWebElement_setTimeout(element, second);
        String value = elm.getAttribute(attribute);
        LogUtils.info("[MobileUI] Retrieved attribute value: '" + value + "'");
        return value;
    }

    //Hàm lấy giá trị của một thuộc tính (attribute) cụ thể (Dùng WebElement) => Không set timeout
    public static String getElementAttribute_UseWebElement_NoSetTimeout(WebElement element, String attribute) {
        LogUtils.info("[MobileUI] Getting attribute '" + attribute + "' from element: " + element + " with default timeout.");
        WebElement elm = waitForElementVisible_UseWebElement_NoSetTimeout(element);
        String value = elm.getAttribute(attribute);
        LogUtils.info("[MobileUI] Retrieved attribute value: '" + value + "'");
        return value;
    }

    // ---------------------------- Hàm kiểm tra xem một phần tử vừa tồn tại trong DOM (present), vừa hiển thị trên giao diện (displayed) -----------------------------------------------------------
    //Hàm kiểm tra xem một phần tử vừa tồn tại trong DOM (present), vừa hiển thị trên giao diện (displayed) => (Dùng By locator)
    public static boolean isElementPresentAndDisplayed_UseByLocator(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Checking if element is present and displayed: " + locator);
        boolean result;
        try {
            WebElement element = DriverManager.getDriver().findElement(locator); // Find first, then check display
            result = element != null && element.isDisplayed();
            LogUtils.info("[MobileUI] Element present and displayed check result: " + result + " for locator: " + locator);
            return result;
        } catch (NoSuchElementException e) {
            LogUtils.info("[MobileUI] Element not found during presence/display check: " + locator + " - " + e.getMessage());
            return false;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking presence/display for locator: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    //Hàm kiểm tra xem một phần tử vừa tồn tại trong DOM (present), vừa hiển thị trên giao diện (displayed) => (Dùng WebElement)
    public static boolean isElementPresentAndDisplayed_UseWebElement(WebElement element) {
        LogUtils.info("[MobileUI] Checking if element is present and displayed: " + element);
        boolean result;
        try {
            result = element != null && element.isDisplayed();
            LogUtils.info("[MobileUI] Element present and displayed check result: " + result);
            return result;
        } catch (NoSuchElementException e) {
            LogUtils.info("[MobileUI] Element not found during presence/display check: " + element + " - " + e.getMessage());
            return false;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking presence/display for element: " + element + " - " + e.getMessage());
            return false;
        }
    }


    // ---------------------- Hàm kểm tra xem một phần tử (element) có đang được (enabled) hay không -----------------------------------------------------------
    //Hàm kểm tra xem một phần tử (element) có đang được (enabled) hay không  => (Dùng By locator)
    public static boolean isElementEnabled_UseByLocator(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Checking if element is enabled: " + locator);
        boolean result;
        try {
            WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator); // Ensure it's visible before checking enabled
            result = element != null && element.isEnabled();
            LogUtils.info("[MobileUI] Element enabled check result: " + result + " for locator: " + locator);
            return result;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking enabled status for locator: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    //Hàm kểm tra xem một phần tử (element) có đang được (enabled) hay không  => (Dùng WebElement)
    public static boolean isElementEnabled_UseWebElement(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Checking if element is enabled: " + element);
        boolean result;
        try {
            result = element != null && element.isEnabled();
            LogUtils.info("[MobileUI] Element enabled check result: " + result);
            return result;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking enabled status for element: " + element + " - " + e.getMessage());
            return false;
        }
    }
    // ----------------------- Hàm kiểm tra xem một phần tử có đang được chọn hay không -----------------------------------------------------------
    //Hàm kiểm tra xem một phần tử có đang được chọn hay không => (Dùng By locator)
    public static boolean isElementSelected_UseByLocator(By locator) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Checking if element is selected: " + locator);
        boolean result;
        try {
            WebElement element = waitForElementVisible_UseByLocator_NoSetTimeout(locator); // Ensure it's visible before checking selected
            result = element != null && element.isSelected();
            LogUtils.info("[MobileUI] Element selected check result: " + result + " for locator: " + locator);
            return result;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking selected status for locator: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    //Hàm kiểm tra xem một phần tử có đang được chọn hay không => (Dùng WebElement)
    public static boolean isElementSelected_UseWebElement(WebElement element) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Checking if element is selected: " + element);
        boolean result;
        try {
            result = element != null && element.isSelected();
            LogUtils.info("[MobileUI] Element selected check result: " + result);
            return result;
        } catch (Exception e) {
            LogUtils.info("[MobileUI] An error occurred checking selected status for element: " + element + " - " + e.getMessage());
            return false;
        }
    }

    // -----------------------------------Các hàm verify (sử dụng Assert và gọi lại các hàm is) -----------------------------------------------------------

    //Verìy phần tử có tồn tại trong DOM và đang hiển thị trên giao diện hay không => (Dùng By locator)
    public static void verifyElementPresentAndDisplayed_UseByLocator(By locator, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is present and displayed: " + locator + ". Message if failed: " + message);
        Assert.assertTrue(isElementPresentAndDisplayed_UseByLocator(locator), message);
    }

    //Verìy phần tử có tồn tại trong DOM và đang hiển thị trên giao diện hay không => (Dùng WebElement)
    public static void verifyElementPresentAndDisplayed_UseWebElement(WebElement element, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is present and displayed: " + element + ". Message if failed: " + message);
        Assert.assertTrue(isElementPresentAndDisplayed_UseWebElement(element), message);
    }

    //-----------------------------------------------------

    //Verìy phần tử (element) có đang được (enabled) hay không  => (Dùng By locator)
    public static void verifyElementEnabled_UseByLocator(By locator, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is enabled: " + locator + ". Message if failed: " + message);
        Assert.assertTrue(isElementEnabled_UseByLocator(locator), message);
    }

    //Verìy phần tử (element) có đang được (enabled) hay không  => (Dùng WebElement)
    public static void verifyElementEnabled_UseWebElement(WebElement element, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is enabled: " + element + ". Message if failed: " + message);
        Assert.assertTrue(isElementEnabled_UseWebElement(element), message);
    }

    //-----------------------------------------------------

    //Verìy phần tử có đang được chọn hay không => (Dùng By locator)
    public static void verifyElementSelected_UseByLocator(By locator, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is selected: " + locator + ". Message if failed: " + message);
        Assert.assertTrue(isElementSelected_UseByLocator(locator), message);
    }

    //Verìy phần tử có đang được chọn hay không => (Dùng WebElement)
    public static void verifyElementSelected_UseWebElement(WebElement element, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying element is selected: " + element + ". Message if failed: " + message);
        Assert.assertTrue(isElementSelected_UseWebElement(element), message);
    }

    //-----------------------------------------------------

    //Verìy hiển thị một đoạn text cụ thể (Dùng By locator)
    public static void verifyElementText_UseByLocator(By locator, String expectedText, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying text of element: " + locator + " equals '" + expectedText + "'. Message if failed: " + message);
        Assert.assertEquals(getElementText_UseByLocator_NoSetTimeout(locator), expectedText, message);
    }

    //Verìy hiển thị một đoạn text cụ thể (Dùng WebElement)
    public static void verifyElementText_UseWebElement(WebElement element, String expectedText, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying text of element: " + element + " equals '" + expectedText + "'. Message if failed: " + message);
        Assert.assertEquals(getElementText_UseWebElement_NoSetTimeout(element), expectedText, message);
    }

    //-----------------------------------------------------

    //Verìy giá trị của một thuộc tính (attribute) cụ thể (Dùng By locator)
    public static void verifyElementAttribute_UseByLocator(By locator, String attribute, String expectedValue, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying attribute '" + attribute + "' of element: " + locator + " equals '" + expectedValue + "'. Message if failed: " + message);
        Assert.assertEquals(getElementAttribute_UseByLocator_NoSetTimeout(locator, attribute), expectedValue, message);
    }

    //Verìy giá trị của một thuộc tính (attribute) cụ thể (Dùng WebElement)
    public static void verifyElementAttribute_UseWebElement(WebElement element, String attribute, String expectedValue, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Verifying attribute '" + attribute + "' of element: " + element + " equals '" + expectedValue + "'. Message if failed: " + message);
        Assert.assertEquals(getElementAttribute_UseWebElement_NoSetTimeout(element, attribute), expectedValue, message);
    }

    //-----------------------------------------------------

    //Verìy lệnh kiểm tra điều kiện đúng (true), nếu sai thì test sẽ fail với thông báo truyền vào
    public static void assertTrueCondition(boolean condition, String message) {
        sleep(STEP_ACTION_TIMEOUT);
        LogUtils.info("[MobileUI] Asserting condition: " + condition + ". Message if failed: " + message);
        Assert.assertTrue(condition, message);
        LogUtils.info("[MobileUI] Assertion passed for condition: " + condition);
    }
}
