package xyz.cym2018.syntax;

import java.io.Serializable;

@SuppressWarnings("unused")
public class SyntaxTree implements Serializable {
    final Boolean isNumber;
    final Integer number;
    final String operator;
    final SyntaxTree left;
    final SyntaxTree right;

    public SyntaxTree(Boolean isNumber, Integer number, SyntaxTree left, SyntaxTree right, String operator) {
        this.isNumber = isNumber;
        this.number = number;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public static SyntaxTree createNumber(Integer number) {
        return new SyntaxTree(true, number, null, null, null);
    }

    public static SyntaxTree createTree(SyntaxTree left, SyntaxTree right, String operator) {
        return new SyntaxTree(false, null, left, right, operator);
    }

    public static SyntaxTree createTree(Integer leftNumber, Integer rightNumber, String operator) {
        return createTree(createNumber(leftNumber), createNumber(rightNumber), operator);
    }

    public static SyntaxTree createTree(SyntaxTree left, Integer rightNumber, String operator) {
        return createTree(left, createNumber(rightNumber), operator);
    }

    public static SyntaxTree createTree(Integer leftNumber, SyntaxTree right, String operator) {
        return createTree(createNumber(leftNumber), right, operator);
    }

    @Override
    public String toString() {
        if (isNumber) {
            return String.valueOf(number);
        }
        return "[" + left + operator + right + "]";
    }
}
