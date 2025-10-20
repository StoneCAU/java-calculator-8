package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DelimiterTest {

    @Test
    @DisplayName("기본 구분자로 쉼표를 사용하여 문자열을 분리한다")
    void splitWithComma() {
        Delimiter delimiter = new Delimiter("");
        String[] result = delimiter.split("1,2,3");

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자로 콜론을 사용하여 문자열을 분리한다")
    void splitWithColon() {
        Delimiter delimiter = new Delimiter("");
        String[] result = delimiter.split("1:2:3");

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자를 혼용하여 문자열을 분리한다")
    void splitWithMixedDelimiters() {
        Delimiter delimiter = new Delimiter("");
        String[] result = delimiter.split("1,2:3");

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("커스텀 구분자로 문자열을 분리한다")
    void splitWithCustomDelimiter() {
        Delimiter delimiter = new Delimiter(";");
        String[] result = delimiter.split("1;2;3");

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("특수문자 커스텀 구분자로 문자열을 분리한다")
    void splitWithSpecialCharacterDelimiter() {
        Delimiter delimiter = new Delimiter("|");
        String[] result = delimiter.split("1|2|3");

        assertThat(result).containsExactly("1", "2", "3");
    }

    @Test
    @DisplayName("기본 구분자 패턴과 일치하는지 확인한다")
    void matchesWithDefaultDelimiter() {
        Delimiter delimiter = new Delimiter("");

        assertThat(delimiter.matches("1,2,3")).isTrue();
        assertThat(delimiter.matches("1:2:3")).isTrue();
        assertThat(delimiter.matches("1,2:3")).isTrue();
    }

    @Test
    @DisplayName("커스텀 구분자 패턴과 일치하는지 확인한다")
    void matchesWithCustomDelimiter() {
        Delimiter delimiter = new Delimiter(";");

        assertThat(delimiter.matches("1;2;3")).isTrue();
        assertThat(delimiter.matches("10;20;30")).isTrue();
    }

    @Test
    @DisplayName("0으로 시작하는 숫자는 패턴과 일치하지 않는다")
    void notMatchesWithLeadingZero() {
        Delimiter delimiter = new Delimiter("");

        assertThat(delimiter.matches("01,02")).isFalse();
        assertThat(delimiter.matches("1,02,3")).isFalse();
    }

    @Test
    @DisplayName("구분자가 연속으로 나오면 패턴과 일치하지 않는다")
    void notMatchesWithConsecutiveDelimiters() {
        Delimiter delimiter = new Delimiter("");

        assertThat(delimiter.matches("1,,2")).isFalse();
        assertThat(delimiter.matches("1::2")).isFalse();
    }

    @Test
    @DisplayName("커스텀 구분자가 두 글자 이상이면 예외가 발생한다")
    void invalidDelimiterLength() {
        assertThatThrownBy(() -> new Delimiter(";;"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 숫자가 아닌 1글자여야 합니다.");
    }

    @Test
    @DisplayName("커스텀 구분자가 숫자이면 예외가 발생한다")
    void invalidDelimiterDigit() {
        assertThatThrownBy(() -> new Delimiter("1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 숫자가 아닌 1글자여야 합니다.");
    }
}