package helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;
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


//public class DataProviders {
//
//    private static final String REGISTRATION_DATA_FILE = "RegistrationData.csv";
//
//    @DataProvider(name = "registrationData")
//    public Object[][] getRegistrationData() {
//        return read(REGISTRATION_DATA_FILE);
//    }
//
//    public Object[][] read(String csvFilePath) {
//        InputStream is = getClass().getClassLoader().getResourceAsStream(csvFilePath);
//        InputStreamReader isr = new InputStreamReader(is);
//        CSVReader reader = new CSVReader(isr, ';');
//        List<String[]> lines = null;
//        try {
//            lines = reader.readAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int s = lines.size();
//        int l = 0;
//        if (s > 0) {
//            l = lines.get(0).length;
//        }
//        Object[][] obj = new Object[s][l];
//
//
//        int v = 0;
//
//        for (String[] line : lines) {
//            for (int a = 0; a < line.length; a++) {
//                obj[v][a] = line[a];
//            }
//            v++;
//        }
//
//
//        return obj;
//    }
//
//    @Test
//    public void main() {
//        read(REGISTRATION_DATA_FILE);
//
//    }
//}
