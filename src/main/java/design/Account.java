package design;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * Created by seokangchun on 2014. 10. 6..
 */
@AllArgsConstructor
@ToString
@Data
public class Account {
    public enum AccountType { SAVING, DEPOSIT, CHECKING }
    private AccountType type;
    private int value;
    public static List<Account> toAccounts() {
        return Arrays.asList(
                new Account(AccountType.SAVING, 5000),
                new Account(AccountType.SAVING, 2000),
                new Account(AccountType.CHECKING, 2000),
                new Account(AccountType.CHECKING, 3000),
                new Account(AccountType.DEPOSIT, 4000)
                );
    }
}
