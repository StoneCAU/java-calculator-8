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
        validateTokens();
    }

    private void validatePattern() {
        String allowedPattern = "^\\d+(?:" + delimiter.regex() + "\\d+)*$";

        if (!numbersPart.matches(allowedPattern)) throw new IllegalArgumentException();
    }

    private void validateTokens() {
        List<String> tokens = Arrays.asList(numbersPart.split(delimiter.regex()));
        for (String token : tokens) {
            validateIsNumber(token);
            validateNoLeadingZero(token);
        }
    }

    private void validateIsNumber(String token) {
        if (!token.matches("\\d+")) throw new IllegalArgumentException();
    }

    private void validateNoLeadingZero(String token) {
        if (token.startsWith("0")) throw new IllegalArgumentException();
    }

    public List<BigInteger> toList() {
        return Arrays.stream(numbersPart.split(delimiter.regex()))
                .map(BigInteger::new)
                .collect(Collectors.toList());
    }
}
