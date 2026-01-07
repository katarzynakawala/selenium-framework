package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojos.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readLoginUsersFromCsv() {

        String path = System.getProperty("user.dir")
                + File.separator + "testData"
                + File.separator + "login-data.csv";

        List<User> users = new ArrayList<>();


        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {

            csvReader.readNext();
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                users.add(new User(row[0], row[1]));
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Failed to read login test data from CSV file " + path, e);
        }

        return users.iterator();
    }
}
