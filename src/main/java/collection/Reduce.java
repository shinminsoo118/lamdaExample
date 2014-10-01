package collection;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 컬렉션을 하나으 값으로 리듀스 하기
 *
 */
@Slf4j
public class Reduce {
    public static
    final List<String> friends = Arrays.asList("김", "강", "서", "문", "정", "박");

    public static void main(String[] args) {
        redu1();
        redu2();
    }

    /**
     * 문자의 전체수를 산하기
     */
    public static void redu1() {
        int count = friends.stream().mapToInt(name -> name.length()).sum();
        log.info("count {}", count);
    }

    /**
     * Max Charactor 선택하기
     */
    public static void redu2() {
        final Optional<String> averageName =
                friends.stream().reduce((name1, name2) -> (int)name1.charAt(0) >= (int)name2.charAt(0) ? name1 : name2 );
        log.info("average {}", averageName.get());
    }
}
