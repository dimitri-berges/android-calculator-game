package fr.lomis.iut.m4101c2021_2022.utils;

import androidx.annotation.NonNull;

public class GameCalcul {
    private int leftNumber;
    private GameCalculOperator operator;
    private int rightNumber;
    private int result;

    public GameCalcul(int leftNumber, GameCalculOperator operator, int rightNumber, int result) {
        this.leftNumber = leftNumber;
        this.operator = operator;
        this.rightNumber = rightNumber;
        this.result = result;
    }

    public GameCalcul(int leftNumber, GameCalculOperator operator, int rightNumber) throws ArithmeticException {
        this.leftNumber = leftNumber;
        this.operator = operator;
        this.rightNumber = rightNumber;
        this.result = execCalcul(leftNumber, operator, rightNumber);
    }

    public int getLeftNumber() {
        return leftNumber;
    }

    public GameCalculOperator getOperator() {
        return operator;
    }

    public int getRightNumber() {
        return rightNumber;
    }

    public int getResult() {
        return result;
    }

    public static int execCalcul(int leftNumber, GameCalculOperator operator, int rightNumber) throws ArithmeticException {
        switch (operator) {
            case Addition:
                return leftNumber + rightNumber;
            case Soustraction:
                if (leftNumber < rightNumber) throw new ArithmeticException("Result must be positive and values aren't compatible with this. leftN = " + leftNumber + "; rightN = " + rightNumber);
                return leftNumber - rightNumber;
            case Multiplication:
                return leftNumber * rightNumber;
            case Division:
                if (((double)leftNumber / (double) rightNumber) != (double)(leftNumber / rightNumber))
                    throw new ArithmeticException("Result can't be an integer with those values. leftN = " + leftNumber + "; rightN = " + rightNumber);
            case Division_euclid:
                return leftNumber / rightNumber;
            case Division_reste:
                return leftNumber % rightNumber;
            default:
                throw new IllegalArgumentException("Operator isn't valid.");
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "" + leftNumber + getOperatorString(operator) + rightNumber;
    }

    public static String getOperatorString(GameCalculOperator operator) {
        switch (operator) {
            case Addition:
                return "+";
            case Soustraction:
                return "-";
            case Multiplication:
                return "*";
            case Division:
            case Division_euclid:
                return "/";
            case Division_reste:
                return "%";
            default:
                return "";
        }
    }
}
