package com.juanpabloprado.calculator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component
public class Calculator {
    private final Stack<Character> operators = new Stack<>();
    private final Stack<Double> values = new Stack<>();

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int getPrecedence(char c) {
        if (c == '+' || c == '-') {
            return 1;
        }
        if (c == '*' || c == '/') {
            return 2;
        }
        return 0;
    }

    private void processOperator(char o) {
        double a, b;
        if (values.empty()) {
            throw new IllegalArgumentException("Expression error.");
        } else {
            b = values.pop();
        }
        if (values.empty()) {
            throw new IllegalArgumentException("Expression error.");
        } else {
            a = values.pop();
        }
        double r;
        switch (o) {
            case '+':
                r = a + b;
                break;
            case '-':
                r = a - b;
                break;
            case '*':
                r = a * b;
                break;
            case '/':
                r = a / b;
                break;
            default:
                throw new IllegalArgumentException("Operator error.");
        }
        values.push(r);
    }

    public double processInput(List<String> tokens) {

        for (String nextToken : tokens) {
            char current = nextToken.charAt(0);
            if (isNumber(current)) {
                double value = Double.parseDouble(nextToken);
                values.push(value);
            } else if (isOperator(current)) {
                if (operators.empty() || getPrecedence(current) > getPrecedence(operators.peek())) {
                    operators.push(current);
                } else {
                    while (!operators.empty() && getPrecedence(current) <= getPrecedence(operators.peek())) {
                        processOperator(operators.pop());
                    }
                    operators.push(current);
                }
            }

        }

        while (!operators.empty() && isOperator(operators.peek())) {
            processOperator(operators.pop());
        }

        double result = values.pop();
        System.out.println("Result: " + result);

        return result;

    }
}
