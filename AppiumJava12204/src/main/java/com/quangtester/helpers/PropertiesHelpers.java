package com.quangtester.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelpers {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    //Khai báo đường lẫn lấy dữ liệu từ file Json
    private static String relPropertiesFilePathDefault = "src/test/resources/configs/config.properties";

    //Hàm loadAllFiles() => khởi tạo giá trị cho nhiều file properties setup sẵn trong hàm
    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add tất cả file Properties vào đây theo mẫu
        files.add("src/test/resources/configs/config.properties");
        // files.add("src/test/resources/configs/local.properties");
        // files.add("src/test/resources/configs/production.properties");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelpers.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    //Đọc file theo đường dẫn file configs.properties được truyền vào
    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelpers.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Đọc file theo đường dẫn file configs.properties được khai báo cứng
    public static void setDefaultFile() {
        properties = new Properties();
        try {
            linkFile = SystemHelpers.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Hàm getValue() để đọc file đã setup bên trên và lấy giá trị ra theo Key trong file đã tạo
    public static String getValue(String key) {
        String value = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemHelpers.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            value = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return value;
    }

    //Hàm setValue() để gán ngược giá trị với key tương ứng vào lại file properties trên.
    public static void setValue(String key, String keyValue) {
        try {
            if (file == null) {
                properties = new Properties();
                file = new FileInputStream(SystemHelpers.getCurrentDir() + relPropertiesFilePathDefault);
                properties.load(file);
                file.close();
                out = new FileOutputStream(SystemHelpers.getCurrentDir() + relPropertiesFilePathDefault);
            }
            //Ghi vào cùng file Prop với file lấy ra
            out = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setValue(String filePropertiesRelativePath, String key, String keyValue) {
        try {

            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(SystemHelpers.getCurrentDir() + filePropertiesRelativePath);
            properties.load(file);
            file.close();

            properties.setProperty(key, keyValue);
            FileOutputStream out = new FileOutputStream(SystemHelpers.getCurrentDir() + filePropertiesRelativePath);
            properties.store(out, null);
            out.close();
            System.out.println("Set value '" + keyValue + "' to file " + filePropertiesRelativePath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeKey(String filePropertiesRelativePath, String key) {
        try {
            // Đọc file properties
            Properties properties = new Properties();
            File file = new File(SystemHelpers.getCurrentDir() + filePropertiesRelativePath);
            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();

            // Xoá key nếu tồn tại
            if (properties.containsKey(key)) {
                properties.remove(key);
                System.out.println("🔑 Đã xoá key: " + key);
            } else {
                System.out.println("⚠️ Key không tồn tại: " + key);
            }

            // Ghi lại file
            FileOutputStream outputStream = new FileOutputStream(file);
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
