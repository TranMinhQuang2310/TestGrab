package com.quangtester.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Dùng để áp dụng ghi log cho file "log4j2.properties"
public class LogUtils {
    //Initialize Log4j instance
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    //Info Level Logs => Nghĩa là thao tác gì trên UI sẽ log ra
    public static void info(String message) {
        logger.info(message);
    }

    public static void info(Object object) {
        logger.info(object);
    }

    //Warn Level Logs => Nghĩa là trong hàm có tính toán gì đó nếu thấy sai sẽ log ra cảnh báo
    public static void warn(String message) {
        logger.warn(message);
    }

    public static void warn(Object object) {
        logger.warn(object);
    }

    //Error Level Logs  => Nghĩa là trong hàm có lỗi gì đó thì sẽ ghi ra log
    public static void error(String message) {
        logger.error(message);
    }

    public static void error(Object object) {
        logger.error(object);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        logger.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(Object object) {
        logger.debug(object);
    }
}
