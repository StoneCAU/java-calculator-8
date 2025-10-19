package calculator;

import java.util.List;

public class Delimiter {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    private final String regex;

    public Delimiter(String delimiterPart) {
        // 기본 구분자
        if (delimiterPart.isEmpty()) {
            this.regex = "[" + String.join("", DEFAULT_DELIMITERS) + "]";
        }
        // 커스텀 구분자
        else {
            validateCustom(delimiterPart);
            this.regex = delimiterPart;
        }
    }

    public String regex() {
        return regex;
    }

    private void validateCustom(String delimiterPart) {
        if (delimiterPart.length() != 1) throw new IllegalArgumentException();
        if (Character.isDigit(delimiterPart.charAt(0))) throw new IllegalArgumentException();
    }
}
