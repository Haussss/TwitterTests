package helpers;

import au.com.bytecode.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DataProviders {
    private static final String REGISTRATION_DATA_FILE = "./src/main/resources/RegistrationData.csv";

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() throws IOException {
        return getData(REGISTRATION_DATA_FILE, ",");
    }

    private static Object[][] getData(String path, String divider) throws IOException {
        List<String> data = Helpers.readAllLines(path);
        Object[][] dataRows = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataRows[i] = data.get(i).split(divider);
        }
        return dataRows;
    }
}



