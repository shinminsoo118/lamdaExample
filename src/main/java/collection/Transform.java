package collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 메서드 레퍼런스에 대한 한마디..
 *
 * 일반적으로 자바에서는 프로그래밍을 할 때 메서드 레퍼런스보다는 람다 표현식을 훨씬 더
 * 많이 사용한다. 그렇다고 해서 메서드 레퍼런스가 덜 중요하다는 의미는 아니다.
 * 메서드 레퍼런스는 람다 표현식이 짧아서 간단하게 만들거나 인스턴스 메서드 혹은 정적 메서드를
 * 직접 호출하는 경우에 유용하다.
 */
@Slf4j
public class Transform {
    public static
    final List<String> friends = Arrays.asList("김", "강", "서", "문", "정", "박");

    public static void main(final String[] args) {
        badCase();
        badTwoCase();
        goodCase();
    }

    public static void badCase() {
        final List<String> uppercaseNames = new ArrayList<String>();
        for(String name : friends) {
            uppercaseNames.add(name.toUpperCase());
        }
        log.info("{}",uppercaseNames);
    }

    public static void badTwoCase() {
        final List<String> uppercaseNames = new ArrayList<String>();
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
        log.info("{}",uppercaseNames);
    }

    public static void goodCase() {
        friends.stream().map(f->f.toUpperCase()).forEach(log::info);
    }
}
