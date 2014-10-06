package recursion;

import lombok.extern.slf4j.Slf4j;
import java.math.BigInteger;

import static recursion.TailCalls.call;
import static recursion.TailCalls.done;

/**
 * TCO 기술을 이용한 테일재귀 구현
 * 스택 오버플로우와 큰 수에 대한 산술 오버플로우를 해결
 */
@Slf4j
public class BigFactorial {

    public static void main(String[] args) {
        log.info("5!의 결과 ? {}", factorialTailRec(BigInteger.ONE, new BigInteger("5")).invoke());
        log.info("20000!의 결과 ? {}", factorialTailRec(BigInteger.ONE, new BigInteger("20000")).invoke());
        log.info("캡슐화 20000!의 결과 ?{}", factorial(new BigInteger("20000")));
    }

    /**
     * BigIneger를 이용한 산술곱
     *
     * @param first
     * @param second
     * @return
     */
    public static BigInteger multiply(final BigInteger first, final BigInteger second) {
        return first.multiply(second);
    }

    /**
     * BigInteger를 이용한 -1의 값감소
     * @param number
     * @return
     */
    public static BigInteger decrement(final BigInteger number) {
        return number.subtract(BigInteger.ONE);
    }

    public static TailCall<BigInteger> factorialTailRec(final BigInteger factorial, final BigInteger number) {

        if(number.equals(BigInteger.ONE)) {
            return done(factorial);
        } else {
            return call(() -> factorialTailRec(multiply(factorial, number), decrement(number)));
        }

    }

    public static BigInteger factorial(final BigInteger number) {
        return factorialTailRec(multiply(BigInteger.ONE, number), decrement(number)).invoke();
    }
}
