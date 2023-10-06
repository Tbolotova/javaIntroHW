package HW1_3;

public class Calculator {
    public int calculate(char op, int a, int b) {
        // Введите свое решение ниже
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new IllegalStateException("Некорректный оператор: " + op);
        };

    }
}
