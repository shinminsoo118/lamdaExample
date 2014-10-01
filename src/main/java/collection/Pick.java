package collection;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 렉시컬 스코프를 이용한 처리
 *
 * 렉시컬 스코프란
 * ? 사용한 하나의 컨텍스트에서 제공한 값을 캐시해
 *   두었다가 나중에 다른 컨텍스트에서 사용할 수있게 하는 기술
 * ? 프로그래머가 코드를 작성할 때 함수를 어디에 선언하는지에 따라 정의되는 스코프를 활용 하는 기술
 * ? 사용되는 시점에서의 유효범위를 사용하는 것이 아니라 정의된 시점에서의 유효범위(스코프)를 활용 하는 기술
 *
 * 참고할만한 글 : http://opentutorials.org/module/516/5447
 *
 */
@Slf4j
public class Pick {
    public static
    final List<String> friends = Arrays.asList("김", "강", "서", "문", "정", "박");
    public static void main(final String[] args) {
        name1(friends, "김");
        name1(friends, "이");

        name2();
        name3();
        name4();
    }

    /**
     * 렉시컬 스코프의 중복제거
     * @param names
     * @param startingLetter
     */
    public static void name1(final List<String> names, final String startingLetter) {
        final Optional<String> foundName =
                names.stream()
                .filter(name -> name.startsWith(startingLetter))
                .findFirst();
        foundName.ifPresent(name -> log.info("first name is {}", name) );
    }

    /**
     * 적용범위를 좁히기 위한 리팩토링
     * Function 인터페이스 활용
     */
    public static void name2() {
        final Function<String, Predicate<String>> startsWithLetter =
                (String letter) -> {
                    Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
                    return checkStarts;
                };

        final long countFriendsStart =
                friends.stream()
                        .filter(startsWithLetter.apply("강")).count();

        log.info("count {}", countFriendsStart);
    }

    /**
     * 적용범위를 좁히기 위한 리팩토링 (개선 1)
     * Function 인터페이스 활용
     */
    public static void name3() {
        final Function<String, Predicate<String>> startsWithLetter =
                (String letter) -> (String name) -> name.startsWith(letter);

        final long countFriendsStart =
                friends.stream()
                        .filter(startsWithLetter.apply("강")).count();
        log.info("count {}", countFriendsStart);

    }

    /**
     * 적용범위를 좁히기 위한 리팩토링 (개선 2)
     * Function 인터페이스 활용
     */
    public static void name4() {
        final Function<String, Predicate<String>> startsWithLetter =
                letter -> name -> name.startsWith(letter);
        final long countFriendsStart =
                friends.stream()
                        .filter(startsWithLetter.apply("강")).count();
        log.info("count {}", countFriendsStart);

    }
}
