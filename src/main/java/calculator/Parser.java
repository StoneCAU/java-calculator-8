package calculator;

import java.util.Arrays;
import java.util.List;
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

    // 문자열을 지정된 구분자 기준으로 분리하여 리스트로 반환
    public List<String> split(String delimiter, String numbers) {
        return Arrays.asList(numbers.split(delimiter));
    }
}