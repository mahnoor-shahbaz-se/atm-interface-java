package ATM_Interface;

public class BankAccount {

    private String accountHolder;
    private String accountNumber;
    private double balance;
    private int pin;
    private String[] transactionHistory;
    private int historyCount;

    private static final int MAX_HISTORY = 50;

    public BankAccount(String accountHolder, String accountNumber, double initialBalance, int pin) {
        this.accountHolder      = accountHolder;
        this.accountNumber      = accountNumber;
        this.balance            = initialBalance;
        this.pin                = pin;
        this.transactionHistory = new String[MAX_HISTORY];
        this.historyCount       = 0;
        addToHistory("Account opened. Initial balance: $" + String.format("%.2f", initialBalance));
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int enteredPin) {
        return this.pin == enteredPin;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        addToHistory("Deposited:  $" + String.format("%.2f", amount)
                + " | Balance: $" + String.format("%.2f", balance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        addToHistory("Withdrawn:  $" + String.format("%.2f", amount)
                + " | Balance: $" + String.format("%.2f", balance));
        return true;
    }

    private void addToHistory(String record) {
        if (historyCount < MAX_HISTORY) {
            transactionHistory[historyCount++] = record;
        }
    }

    public String[] getTransactionHistory() {
        String[] copy = new String[historyCount];
        for (int i = 0; i < historyCount; i++) {
            copy[i] = transactionHistory[i];
        }
        return copy;
    }
}