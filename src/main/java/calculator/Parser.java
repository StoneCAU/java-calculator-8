package calculator;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");
    private static final String CUSTOM_INDICATOR_START = "//";
    private static final String CUSTOM_INDICATOR_END = "\\n";

    // 커스텀 구분자 형식인지 판단
    public boolean isCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_INDICATOR_START)
                && input.contains(CUSTOM_INDICATOR_END);
    }

    // 정규표현식 기반 String 값 리턴
    public String extractDelimiter(String input) {
        if (!isCustomDelimiter(input)) {
            return "[" + String.join("", DEFAULT_DELIMITERS) + "]";
        }

        int end = input.indexOf(CUSTOM_INDICATOR_END);
        if (end == -1) throw new IllegalArgumentException(); // input 형식 오류

        String delimiter = input.substring(2, end);
        if (delimiter.isEmpty()) throw new IllegalArgumentException(); // input 형식 오류

        return delimiter;
    }

    // numbers String 값 리턴
    public String extractNumbers(String input) {
        if (!isCustomDelimiter(input)) return input;
        int start = input.indexOf(CUSTOM_INDICATOR_END);
        return input.substring(start + 2);
    }

    // 문자열을 지정된 구분자 기준으로 분리하여 리스트로 반환
    public List<String> split(String delimiter, String numbers) {
        return Arrays.asList(numbers.split(delimiter));
    }
}