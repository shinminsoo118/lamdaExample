package collection;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 결과값을 조인하기
 */
@Slf4j
public class Joining {
    public static
    final List<String> friends = Arrays.asList("Kim", "Kang", "Seo", "Moon", "Jung", "Bak");

    public static void main(final String[] args) {
        String joinList;
        joinList = friends.stream().map(String::toUpperCase).collect(Collectors.joining(", "));
        log.info("{}", joinList);
    }
}
