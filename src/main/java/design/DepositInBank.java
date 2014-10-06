package design;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

import static design.Deposit.DepositType;
/**
 * Created by kaha on 2014. 10. 4..
 */
@Slf4j
public class DepositInBank {

    public static void main(String[] args) {

        log.info("Total Deposit is {}", totalDepositValues(Deposit.toDeposits()));
        log.info("Total Fixed Deposit is {}", totalFixedValues(Deposit.toDeposits()));
        log.info("Total Regular Saving Account is {}", totalRegularValues(Deposit.toDeposits()));

        log.info("refactoring --------------");

        log.info("Total Deposit is {}", totalValues(Deposit.toDeposits(), deposit -> true));
        log.info("Total Fixed Deposit is {}",totalValues(Deposit.toDeposits(), deposit -> deposit.getType() == DepositType.FIXED));
        log.info("Total Regular Saving Account is {}", totalValues(Deposit.toDeposits(), deposit -> deposit.getType() == DepositType.REGULAR));

    }

    public static int totalDepositValues(final List<Deposit> deposits) {
        return deposits.stream().mapToInt(Deposit::getValue).sum();
    }

    public static int totalFixedValues(final List<Deposit> deposits) {
        return deposits
                .stream()
                .mapToInt(d -> d.getType() == DepositType.FIXED ? d.getValue() : 0)
                .sum();
    }

    public static int totalRegularValues(final List<Deposit> deposits) {
        return deposits
                .stream()
                .mapToInt(d -> d.getType() == DepositType.REGULAR ? d.getValue() :0)
                .sum();
    }

    /**
     * Predicate 함수인터페이스를 이용한 필터링된 합계예금
     * 리팩토링된 함수
     * @param deposits 예금 리스트
     * @param depositSelector Predicate를 이용한 조건 파라메터
     * @return
     */
    public static int totalValues(final List<Deposit> deposits, final Predicate<Deposit> depositSelector) {
        return deposits.stream().filter(depositSelector).mapToInt(Deposit::getValue).sum();
    }


}
