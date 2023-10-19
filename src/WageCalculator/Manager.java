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
