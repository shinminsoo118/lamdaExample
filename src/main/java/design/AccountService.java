package design;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by seokangchun on 2014. 10. 6..
 */
@Slf4j
public class AccountService {

    public static void main(String[] args) {
        log.info("account values {}", totalAccountValues(Account.toAccounts()));
        log.info("saving values {}", totalSavingValues(Account.toAccounts()));
        log.info("account selector {}",
                accountSelector(Account.toAccounts(), account-> account.getType() == Account.AccountType.SAVING));
    }

    public static int totalAccountValues(final List<Account> accounts) {
        return accounts.stream().filter(account -> true).mapToInt(Account::getValue).sum();
    }

    public static int totalSavingValues(final List<Account> accounts) {
        return accounts.stream().filter(account -> account.getType() == Account.AccountType.SAVING)
                .mapToInt(Account::getValue).sum();
    }

    public static int accountSelector(final List<Account> accounts, final Predicate<Account> accountSelector) {
        return accounts.stream().filter(accountSelector).mapToInt(Account::getValue).sum();
    }

}
