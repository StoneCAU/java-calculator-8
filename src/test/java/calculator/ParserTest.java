package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    @DisplayName("기본 구분자 형식의 문자열을 파싱한다")
    void parseWithDefaultDelimiter() {
        List<BigInteger> result = parser.parse("1,2:3");

        assertThat(result).containsExactly(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
    }

    @Test
    @DisplayName("커스텀 구분자 형식의 문자열을 파싱한다")
    void parseWithCustomDelimiter() {
        List<BigInteger> result = parser.parse("//;\\n1;2;3");

        assertThat(result).containsExactly(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
    }

    @Test
    @DisplayName("단일 숫자를 파싱한다")
    void parseSingleNumber() {
        List<BigInteger> result = parser.parse("5");

        assertThat(result).containsExactly(BigInteger.valueOf(5));
    }

    @Test
    @DisplayName("커스텀 구분자가 기본 구분자와 같아도 파싱한다")
    void parseCustomDelimiterSameAsDefault() {
        List<BigInteger> result = parser.parse("//,\\n1,2,3");

        assertThat(result).containsExactly(
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3)
        );
    }

    @Test
    @DisplayName("커스텀 구분자가 비어있으면 예외가 발생한다")
    void invalidEmptyCustomDelimiter() {
        assertThatThrownBy(() -> parser.parse("//\\n1,2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자 형식이 올바르지 않습니다.");
    }

    @Test
    @DisplayName("커스텀 구분자 형식에 숫자 부분이 없으면 예외가 발생한다")
    void invalidCustomDelimiterWithoutNumbers() {
        assertThatThrownBy(() -> parser.parse("//;\\n"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자와 구분자 형식이 올바르지 않습니다.");
    }
}