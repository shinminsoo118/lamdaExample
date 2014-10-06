package design;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 데코레이션 패턴을 람다를 이용해 간단하게 처리하기
 */
@Slf4j
public class Camera2 {

    public Camera2() { setFilter(); }

    private Function<Color, Color> filter;

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public void setFilter(final Function<Color, Color>... filters) {
        filter = Stream.of(filters).reduce((filter, next) -> filter.compose(next))
                .orElseGet(Function::identity);
    }

    public static void main(String[] args) {
        final Camera2 camera = new Camera2();
        final Consumer<String> printCaptured = (filterInfo) -> log.info("with {}: {}",
                filterInfo, camera.capture(new Color(200, 100, 200)));

        printCaptured.accept("no filter");
        camera.setFilter(Color::brighter);
        printCaptured.accept("brighter filter");
        camera.setFilter(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");

    }
}
