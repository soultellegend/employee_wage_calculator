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
