package com.quangtester.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

//Chức năng : Export file excel
//Ghi chú : Giải thích từng file có note trong file ghi chú của bài 23
public class ExcelHelpers {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public ExcelHelpers() {
    }

    //Set Excel File
    public void setExcelFile(String excelPath, String sheetName) {
        System.out.println("Set Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);

        try {
            File f = new File(excelPath);

            //Nếu file không tồn tại
            if (!f.exists()) {
                try {
                    System.out.println("File Excel path not found.");
                    throw new FileNotFoundException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //Nếu sheet không tồn tại trong file
            if (sheetName.isEmpty()) {
                try {
                    System.out.println("The Sheet Name is empty.");
                    throw new FileNotFoundException("The Sheet Name is empty.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                //sh = wb.createSheet(sheetName);
                try {
                    System.out.println("Sheet name not found.");
                    throw new RuntimeException("Sheet name not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            excelFilePath = excelPath;

            //adding all the column header names to the map 'columns'
            //Lấy dòng đầu tiên trong sheet duyệt qua từng hàng
            sheet.getRow(0).forEach(cell -> {
                //Đẩy data vào từng hàng
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            e.getMessage();
            System.out.println(e.getMessage());
        }
    }

    //This method takes the row number as a parameter and returns the data for that row.
    public Row getRowData(int rowNum) {
        row = sheet.getRow(rowNum);
        return row;
    }

    //Get Excel data from the sheet => nghĩa là đọc hết data trong một Sheet của file Excel
    public Object[][] getExcelData(String excelPath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;

        System.out.println("Set Excel file " + excelPath);
        System.out.println("Selected Sheet: " + sheetName);

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    System.out.println("File Excel path not found.");
                    throw new FileNotFoundException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sheetName.isEmpty()) {
                try {
                    System.out.println("The Sheet Name is empty.");
                    throw new FileNotFoundException("The Sheet Name is empty.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // load the file
            FileInputStream fis = new FileInputStream(excelPath);

            // load the workbook
            workbook = new XSSFWorkbook(fis);
            // load the sheet
            Sheet sheet = workbook.getSheet(sheetName);
            // load the row
            Row row = sheet.getRow(0);

            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            System.out.println("Row: " + (noOfRows - 1) + " - Column: " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];

            //FOR loop runs from 1 to drop header line (headline is 0)
            //noOfRows => Duyệt qua từng dòng
            //noOfCols => Duyệt qua từng cột
            //i = 1 => bắt đầu từ dòng thứ 2
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);

                    //This is used to determine the data type from cells in Excel and then convert it to String for ease of reading
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = "";
                            break;
                        default:
                            data[i - 1][j] = null;
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.getMessage();
            throw new RuntimeException(e);
        }
        return data;
    }

    //Đọc data theo vị trí dòng được set (bắt đầu - kết thúc) chỉ định trong 1 sheet
    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        System.out.println("Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);

        Object[][] data = null;

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    System.out.println("File Excel path not found.");
                    throw new RuntimeException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int rows = getRows();
            int columns = getColumns();

            System.out.println("Row: " + rows + " - Column: " + columns);
            System.out.println("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Hashtable<String, String> table = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                table = new Hashtable<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    table.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = table;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return data;

    }

    // Get data from specific rows => Đọc data theo vị trí dòng được chọn trong 1 sheet (Cách 1)
    public Object[][] getDataFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        System.out.println("Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);
        System.out.println("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                System.out.println("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();
            System.out.println("Column count: " + columns);

            // Khởi tạo mảng data với kích thước bằng số lượng dòng được chỉ định
            data = new Object[rowNumbers.length][columns];

            // Đọc dữ liệu từ các dòng được chỉ định
            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];
                // Kiểm tra xem dòng có tồn tại không
                if (rowNum > sheet.getLastRowNum()) {
                    System.out.println("WARNING: Row " + rowNum + " does not exist in the sheet.");
                    // Gán giá trị rỗng cho dòng không tồn tại
                    for (int j = 0; j < columns; j++) {
                        data[i][j] = "";
                    }
                    continue;
                }

                for (int j = 0; j < columns; j++) {
                    data[i][j] = getCellData(rowNum, j);
                }
            }

            // Đóng workbook và FileInputStream
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Exception in getDataFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    // Get data from specific rows with hashtable => Đọc data theo vị trí dòng được chọn trong 1 sheet (Cách 2)
    public Object[][] getDataHashTableFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        System.out.println("Excel File: " + excelPath);
        System.out.println("Sheet Name: " + sheetName);
        System.out.println("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                System.out.println("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.out.println("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();
            // Khởi tạo mảng data với kích thước bằng số lượng dòng được chỉ định
            data = new Object[rowNumbers.length][1];

            // Đọc dữ liệu từ các dòng được chỉ định
            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];
                // Kiểm tra xem dòng có tồn tại không
                if (rowNum > sheet.getLastRowNum()) {
                    System.out.println("WARNING: Row " + rowNum + " does not exist in the sheet.");
                    data[i][0] = new Hashtable<String, String>();
                    continue;
                }

                Hashtable<String, String> table = new Hashtable<>();
                for (int j = 0; j < columns; j++) {
                    // Lấy tên cột từ dòng đầu tiên (header)
                    String columnName = getCellData(0, j);
                    // Lấy giá trị từ dòng hiện tại và cột j
                    String cellValue = getCellData(rowNum, j);
                    // Thêm vào Hashtable
                    table.put(columnName, cellValue);
                }
                data[i][0] = table;
            }

            // Đóng workbook và FileInputStream
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Exception in getDataHashTableFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

    public int getRowContains(String sTestCaseName, int colNum) {
        int i;
        int rowCount = getRows();
        for (i = 0; i < rowCount; i++) {
            if (getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
                break;
            }
        }
        return i;
    }

    public int getRows() {
        try {
            return sheet.getLastRowNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    // Get cell data (Lấy data theo từng hàng và cột)
    public String getCellData(int rowNum, int colNum) {
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    // Get cell data (Lấy data theo số thứ tự dòng và tên cột)
    // VD : getCellData(2, "Age") => Lấy dữ liệu ở dòng số 2, cột có tên là Age"
    public String getCellData(int rowNum, String columnName) {
        return getCellData(rowNum, columns.get(columnName));
    }

    // Get cell data (Lấy data theo tên dòng và số thứ tự cột)
    // VD : getCellData("Age" , 2) => Lấy dữ liệu có tên là Age" , ở dòng số 2
    public String getCellData(String columnName, int rowNum) {
        return getCellData(rowNum, columns.get(columnName));
    }

    // Write data to excel sheet
    public void setCellData(String text, int rowNumber, int colNumber) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(colNumber);

            if (cell == null) {
                cell = row.createCell(colNumber);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            text = text.trim().toLowerCase();
            if (text == "pass" || text == "passed" || text == "success") {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text == "fail" || text == "failed" || text == "failure") {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            //e.getMessage();
            //System.out.println(e.getMessage());
            System.out.println("Error : column name not found.");
        }
    }

    // Write data to excel sheet
    public void setCellData(String text, int rowNumber, String columnName) {
        try {
            row = sheet.getRow(rowNumber);
            if (row == null) {
                row = sheet.createRow(rowNumber);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            text = text.trim().toLowerCase();
            if (text == "pass" || text == "passed" || text == "success") {
                style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            }
            if (text == "fail" || text == "failed" || text == "failure") {
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
            }

            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

            System.out.println("Write data to excel file successfully.");

        } catch (Exception e) {
            //e.getMessage();
            //System.out.println(e.getMessage());
            System.out.println("Error : column name not found .");
        }
    }

}
