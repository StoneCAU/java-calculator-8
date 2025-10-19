package calculator;

import java.util.List;
import java.util.regex.Pattern;

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
            validate(delimiterPart);
            this.regex = Pattern.quote(delimiterPart);
        }
    }

    public String[] split(String input) {
        return input.split(regex);
    }

    public boolean matches(String input) {
        String allowedPattern = "^[1-9]\\d*(?:" + regex + "[1-9]\\d*)*$";
        return input.matches(allowedPattern);
    }

    private void validate(String delimiterPart) {
        if (delimiterPart.length() != 1 || Character.isDigit(delimiterPart.charAt(0))) {
            throw new IllegalArgumentException("커스텀 구분자는 숫자가 아닌 1글자여야 합니다.");
        }
    }
}
