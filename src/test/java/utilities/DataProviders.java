package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {
        String path = ".\\testData\\Opencart_LoginData.xlsx"; // Path to Excel file
        ExcelUtility xlutil = new ExcelUtility(path); // Creating an object for ExcelUtility

        int totalrows = xlutil.getRowCount("Sheet1"); // Get total rows in Sheet1
        int totalcols = xlutil.getCellCount("Sheet1", 1); // Get total columns in Sheet1 at row 1

        Object[][] logindata = new Object[totalrows][totalcols]; // Create a 2D array to store login data

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // Read data from Excel and store in array
            }
        }
        return logindata; // Return the login data array
    }
}
