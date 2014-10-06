package design;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by seokangchun on 2014. 10. 6..
 */
@Slf4j
public class Camera {

    public Camera() { setFilter(); }

    private Function<Color, Color> filter;

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public void setFilter(final Function<Color, Color>... filters) {
        filter = Stream.of(filters).reduce((filter, next) -> filter.compose(next))
                .orElse(color->color);
    }


    public static void main(String[] args) {
        final Camera camera = new Camera();
        final Consumer<String> printCaptured = (filterInfo) -> log.info("with {}: {}",
                filterInfo, camera.capture(new Color(200, 100, 200)));

        printCaptured.accept("no filter");
        camera.setFilter(Color::brighter);
        printCaptured.accept("brighter filter");
        camera.setFilter(Color::brighter, Color::darker);
        printCaptured.accept("brighter & darker filter");

    }
}
