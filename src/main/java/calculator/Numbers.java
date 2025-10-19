package calculator;

public class Numbers {
    private final Delimiter delimiter;
    private final String numbersPart;

    public Numbers(Delimiter delimiter, String numbersPart) {
        this.delimiter = delimiter;
        this.numbersPart = numbersPart;
        validateNumbers();
    }

    private void validateNumbers() {
        String allowedPattern = "^[1-9](?:" + delimiter.regex() + "[1-9])*$";

        if (!numbersPart.matches(allowedPattern)) throw new IllegalArgumentException();
    }
}
