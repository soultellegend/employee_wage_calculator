package WageCalculator;

import java.util.Comparator;
import java.util.List;

public class EmployeeReport {
    private final List<Employee> employees;

    public EmployeeReport(List<Employee> employees) {
        this.employees = employees;
    }

    public void printWagesAlphabetically() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee -> System.out.printf("%s: $%.2f%n", employee.getName(), employee.calculateWage()));
    }

    public void printEmployeesByMissedHours() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getNumberOfMissedHours).reversed())
                .forEach(employee -> System.out.printf("%s: Missed Hours - %.2f%n", employee.getName(), employee.getNumberOfMissedHours()));
    }

    public void printTotalAmountToPay() {
        double totalNormalWage = employees.stream().mapToDouble(Employee::calculateWage).sum();
        double totalOvertimeWage = employees.stream().mapToDouble(e -> e.getSumOfOvertimeHours() * ((e instanceof Manager) ? ((Manager) e).getOvertimeWage() : ((Worker) e).getHourlyWage() * (1 + ((Worker) e).getOvertimeWagePercentage()))).sum();
        double totalLossByMissedHours = employees.stream().mapToDouble(e -> e.getNumberOfMissedHours() * ((e instanceof Manager) ? ((Manager) e).getBasicWage() / e.getRequiredWorkHours() : ((Worker) e).getHourlyWage())).sum();

        System.out.printf("Total Normal Wage: $%.2f%n", totalNormalWage);
        System.out.printf("Total Overtime Wage: $%.2f%n", totalOvertimeWage);
        System.out.printf("Total Loss By Missed Hours: $%.2f%n", totalLossByMissedHours);
    }
}
