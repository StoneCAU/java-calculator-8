package calculator;

import java.util.regex.Pattern;

public class Parser {
    // 커스텀 구분자 형식인지 판단
    public boolean isCustomDelimiter(String input) {
        return input.startsWith(Constants.CUSTOM_INDICATOR_START)
                && input.contains(Constants.CUSTOM_INDICATOR_END);
    }

    // 정규표현식 기반 String 값 리턴
    public String extractDelimiter(String input) {
        if (!isCustomDelimiter(input)) {
            return "[" + String.join("", Constants.DEFAULT_DELIMITERS) + "]";
        }

        int end = input.indexOf(Constants.CUSTOM_INDICATOR_END);
        String delimiter = input.substring(2, end);
        return Pattern.quote(delimiter);
    }

    // numbers String 값 리턴
    public String extractNumbers(String input) {
        if (!isCustomDelimiter(input)) return input;
        int start = input.indexOf(Constants.CUSTOM_INDICATOR_END);
        return input.substring(start + 2);
    }
}