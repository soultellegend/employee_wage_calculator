package WageCalculator;

public class Manager extends Employee {
    private double basicWage;
    private double overtimeWage;

    public Manager(String identifier, String name, double requiredWorkHours, double basicWage, double overtimeWage) {
        super(identifier, name, requiredWorkHours);
        this.basicWage = basicWage;
        this.overtimeWage = overtimeWage;
    }

    @Override
    public double calculateWage() {
        return basicWage + (overtimeWage * sumOfOvertimeHours);
    }

    public double getBasicWage() {
        return basicWage;
    }

    public double getOvertimeWage() {
        return overtimeWage;
    }
}
