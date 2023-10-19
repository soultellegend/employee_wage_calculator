package WageCalculator;

public class Worker extends Employee {
    private double hourlyWage;
    private double overtimeWagePercentage;

    public Worker(String identifier, String name, double requiredWorkHours, double hourlyWage, double overtimeWagePercentage) {
        super(identifier, name, requiredWorkHours);
        this.hourlyWage = hourlyWage;
        this.overtimeWagePercentage = overtimeWagePercentage / 100.0;
    }

    @Override
    public double calculateWage() {
        return (requiredWorkHours * hourlyWage)
                + (sumOfOvertimeHours * hourlyWage * (1 + overtimeWagePercentage));
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public double getOvertimeWagePercentage() {
        return overtimeWagePercentage;
    }
}
