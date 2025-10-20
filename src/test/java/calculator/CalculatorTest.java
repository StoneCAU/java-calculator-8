package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(new Parser());
    }

    @Test
    @DisplayName("빈 문자열 입력 시 0을 반환한다")
    void emptyString() {
        BigInteger result = calculator.calculate("");

        assertThat(result).isEqualTo(BigInteger.ZERO);
    }

    @Test
    @DisplayName("숫자 하나만 입력 시 해당 숫자를 반환한다")
    void singleNumber() {
        BigInteger result = calculator.calculate("5");

        assertThat(result).isEqualTo(BigInteger.valueOf(5));
    }

    @Test
    @DisplayName("쉼표 구분자로 숫자들의 합을 계산한다")
    void commaDelimiter() {
        BigInteger result = calculator.calculate("1,2,3");

        assertThat(result).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    @DisplayName("콜론 구분자로 숫자들의 합을 계산한다")
    void colonDelimiter() {
        BigInteger result = calculator.calculate("1:2:3");

        assertThat(result).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    @DisplayName("쉼표와 콜론을 혼용하여 숫자들의 합을 계산한다")
    void mixedDelimiters() {
        BigInteger result = calculator.calculate("1,2:3");

        assertThat(result).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    @DisplayName("커스텀 구분자로 숫자들의 합을 계산한다")
    void customDelimiter() {
        BigInteger result = calculator.calculate("//;\\n1;2;3");

        assertThat(result).isEqualTo(BigInteger.valueOf(6));
    }

    @Test
    @DisplayName("Long 범위를 초과하는 큰 수의 합을 계산한다")
    void bigNumbers() {
        BigInteger result = calculator.calculate("99999999999999999999,1");

        assertThat(result).isEqualTo(new BigInteger("100000000000000000000"));
    }

    @Test
    @DisplayName("음수 입력 시 예외가 발생한다")
    void negativeNumber() {
        assertThatThrownBy(() -> calculator.calculate("1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("0 입력 시 예외가 발생한다")
    void zeroNumber() {
        assertThatThrownBy(() -> calculator.calculate("1,0,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}