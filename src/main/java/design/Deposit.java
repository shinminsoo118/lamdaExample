package design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kaha on 2014. 10. 4..
 */
@AllArgsConstructor
@ToString
@Data
public class Deposit {
    public enum DepositType {FIXED, REGULAR, CHECKING}
    private DepositType type;
    private int value;

    public static List<Deposit> toDeposits() {
        return Arrays.asList(
                new Deposit(DepositType.FIXED, 5000),
                new Deposit(DepositType.FIXED, 2000),
                new Deposit(DepositType.CHECKING, 6000),
                new Deposit(DepositType.REGULAR, 1000),
                new Deposit(DepositType.REGULAR, 3000));
    }
}
