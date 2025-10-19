package calculator;

import java.util.List;
import java.util.regex.Pattern;

public class Delimiter {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");
    private static final String CUSTOM_INDICATOR_START = "//";
    private static final String CUSTOM_INDICATOR_END = "\\n";

    private final String regex;

    public Delimiter(String input) {
        if (isCustomDelimiter(input)) {
            String custom = extractCustom(input);
            validateCustom(custom);
            this.regex = Pattern.quote(custom);
        }
        else {
            this.regex = "[" + String.join("", DEFAULT_DELIMITERS) + "]";
        }
    }

    public String regex() {
        return regex;
    }

    private boolean isCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_INDICATOR_START)
                && input.contains(CUSTOM_INDICATOR_END);
    }

    private String extractCustom(String input) {
        int end = input.indexOf(CUSTOM_INDICATOR_END);
        if (end == -1) throw new IllegalArgumentException();

        String custom = input.substring(2, end);
        if (custom.isEmpty()) throw new IllegalArgumentException();

        return custom;
    }

    private void validateCustom(String custom) {
        if (custom.length() != 1) throw new IllegalArgumentException();
        if (Character.isDigit(custom.charAt(0))) throw new IllegalArgumentException();
    }
}
