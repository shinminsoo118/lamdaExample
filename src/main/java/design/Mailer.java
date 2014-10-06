package design;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * 메서드 체인을 이용한 직관적이고 보기좋게 만들기
 */
@Slf4j
public class Mailer {

    public Mailer from(final String address) { return this; }
    public Mailer to(final String to) { return this; }
    public Mailer subject(final String title) { return this; }
    public Mailer body(final String body) { return this; }

    public static void send(final Consumer<Mailer> block) {
        final Mailer mailer = new Mailer();
        block.accept(mailer);
        log.info("mail send ..");
    }

    public static void main(String[] args) {
        Mailer.send(mail -> mail
                .from("a@abc.co.kr")
                .to("b@abc.co.kr")
                .subject("subject")
                .body("message"));
    }
}
