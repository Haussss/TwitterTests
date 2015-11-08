package helpers;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DataProviders {

    private static final String REGISTRATION_DATA_FILE = "registrationData.csv";

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return read(REGISTRATION_DATA_FILE);
    }

    public Object[][] read(String csvFilePath) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(csvFilePath);
        InputStreamReader isr = new InputStreamReader(is);
        CSVReader reader = new CSVReader(isr, ';');
        List<String[]> lines = null;
        try {
            lines = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int s = lines.size();
        int l = 0;
        if (s > 0) {
            l = lines.get(0).length;
        }
        Object[][] obj = new Object[s][l];


        int v = 0;

        for (String[] line : lines) {
            for (int a = 0; a < line.length; a++) {
                obj[v][a] = line[a];
            }
            v++;
        }


        return obj;
    }

    @Test
    public void main() {
        read(REGISTRATION_DATA_FILE);

    }
}
