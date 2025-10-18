package calculator;

public class Validator {
    public void validateCustomDelimiterRules(String delimiter, String numbers) {
        // 1. delimiter 검사
        validateCustomDelimiter(delimiter);

        // 2. numbers 검사
        validateCustomNumbers(delimiter, numbers);
    }

    private void validateCustomDelimiter(String delimiter) {
        // 1. 커스텀 구분자는 한 글자여야 한다
        if (delimiter.length() != 1) throw new IllegalArgumentException();

        // 2. 커스텀 구분자는 숫자일 수 없다
        if (Character.isDigit(delimiter.charAt(0))) throw new IllegalArgumentException();
    }

    private void validateCustomNumbers(String delimiter, String numbers) {
        // 1. 문자열은 구분자로 시작/끝날 수 없다
        if (numbers.startsWith(delimiter) || numbers.endsWith(delimiter)) throw new IllegalArgumentException();

        // 2. 구분자는 연속으로 올 수 없다
        if (numbers.contains(delimiter + delimiter)) throw new IllegalArgumentException();
    }
}