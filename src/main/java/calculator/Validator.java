package calculator;

public class Validator {
    private boolean isCustom;

    public void validateInput(String input) {
        isCustom = input.startsWith(Constants.CUSTOM_INDICATOR_START);

        if (isCustom) {
            validateCustomFormat(input);
            validateCustomDelimiterRules(input);
        } else {
            validateDefaultDelimiterRules(input);
        }
    }

    private void validateCustomDelimiterRules(String input) {
        String numbers = extractNumbers(input);
        String delimiter = extractCustomDelimiter(input);

        if (numbers.startsWith(delimiter) || numbers.endsWith(delimiter)) {
            throw new IllegalArgumentException();
        }

        if (numbers.contains(delimiter + delimiter)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDefaultDelimiterRules(String input) {
        validateNotStartsWithDelimiter(input);
        validateNotEndsWithDelimiter(input);
        validateNoConsecutiveDelimiters(input);
    }

    private void validateNotStartsWithDelimiter(String input) {
        for (String delimiter : Constants.DEFAULT_DELIMITERS) {
            if (input.startsWith(delimiter)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateNotEndsWithDelimiter(String input) {
        for (String delimiter : Constants.DEFAULT_DELIMITERS) {
            if (input.endsWith(delimiter)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void validateNoConsecutiveDelimiters(String input) {
        String delimiterPattern = "[" + String.join("", Constants.DEFAULT_DELIMITERS) + "]{2,}";
        if (input.matches(".*" + delimiterPattern + ".*")) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCustomFormat(String input) {
        if (!input.contains(Constants.CUSTOM_INDICATOR_END)) {
            throw new IllegalArgumentException();
        }

        String delimiter = extractCustomDelimiter(input);
        if (delimiter.length() != 1 || Character.isDigit(delimiter.charAt(0))) {
            throw new IllegalArgumentException();
        }
    }

    private String extractNumbers(String input) {
        return input.substring(input.indexOf(Constants.CUSTOM_INDICATOR_END) + Constants.CUSTOM_INDICATOR_END.length());
    }

    private String extractCustomDelimiter(String input) {
        int end = input.indexOf(Constants.CUSTOM_INDICATOR_END);
        return input.substring(Constants.CUSTOM_INDICATOR_START.length(), end);
    }
}