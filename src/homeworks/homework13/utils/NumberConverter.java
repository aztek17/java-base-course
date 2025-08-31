package homeworks.homework13.utils;

public class NumberConverter {
    public int parseCount(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Невалидное значение", exception);
        }
    }

    public int validateCount(String text) throws RuntimeException {
        try {
            return parseCount(text);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public double parseNumber(String text) throws IllegalArgumentException {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Невалидное значение", exception);
        }
    }

    public double validateNumber(String text) throws RuntimeException {
        try {
            return parseNumber(text);
        } catch (IllegalArgumentException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }
}
