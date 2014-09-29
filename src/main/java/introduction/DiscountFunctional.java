package introduction;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import static introduction.Price.prices;

/**
 * Created by seokangchun on 2014. 9. 29..
 */
@Slf4j
public class DiscountFunctional {

    public BigDecimal discountedPrice() {
        return prices.stream()
                .filter(price->price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.1)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void main(final String[] args) {
        final BigDecimal totalOfDiscountedPrices =
                prices.stream()
                .filter(price->price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.1)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total of discounted 가격 : " + totalOfDiscountedPrices);
        log.info("Total of discounted 가격 : {}", totalOfDiscountedPrices);
    }
}
