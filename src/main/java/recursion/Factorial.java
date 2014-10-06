package recursion;

import lombok.extern.slf4j.Slf4j;

import static recursion.TailCalls.call;
import static recursion.TailCalls.done;

/**
 * 재귀함수의 3가지 케이스
 * <pre>
 * 1의 메소드는 스택오버를 발생한다.
 * 2,3의 TCO기술을 사용해 더 이상은 테일 스택오버를 발생하지 않는다.
 * 그러나 여전히 값을 입력하면 문제(산술 오버플로우)가 발생한다.
 * </pre>
 *
 */
@Slf4j
public class Factorial {
    public static void main(String[] args){

        log.info("5!의 값은 ? {}", factorialRec(5));
        // result : Exception in thread "main" java.lang.StackOverflowError
        try { log.info("20000!의 값은 ? {}", factorialRec(20000)); } catch (StackOverflowError ex) { ex.printStackTrace(); }

        // 스택오버 플로우는 발생하지 않는다 그러나
        // 높은 수에서는 0을 리턴한다.
        log.info("2의 5!의 값은 ? {}", factorialRec2(1,5).invoke());
        log.info("2의 20000!의 값은 ? {}", factorialRec2(1,20000).invoke());

        // 테일 재귀를 캡슐화
        log.info("3의 5!의 값은 ? {}", factorialRec3(5));
        log.info("3의 20000!의 값은 ? {}", factorialRec3(20000));

    }

    /**
     * 스택을 활용한 재귀호출 방식
     * 팩토리얼하고자 하는 값이 높으면 스택오버플로우가 발생한다.
     * @param number
     * @return
     */
    public static int factorialRec(final int number) {
        if(number == 1) {
            return number;
        } else  {
            return number * factorialRec(number -1);
        }
    }

    /**
     *
     * TCO(Tail Call Optimization) 기술을 사용한 방식
     *
     * <pre>
     * 재귀를 사용할 때의 가장 큰 문제점은 입력 데이터가 매우 많은 경우에 스택오버플로우가
     * 발생할 위험이 있다는 것이다.
     *
     * TCO 기술은 테일콜이 마지막 오퍼레이션이 실행되는 위치에 있는 재귀호출이 자기자신을
     * 호출하는 방법을 말한다.
     *
     * 람다 표현식을 사용하면 몇 라인의 코드만으로도 TCO를 구현할 수 있다.
     *
     * 이방식을 사용하면 스택이 넘칠지 모른다는 걱정 없이 재귀의
     * 강력한 기능르 사용 할 수 있다.
     *
     * 테일 재귀를 사용하기 위해서 2개의 메서드를 사용하는 코드를 작성했다.
     * 마지막 오퍼레이션은 자기 자신에 대한 레이지 호출이며, 리턴 결과를 수행하기 위한
     * 추가적인 연산은 없다. 또한 팩토리얼 메서드인 TailRec()을 계속 호출하는 것보다
     * 지연(lazy/later) 실행을 위해 이 부분을 람다 표현식으로 래핑했다.
     *
     * </pre>
     * @param factorial
     * @param number
     * @return
     */
    public static TailCall<Integer> factorialRec2(final int factorial, final int number) {
        if(number == 1) {
            return done(factorial);
        } else {
            return call(() -> factorialRec2(factorial * number, number - 1));
        }
    }

    /**
     * 테일 재귀을 캡슐화
     * <pre>
     * 테일 재귀 메소드의 파라메터를 처리후 invoke메서드를 호출하는 코드를
     * 리턴하도록 캡슐화 함.
     *
     * 그러나, 파라메터로 1, 0이 입력되면 문제가 발생한다.
     *
     * </pre>
     * @param number
     * @return
     */
    public static int factorialRec3(final int number) {
        return factorialRec2(1, number).invoke();
    }
}
