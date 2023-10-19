package WageCalculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Datastore {
    private List<Employee> employees = new ArrayList<>();

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
                    //TODO: Invalid data maybe log error etc.
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
        try {
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            getDailyWorkHours(filePath.toString());
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    });

        } catch (IOException err) {

            System.err.println("Error getting work hours: " + err.getMessage());

        }

    }


}

