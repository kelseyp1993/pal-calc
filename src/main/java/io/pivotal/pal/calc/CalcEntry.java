package io.pivotal.pal.calc;

import java.util.Objects;

public class CalcEntry {
    private double operandOne;
    private double operandTwo;
    private int operator;
    private double result;

    public CalcEntry(double operandOne, double operandTwo, int operator) {
        this.operandOne = operandOne;
        this.operandTwo = operandTwo;
        this.operator = operator;
    }


    public CalcEntry(double operandOne, double operandTwo, int operator, double result) {
        this.operandOne = operandOne;
        this.operandTwo = operandTwo;
        this.operator = operator;
        this.result = result;
    }

    public CalcEntry(){}

    public double getOperandOne() {
        return operandOne;
    }

    public double getOperandTwo() {
        return operandTwo;
    }

    public int getOperator() {
        return operator;
    }

    public double getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalcEntry calcEntry = (CalcEntry) o;
        return Double.compare(calcEntry.operandOne, operandOne) == 0 &&
                Double.compare(calcEntry.operandTwo, operandTwo) == 0 &&
                operator == calcEntry.operator &&
                Double.compare(calcEntry.result, result) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operandOne, operandTwo, operator, result);
    }

    @Override
    public String toString() {
        return "CalcEntry{" +
                "operandOne=" + operandOne +
                ", operandTwo=" + operandTwo +
                ", operator=" + operator +
                ", result=" + result +
                '}';
    }

    public void setOperandOne(double operandOne) {
        this.operandOne = operandOne;
    }

    public void setOperandTwo(double operandTwo) {
        this.operandTwo = operandTwo;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
