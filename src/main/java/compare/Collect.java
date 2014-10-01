package compare;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Collectors 함수인터페이스 사용하기
 */
@Slf4j
public class Collect {

    public static void main(String[] args) {
        userAgeOlderThan30_1();
        userAgeOlderThan30_2();
        userAgeGroup();
        userAgeGroup2();
        userNameByOldestAge();
    }

    /**
     * 스레드에 불안전한 코드
     * 30세 이상의 사용자를 구하기
     */
    public static void userAgeOlderThan30_1() {
        List<User> users = new ArrayList<>();
        User.toUsers().stream().filter(user -> user.age > 30)
                .forEach(user -> users.add(user) );

        log.info("{}", users);
    }

    /**
     * 스레드에 안전한 코드
     * Collector는 supplier, accumulator, combiner에 대한 인터페이스의 역할을 함.
     * 30세 이상의 사용자를 구하기
     */
    public static void userAgeOlderThan30_2() {
        List<User> users =
        User.toUsers().stream().filter(user -> user.age > 30)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        log.info("{}", users);
    }

    /**
     * 스레드에 안전한 코드
     * toList는 컨비니언스 메서드를 제공(?)
     * 컨비니언스란?
     * 30세 이상의 사용자를 구하기
     */
    public static void userAgeOlderThan30_3() {
        List<User> users =
                User.toUsers().stream().filter(user -> user.age > 30)
                        .collect(Collectors.toList());
        log.info("{}", users);
    }

    /**
     * grouping를 이용한 그룹핑
     */
    public static void userAgeGroup() {
        Map<Integer, List<User>> userByAges =
                User.toUsers().stream().collect(Collectors.groupingBy(User::getAge));
        log.info("{}", userByAges);
    }

    /**
     * grouping를 이용한 그룹핑과
     * 그룹핑된 사용자이름
     */
    public static void userAgeGroup2() {
        Map<Integer, List<String>> userByAgeAndName =
                User.toUsers().stream().collect(Collectors.groupingBy(User::getAge,
                        Collectors.mapping(User::getName, Collectors.toList())));
        log.info("{}", userByAgeAndName);
    }

    /**
     * 성의 첫글자 그룹별로 가장 나이가 많은 사용자
     */
    public static void userNameByOldestAge() {
        Comparator<User> byAge = Comparator.comparing(User::getAge);
        Map<Character, Optional<User>> nameByOldestAge =
                User.toUsers().stream().collect(Collectors.groupingBy(user -> user.getName().charAt(0),
                            Collectors.reducing(BinaryOperator.maxBy(byAge))));
        log.info("{}", nameByOldestAge);
    }

}
