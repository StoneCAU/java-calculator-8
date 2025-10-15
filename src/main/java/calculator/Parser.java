package calculator;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> DEFAULT_DELIMITERS = List.of(",", ":");

    public List<String> parse(String input) {
        // "[,:]" 형태의 정규식 생성 - 쉼표 또는 콜론으로 분리
        String regex = "[" + String.join("", DEFAULT_DELIMITERS) + "]";
        return Arrays.asList(input.split(regex));
    }
}
