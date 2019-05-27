package ru.job4j.bank;

import java.util.Objects;

public class Account {
    private String requisites;
    private double value;

    public Account() {
    }
    public Account(String requisites, double value) {
        this.requisites = requisites;
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    public boolean transferValue(Account srcAccount, double amount) {
        boolean result = false;
        double srcValue = srcAccount.getValue() - amount;
        if (srcValue >= 0) {
            value = value + amount;
            srcAccount.setValue(srcValue);
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.value, value) == 0
                && Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites, value);
    }
}
