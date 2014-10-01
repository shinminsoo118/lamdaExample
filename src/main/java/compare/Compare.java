package compare;


import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static compare.User.toUsers;

/**
 * Comparator 함수인터페이스 사용하기
 */
@Slf4j
public class Compare {

    public static void main(String[] args) {

        userSort();
        userSort2();
        userSort3();
        userSort4();
        userSort5();
    }

    /**
     * 함수 인터페이스를 이용한 람다 표현식
     */
    public static void userSort() {
        List<User> ascUsers =
                toUsers().stream().sorted((user1, user2) -> ageAsc(user1, user2)).collect(Collectors.toList());
        ascUsers.forEach(u -> log.info("{}", u.toString()));
    }

    /**
     * method referance 방식
     */
    public static void userSort2() {
        List<User> ascUsers =
            User.toUsers().stream().sorted(Compare::ageAsc).collect(Collectors.toList());
        ascUsers.forEach(u -> log.info("{}", u.toString()));
    }

    /**
     * method referance 방식으로 소팅과
     * anonymous class 방식으로 출력
     */
    public static void userSort3() {
        List<User> ascUsers =
                User.toUsers().stream().sorted(Compare::ageAsc).collect(Collectors.toList());
        ascUsers.forEach(new Consumer<User>() {
            @Override
            public void accept(User u) {
                log.info("{}", u.toString());
            }
        });
    }

    /**
     * Comparator 함수형 인터페이스를 이용한 방식
     */
    public static void userSort4() {
        Comparator<User> compareAscending =
                (user1, user2) -> ageAsc(user1, user2);
        Comparator<User> compareDescending = compareAscending.reversed();

        List<User> descUsers =
                User.toUsers().stream().sorted(compareDescending).collect(Collectors.toList());
        descUsers.forEach(u -> log.info("{}", u.toString()));
    }

    /**
     * Function, Comparator 함수형 인터페이스를 이용한 멀티소팅 방식
     */
    public static void userSort5() {
        final Function<User, Integer> byAge = user -> user.age;
        final Function<User, String> byName = user -> user.name;
        List<User> ascAgeAndNameUsers =
        User.toUsers().stream().sorted(Comparator.comparing(byAge).thenComparing(byName)).collect(Collectors.toList());
        ascAgeAndNameUsers.forEach(u -> log.info("{}", u.toString()));
    }

    private static int ageAsc(User user1, User user2) {
        if(user1.getAge() > user2.getAge()) {
            return 1;
        } else if(user1.getAge() < user2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}
