import WageCalculator.*;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        //TODO: Dotenv alternative class
        String WORK_HOURS_FOLDER = "data/work_hours/",
                EMPLOYEE_CATALOG_FILE = "data/employee_catalog.txt";

        Datastore datastore = new Datastore();
        try {
            datastore.getCatalog(EMPLOYEE_CATALOG_FILE);
            //TODO: Automatically get work hours files from data/work_hours folder
            datastore.getBulkWorkHours(WORK_HOURS_FOLDER);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        EmployeeReport report = new EmployeeReport(datastore.getEmployees());
        //TODO: Select option
        System.out.println("Wages in Alphabetical Order:");
        report.printWagesAlphabetically();

        System.out.println("\nEmployees By Missed Hours:");
        report.printEmployeesByMissedHours();

        System.out.println("\nTotal Amount to Pay:");
        report.printTotalAmountToPay();


    }
}