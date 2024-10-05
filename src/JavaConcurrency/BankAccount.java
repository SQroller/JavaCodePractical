package JavaConcurrency;

public class BankAccount {
    private int balance;
    private final int accountNumber;

    public BankAccount(int accountNumber, int initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Метод для пополнения счета
    public synchronized void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // Метод для снятия средств со счета
    public synchronized boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Метод для получения текущего баланса счета
    public synchronized int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
