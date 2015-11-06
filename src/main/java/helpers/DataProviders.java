package helpers;

import org.testng.annotations.DataProvider;

public class DataProviders {
    private static final String REGISTRATION_DATA_FILE = "/registrationData.csv";
    @DataProvider("registrationData")
    public Object[][] getRegistrationData(){
        return new Object[][] {
                {},
                {}
        }
    }
}
