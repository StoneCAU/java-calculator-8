package calculator;

import java.math.BigInteger;
import java.util.List;

public class Parser {
    private static final String CUSTOM_INDICATOR_START = "//";
    private static final String CUSTOM_INDICATOR_END = "\\n";

    public List<BigInteger> parse(String input) {
        String delimiterPart = extractDelimiter(input);
        String numbersPart = extractNumbers(input);

        Delimiter delimiter = new Delimiter(delimiterPart);
        Numbers numbers = new Numbers(delimiter, numbersPart);

        return numbers.toList();
    }

    private String extractDelimiter(String input) {
        if (isNotCustomDelimiter(input)) return "";

        int end = input.indexOf(CUSTOM_INDICATOR_END);
        if (end == -1) throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");

        String delimiterPart = input.substring(2, end);
        if (delimiterPart.isEmpty()) throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");

        return delimiterPart;
    }

    private String extractNumbers(String input) {
        if (isNotCustomDelimiter(input)) return input;
        int start = input.indexOf(CUSTOM_INDICATOR_END);
        return input.substring(start + 2);
    }

    private boolean isNotCustomDelimiter(String input) {
        return !input.startsWith(CUSTOM_INDICATOR_START)
                || !input.contains(CUSTOM_INDICATOR_END);
    }
}