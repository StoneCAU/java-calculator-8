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

    public void validateDefaultDelimiterRules(String numbers) {
        // 1. 문자열은 구분자로 시작/끝날 수 없다
        for (String delimiter : Constants.DEFAULT_DELIMITERS) {
            if (numbers.startsWith(delimiter) || numbers.endsWith(delimiter)) throw new IllegalArgumentException();
        }

        // 2. 기본 구분자("," 또는 ":")가 연속으로 올 수 없다
        // [,:] -> 쉼표(,) 또는 콜론(:) 중 하나
        // {2,} -> 위 문자가 2번 이상 연속됨
        // .* ... .* -> 문자열 어디에든 그런 패턴이 존재하면 true
        String delimiterPattern = "[" + String.join("", Constants.DEFAULT_DELIMITERS) + "]{2,}";
        if (numbers.matches(".*" + delimiterPattern + ".*")) throw new IllegalArgumentException();
    }
}