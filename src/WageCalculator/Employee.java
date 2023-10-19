package WageCalculator;

public abstract class Employee {
    protected String identifier;
    protected String name;
    protected double requiredWorkHours;
    protected double sumOfWorkHours;
    protected double numberOfMissedHours;
    protected double sumOfOvertimeHours;

    public Employee(String identifier, String name, double requiredWorkHours) {
        this.identifier = identifier;
        this.name = name;
        this.requiredWorkHours = requiredWorkHours;
        this.sumOfWorkHours = 0;
        this.numberOfMissedHours = 0;
        this.sumOfOvertimeHours = 0;
    }

    public abstract double calculateWage();

    public void addWorkHours(double hours) {
        this.sumOfWorkHours += hours;
        if (hours < requiredWorkHours) {
            this.numberOfMissedHours += requiredWorkHours - hours;
        } else if (hours > requiredWorkHours) {
            this.sumOfOvertimeHours += hours - requiredWorkHours;
        }
    }

    // Getters
    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public double getRequiredWorkHours() {
        return requiredWorkHours;
    }

    public double getNumberOfMissedHours() {
        return numberOfMissedHours;
    }

    public double getSumOfOvertimeHours() {
        return sumOfOvertimeHours;
    }
}
