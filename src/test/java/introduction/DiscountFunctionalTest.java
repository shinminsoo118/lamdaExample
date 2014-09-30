package introduction;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@Slf4j
public class DiscountFunctionalTest {

    @Test
    public void testDiscountFunctional() {
        DiscountFunctional discountFunctional = new DiscountFunctional();
        BigDecimal price = discountFunctional.discountedPrice();

        log.info("\"Total of discounted 가격 : {}",  price);
    }
}