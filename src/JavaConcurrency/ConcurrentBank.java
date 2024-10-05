package JavaConcurrency;

import java.util.ArrayList;
import java.util.List;

public class ConcurrentBank {
    private final List<BankAccount> accounts = new ArrayList<>();
    private int nextAccountNumber = 1;

    // Метод для создания нового счета
    public synchronized BankAccount createAccount(int initialBalance) {
        BankAccount account = new BankAccount(nextAccountNumber++, initialBalance);
        accounts.add(account);
        return account;
    }

    // Метод для перевода средств между счетами
    public void transfer(BankAccount fromAccount, BankAccount toAccount, int amount) {
        BankAccount firstLock, secondLock;

        if (fromAccount.getAccountNumber() < toAccount.getAccountNumber()) {
            firstLock = fromAccount;
            secondLock = toAccount;
        } else {
            firstLock = toAccount;
            secondLock = fromAccount;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (fromAccount.withdraw(amount)) {
                    toAccount.deposit(amount);
                    System.out.println("Transferred " + amount + " from Account " +
                            fromAccount.getAccountNumber() + " to Account " +
                            toAccount.getAccountNumber());
                } else {
                    System.out.println("Failed to transfer " + amount + " from Account " +
                            fromAccount.getAccountNumber() + " to Account " +
                            toAccount.getAccountNumber() + ": insufficient funds.");
                }
            }
        }
    }

    // Метод для получения общего баланса всех счетов
    public synchronized int getTotalBalance() {
        int totalBalance = 0;
        for (BankAccount account : accounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }
}


