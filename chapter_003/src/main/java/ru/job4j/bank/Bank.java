package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        users.keySet().stream().filter(x -> x.getPassport().equals(passport)).findFirst().ifPresent(user -> users.get(user).add(account));
    }

    public void deleteAccountFromUser(String passport, Account account) {
        users.keySet().stream().filter(x -> x.getPassport().equals(passport)).findFirst().ifPresent(user -> users.get(user).remove(account));
    }
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        User user = users.keySet().stream().filter(x -> x.getPassport().equals(passport)).findFirst().orElse(null);
        if (!Objects.isNull(user)) {
            accounts = users.get(user);
        }
        return accounts;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = searchAccountToRequisites(srcPassport, srcRequisite);
        Account dstAccount = searchAccountToRequisites(dstPassport, dstRequisite);
        if (!Objects.isNull(srcAccount) && !Objects.isNull(dstAccount)) {
           result = dstAccount.transferValue(srcAccount, amount);
        }
        return result;
    }

    public User searchUserByPassport(String passport) {
        return users.keySet().stream().filter(user -> user.getPassport().equals(passport)).findFirst().orElse(null);
    }
    public Account searchAccountToRequisites(String passport, String requisites) {
        List<Account> accounts = getUserAccounts(passport);
        return accounts.stream().filter(account -> account.getRequisites().equals(requisites)).findFirst().orElse(null);
    }
}
