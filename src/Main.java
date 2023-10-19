/*
 * MIT License
 *
 * Copyright (c) 2023. [Asaf Efe Bağ]
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
            datastore.getBulkWorkHours(WORK_HOURS_FOLDER);

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        EmployeeReport report = new EmployeeReport(datastore.getEmployees());
        //TODO: Select option
        System.out.println("\nWages in Alphabetical Order:");
        report.printWagesAlphabetically();

        System.out.println("\nEmployees By Missed Hours:");
        report.printEmployeesByMissedHours();

        System.out.println("\nTotal Amount to Pay:");
        report.printTotalAmountToPay();


    }
}