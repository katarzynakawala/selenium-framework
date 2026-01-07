package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojos.TestData;
import com.ui.pojos.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public static Iterator<Object[]> loginDataProvider() {

        Gson gson = new Gson();

        String path = System.getProperty("user.dir")
                + File.separator + "testData"
                + File.separator + "login-data.json";


        try (FileReader fileReader = new FileReader(path)) {
                TestData testData = gson.fromJson(fileReader, TestData.class);

                 if (testData == null || testData.getData() == null || testData.getData().isEmpty()) {
                    throw new IllegalStateException(
                            "Login test data is empty or missing");
                }

                List<Object[]> dataToReturn = new ArrayList<>();
                for ( User user:testData.getData()) {
                    dataToReturn.add(new Object[] {user});
                }

                return dataToReturn.iterator();

        } catch (Exception e) {
            throw new RuntimeException("Failed to load login test data", e);
        }
    }

    @DataProvider(name = "LoginCSVDataProvider")
    public Iterator<User> loginCSVDataProvider() {
        return CSVReaderUtility.readLoginUsersFromCsv();
    }

    @DataProvider(name = "LoginExcelDataProvider")
    public Iterator<User> loginExcelDataProvider() {
        return ExcelReaderUtility.readLoginUsersFromExcel();
    }
}






