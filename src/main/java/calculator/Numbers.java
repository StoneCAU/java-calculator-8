package calculator;

public class Numbers {
    private final Delimiter delimiter;
    private final String numbersPart;

    public Numbers(Delimiter delimiter, String numbersPart) {
        this.delimiter = delimiter;
        this.numbersPart = numbersPart;
        validatePattern();
    }

    private void validatePattern() {
        String allowedPattern = "^\\d+(?:" + delimiter.regex() + "\\d+)*$";

        if (!numbersPart.matches(allowedPattern)) throw new IllegalArgumentException();
    }

}
