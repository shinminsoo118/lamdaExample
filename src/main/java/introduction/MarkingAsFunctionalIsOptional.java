package introduction;

/**
 * @FunctionInterface가 Optional 임을 보여주는 클래스
 *
 * @FunctionInterface로 함수형 인터페이스를 마킹할 수 있다.
 * 컴파일러는 이 어노테이션이 필요하지 않지만 명시적으로 인터페이스가 해야
 * 할 목적을 서술한다는 측면에서는 도움이 된다.
 */
public class MarkingAsFunctionalIsOptional {
    public interface Sample1 {
        boolean test(int input);
    }

    @FunctionalInterface
    public interface Sample2 {
        boolean test(int input);
    }

    public static void main(final String[] args) {
        Sample1 sample1 = input -> true;
        Sample2 sample2 = input -> true;

        System.out.println(sample1.test(4));
        System.out.println(sample2.test(4));
    }
}
