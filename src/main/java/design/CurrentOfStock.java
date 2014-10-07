package design;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * Created by kaha on 2014. 10. 7..
 */
@Slf4j
public class CurrentOfStock {
    public static void main(String[] args) {
        log.info("Apple 100주의 금액은 ? {}",  (new CurrentOfStock(YahooStockService::getPrice)).computeStockWorth("AAPL", 100));
    }
    private Function<String, BigDecimal> priceFinder;
    public CurrentOfStock(final Function<String, BigDecimal> priceFinder) {
        this.priceFinder = priceFinder;
    }
    public BigDecimal computeStockWorth(final String ticker, final int volume) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(volume));
    }
}
