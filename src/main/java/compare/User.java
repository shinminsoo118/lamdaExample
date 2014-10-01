package compare;

import lombok.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by seokangchun on 2014. 10. 1..
 */
@AllArgsConstructor
@ToString
@Data
public class User {
    String name;
    String sex;
    int age;

    public static List<User> toUsers() {
        return Arrays.asList(new User("Seo", "man", 10),
                new User("Kim", "man", 30),
                new User("Baak", "man", 32),
                new User("Moon", "man", 40),
                new User("Lee", "woman", 20),
                new User("Kang", "woman", 32),
                new User("Youn", "woman", 25));
    }
}
