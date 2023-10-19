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
