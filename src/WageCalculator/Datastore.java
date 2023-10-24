
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

package WageCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;

import java.util.stream.Stream;


public class Datastore {
    private final List<Employee> employees = new ArrayList<>();

    public void getCatalog(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                /*
                Part:
                   0 - identifier
                   1 - name
                   2 - position
                   3 - required workHours
                   4 - basic or hourly wage (based on position)
                   5 - overtime wage
                */
                if (parts.length < 6)
                    continue;
                String identifier = parts[0].trim();
                String name = parts[1].trim();
                String position = parts[2].trim();
                double requiredWorkHours = Double.parseDouble(parts[3].trim());
                double basicOrHourlyWage = Double.parseDouble(parts[4].trim());
                double overtimeWage = Double.parseDouble(parts[5].trim());

                Employee employee;
                if (position.equalsIgnoreCase("manager")) {
                    employee = new Manager(identifier, name, requiredWorkHours, basicOrHourlyWage, overtimeWage);
                } else {
                    employee = new Worker(identifier, name, requiredWorkHours, basicOrHourlyWage, overtimeWage);
                }
                employees.add(employee);
            }
        }
    }

    public void getDailyWorkHours(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String data_row;
            while ((data_row = reader.readLine()) != null) {
                String[] parts = data_row.split(";");
                  /*
                Part:
                   0 - identifier
                   1 - Daily work hours
                  */

                if (parts.length < 2)
                    continue;
                String identifier = parts[0].trim();
                double workHours = Double.parseDouble(parts[1].trim());
                for (Employee employee : employees) {
                    if (employee.getIdentifier().equals(identifier)) {
                        employee.addWorkHours(workHours);
                        break;
                    }
                }
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }


    public void getBulkWorkHours(String folderPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            paths.filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            System.out.printf("Loading work hours from: %s \n", filePath.toString());
                            getDailyWorkHours(filePath.toString());
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    });
        } catch (IOException err) {
            System.err.println("Error getting work hours: " + err.getMessage());
        }
    }



}

