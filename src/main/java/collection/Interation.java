package collection;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 *  @Class Name : Interation.java 이터레이션 활용
 *
 * @Descrition :
 * 컬렉션에 람다표현식을 활용하면 이터레이션을 더
 * 컴펙트하게 만들 수 있다.
 *
 * @Modification Information
 * @
 * @ 수정일       수정자     수정내용
 * @ 15/01/20   서강춘     주석쓰기
 *
 * @author 서강춘
 * @since 15/01/20
 * @version 1.0
 *
 */
@Slf4j
public class Interation {

    public static
    final List<String> friends = Arrays.asList("김", "강", "서", "문", "정", "박");

    public static void main(final String[] args) {

        // 자바 초급자 스타일
        for(int i = 0; i < friends.size(); i++) {
            log.info(friends.get(i));
        }
        log.info("1. ------");

        // Interator 인터페이스를 활용한 스타일
        for(String name : friends) {
            log.info(name);
        }
        log.info("2. ------");

        // 자바8의 Interable 인터페이스 활용하였으나
        // 코드가 장황하다
        friends.forEach(new Consumer<String>() {
            public void accept(final String name) {
                log.info(name);
            }
        });
        log.info("3. ------");

        // 자바8의 람다표현식을 활용
        friends.forEach((final String name) -> log.info(name));
        friends.forEach((name) -> log.info(name));
        friends.forEach(name -> log.info(name));
        log.info("4. ------");

        // 자바8의 람다표현식을 사용하였으나, 좀더 나아가 보자
        // 메서드 레퍼런스(Method Reference)를 사용하니 얼마나 간결한가.
        friends.forEach(log::info);
        log.info("5. ------");
    }

}
