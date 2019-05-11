package ru.job4j.bank;

import java.util.*;

public class Bank {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        for (User entry : users.keySet()) {
            if (entry.getPassport().equals(passport)) {
                users.get(entry).add(account);
                break;
            }
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        for (User entry : users.keySet()) {
            if (entry.getPassport().equals(passport)) {
                users.get(entry).remove(account);
                break;
            }
        }
    }
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        for (User entry : users.keySet()) {
            if (entry.getPassport().equals(passport)) {
                accounts = users.get(entry);
                break;
            }
        }
        return accounts;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = searchAccountToRequisites(srcPassport, srcRequisite);
        Account dstAccount = searchAccountToRequisites(dstPassport, dstRequisite);
        if (srcAccount!= null && dstAccount!= null) {
           result = dstAccount.transferValue(srcAccount, amount);
        }
        return result;
    }

    public User searchUserByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
            }
        }
        return result;
    }
    public Account searchAccountToRequisites(String passport, String requisites) {
        Account result = null;
        List<Account> accounts = getUserAccounts(passport);
        for (Account account : accounts) {
            if (account.getRequisites().equals(requisites)) {
                result = account;
            }
        }
        return result;
    }
}
