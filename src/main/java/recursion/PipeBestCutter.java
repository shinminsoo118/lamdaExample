package recursion;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kaha on 2014. 10. 4..
 */
@Slf4j
public class PipeBestCutter {

    private static final List<Integer> priceValues =
            Arrays.asList(1,2,2,4,5,6,5,12,11,15);

    private List<Integer> prices;

    public PipeBestCutter(final List<Integer> pricesForLength) {
        this.prices = pricesForLength;
    }

    public int maxProfit(final int length) {
        int profit = (length <= prices.size()) ? prices.get(length - 1) : 0;

        for(int i = 1; i < length; i++) {
            int priceWhenCut = maxProfit(i) + maxProfit(length - 1);
            if(profit < priceWhenCut) profit = priceWhenCut;
        }

        return profit;
    }

    public static void main(String[] args) {

        final PipeBestCutter cutter = new PipeBestCutter(priceValues);

        log.info("max Profit for 5 length pipe ? {}", cutter.maxProfit(5));

        log.info("max Profit for 22 length pipe ? {}", cutter.maxProfit(13));
    }
}
