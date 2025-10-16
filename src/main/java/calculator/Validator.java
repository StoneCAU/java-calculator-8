package calculator;

public class Validator {
    // input 값 검증
    public void validateInput(String input) {
        if (input.startsWith("//")) {
            validateCustomFormat(input);
        }
    }

    private void validateCustomFormat(String input) {
        // "\n"이 없으면 예외
        if (!input.contains("\\n")) {
            throw new IllegalArgumentException();
        }

        // 커스텀 구분자 추출 후 검증
        int end = input.indexOf("\\n");
        String delimiter = input.substring(2, end);
        validateCustomDelimiter(delimiter);
    }

    private void validateCustomDelimiter(String delimiter) {
        // 커스텀 구분자가 한 글자인지 확인
        if (delimiter.length() != 1) {
            throw new IllegalArgumentException();
        }
        // 커스텀 구분자가 숫자가 아닌지 확인
        if (Character.isDigit(delimiter.charAt(0))) {
            throw new IllegalArgumentException();
        }
    }
}
