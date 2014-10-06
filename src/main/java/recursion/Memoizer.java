package recursion;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Created by kaha on 2014. 10. 4..
 */
public class Memoizer {
    public static <T, R> R callMemoized(
            final BiFunction<Function<T,R>, T, R> function, final T input
    ) {
        Function<T, R> memoized = new Function<T, R>() {
            private final Map<T, R> store = (Map<T, R>) new HashMap<>();
            public R apply(final T input) {
                return store.computeIfAbsent(input, key -> function.apply(this, key));
            }
        };
        return memoized.apply(input);
    }
}
