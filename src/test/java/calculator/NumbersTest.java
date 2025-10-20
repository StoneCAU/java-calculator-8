package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NumbersTest {

    @Test
    @DisplayName("기본 구분자로 분리된 숫자를 BigInteger 리스트로 변환한다")
    void toListWithDefaultDelimiter() {
        Delimiter delimiter = new Delimiter("");
        Numbers numbers = new Numbers(delimiter, "1,2,3");

        List<BigInteger> result = numbers.toList();

        assertThat(result).containsExactly(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
    }

    @Test
    @DisplayName("커스텀 구분자로 분리된 숫자를 BigInteger 리스트로 변환한다")
    void toListWithCustomDelimiter() {
        Delimiter delimiter = new Delimiter(";");
        Numbers numbers = new Numbers(delimiter, "10;20;30");

        List<BigInteger> result = numbers.toList();

        assertThat(result).containsExactly(
                BigInteger.valueOf(10),
                BigInteger.valueOf(20),
                BigInteger.valueOf(30)
        );
    }

    @Test
    @DisplayName("Long 범위를 초과하는 큰 수를 BigInteger로 변환한다")
    void toListWithBigNumbers() {
        Delimiter delimiter = new Delimiter("");
        Numbers numbers = new Numbers(delimiter, "99999999999999999999,1");

        List<BigInteger> result = numbers.toList();

        assertThat(result).containsExactly(
                new BigInteger("99999999999999999999"),
                BigInteger.ONE
        );
    }

    @Test
    @DisplayName("0으로 시작하는 숫자가 있으면 예외가 발생한다")
    void invalidLeadingZero() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "01,02"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("음수가 포함되면 예외가 발생한다")
    void invalidNegativeNumber() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("0이 포함되면 예외가 발생한다")
    void invalidZero() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "1,0,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("구분자가 연속으로 나오면 예외가 발생한다")
    void invalidConsecutiveDelimiters() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "1,,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("문자열이 구분자로 시작하면 예외가 발생한다")
    void invalidStartWithDelimiter() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, ",1,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("문자열이 구분자로 끝나면 예외가 발생한다")
    void invalidEndWithDelimiter() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "1,2,"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 문자가 포함되면 예외가 발생한다")
    void invalidNonDigitCharacter() {
        Delimiter delimiter = new Delimiter("");

        assertThatThrownBy(() -> new Numbers(delimiter, "1,abc,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("설정되지 않은 구분자를 사용하면 예외가 발생한다")
    void invalidUnsetDelimiter() {
        Delimiter delimiter = new Delimiter(";");

        assertThatThrownBy(() -> new Numbers(delimiter, "1.2.3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }
}