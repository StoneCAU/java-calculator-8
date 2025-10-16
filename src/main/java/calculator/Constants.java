package calculator;

import java.util.List;

public class Constants {
    // 구분자 관련
    public static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");
    public static final String CUSTOM_INDICATOR_START = "//";
    public static final String CUSTOM_INDICATOR_END = "\\n";

    // 메시지 관련
    public static final String INPUT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    public static final String OUTPUT_MESSAGE = "결과 : %s";
}
