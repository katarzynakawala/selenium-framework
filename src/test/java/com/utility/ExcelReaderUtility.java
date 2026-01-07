package com.utility;

import com.ui.pojos.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> readLoginUsersFromExcel() {

        String path = System.getProperty("user.dir")
                + File.separator + "testData"
                + File.separator + "login-data.xlsx";

        List<User> users = new ArrayList<>();


        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path)) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("login-data");
            Iterator<Row> rowIterator = xssfSheet.iterator();
            rowIterator.next();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                User user = new User(row.getCell(0).toString(), row.getCell(1).toString());
                users.add(user);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read login test data from xlsx file " + path, e);
        }

        return users.iterator();
    }
}
