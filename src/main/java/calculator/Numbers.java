package calculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Numbers {
    private final Delimiter delimiter;
    private final String numbersPart;

    public Numbers(Delimiter delimiter, String numbersPart) {
        this.delimiter = delimiter;
        this.numbersPart = numbersPart;
        validatePattern();
    }

    public List<BigInteger> toList() {
        return Arrays.stream(delimiter.split(numbersPart))
                .map(BigInteger::new)
                .collect(Collectors.toList());
    }

    private void validatePattern() {
        if (!delimiter.matches(numbersPart)) {
            throw new IllegalArgumentException("숫자와 구분자 형식이 올바르지 않습니다.");
        }
    }
}
