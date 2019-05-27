package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenAddNewUser() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        User expect = new User("Петров Петр", "456");
        assertThat(bank.searchUserByPassport("456"), is(expect));
    }
    @Test
    public void whenDeleteUser() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.deleteUser(new User("Петров Петр", "456"));
        User expect = null;
        assertThat(bank.searchUserByPassport("456"), is(expect));
    }
    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser("456", new Account("111111", 1000));
        assertThat(bank.getUserAccounts("456").get(0).getRequisites(), is("111111"));
    }
    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser("123", new Account("111111", 1000));
        bank.addAccountToUser("123", new Account("222222", 2000));
        List<Account> result = bank.getUserAccounts("123");
        List<Account> expect = new ArrayList<>(Arrays.asList(
                new Account("111111", 1000),
                new Account("222222", 2000))
        );
        assertThat(result, is(expect));
    }
    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser("123", new Account("111111", 1000));
        bank.addAccountToUser("123", new Account("222222", 2000));
        bank.deleteAccountFromUser("123", new Account("222222", 2000));
        List<Account> result = bank.getUserAccounts("123");
        List<Account> expect = new ArrayList<>();
        expect.add(new Account("111111", 1000));
        assertThat(result, is(expect));
    }
    @Test
    public void whenTrancferThenResultTrue() {
        Bank bank = new Bank();
        User user1 = new User("Иванов Иван", "123");
        User user2 = new User("Петров Петр", "456");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser("123", new Account("111111", 1000));
        bank.addAccountToUser("456", new Account("222222", 1000));
        bank.addAccountToUser("456", new Account("333333", 500));
        boolean result = bank.transferMoney("123", "111111", "456", "333333", 800);
        assertThat(result, is(true));
    }
}
