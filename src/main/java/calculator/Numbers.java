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

    private void validatePattern() {
        String allowedPattern = "^[1-9]\\d*(?:" + delimiter.regex() + "[1-9]\\d*)*$";

        if (!numbersPart.matches(allowedPattern)) throw new IllegalArgumentException();
    }

    public List<BigInteger> toList() {
        return Arrays.stream(numbersPart.split(delimiter.regex()))
                .map(BigInteger::new)
                .collect(Collectors.toList());
    }
}
