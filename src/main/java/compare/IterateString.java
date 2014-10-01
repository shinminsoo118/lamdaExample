package compare;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by seokangchun on 2014. 10. 1..
 */
@Slf4j
public class IterateString {

    private final static String name = "Seo Kang 123!";

    public static void main(final String[] args) {
        charartorIterate();
        disitIterate();
    }

    public static void charartorIterate() {
        name.chars().forEach(c -> log.info("{} {}", Character.valueOf((char)c), c));
    }

    public static void disitIterate() {
        name.chars().filter(c -> Character.isDigit(c))
                .forEach(c -> log.info("{} {}", Character.valueOf((char)c), c));
    }
}
