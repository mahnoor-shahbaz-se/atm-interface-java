package ATM_Interface;

import java.util.Scanner;

public class ATM {

    private ATM_State idleState;
    private ATM_State hasCardState;
    private ATM_State authenticatedState;
    private ATM_State currentState;

    private BankAccount currentAccount;
    private Scanner scanner;

    public ATM() {
        idleState          = new IdleState();
        hasCardState       = new HasCardState();
        authenticatedState = new AuthenticatedState();
        currentState       = idleState;
        scanner            = new Scanner(System.in);
    }

    public ATM_State getIdleState()          { return idleState; }
    public ATM_State getHasCardState()       { return hasCardState; }
    public ATM_State getAuthenticatedState() { return authenticatedState; }

    public void setState(ATM_State state) {
        this.currentState = state;
    }

    public BankAccount getCurrentAccount() {
        return currentAccount;
    }

    public void startSession(BankAccount account) {
        this.currentAccount = account;
        printWelcomeBanner();

        System.out.println("\n  [SYSTEM] Simulating card insertion for account: "
                + account.getAccountNumber());
        currentState.insertCard(this);

        boolean authenticated = false;
        while (currentState == hasCardState) {
            System.out.print("\n  Enter PIN: ");
            int pin = getSafeInt();
            currentState.enterPin(this, pin);

            if (currentState == authenticatedState) {
                authenticated = true;
                break;
            }
            if (currentState == idleState) {
                System.out.println("  [ATM] Session terminated for security.");
                return;
            }
        }

        if (authenticated) {
            runTransactionMenu();
        }
    }

    private void runTransactionMenu() {
        boolean running = true;
        while (running && currentState == authenticatedState) {
            printMenu();
            System.out.print("  Select option: ");
            int choice = getSafeInt();

            switch (choice) {
                case 1: handleCheckBalance(); break;
                case 2: handleDeposit();      break;
                case 3: handleWithdraw();     break;
                case 4: handleHistory();      break;
                case 5:
                    currentState.ejectCard(this);
                    running = false;
                    break;
                default:
                    System.out.println("  [ATM] Invalid option. Please select 1-5.");
            }
        }
    }

    private void handleCheckBalance() {
        System.out.println("\n  ------------------------------------------");
        System.out.println("  |           BALANCE INQUIRY              |");
        System.out.println("  ------------------------------------------");
        System.out.println("  | Account : " + currentAccount.getAccountNumber());
        System.out.println("  | Holder  : " + currentAccount.getAccountHolder());
        System.out.printf ("  | Balance : $%.2f%n", currentAccount.getBalance());
        System.out.println("  ------------------------------------------");
    }

    private void handleDeposit() {
        System.out.print("\n  Enter deposit amount: $");
        double amount = getSafeDouble();
        if (currentAccount.deposit(amount)) {
            System.out.println("  [ATM] Deposit SUCCESSFUL.");
            System.out.printf ("  [ATM] New balance: $%.2f%n", currentAccount.getBalance());
        } else {
            System.out.println("  [ATM] FAILED: Amount must be greater than $0.00.");
        }
    }

    private void handleWithdraw() {
        System.out.print("\n  Enter withdrawal amount: $");
        double amount = getSafeDouble();
        if (currentAccount.withdraw(amount)) {
            System.out.println("  [ATM] Withdrawal SUCCESSFUL. Please collect your cash.");
            System.out.printf ("  [ATM] New balance: $%.2f%n", currentAccount.getBalance());
        } else {
            if (amount <= 0) {
                System.out.println("  [ATM] FAILED: Amount must be greater than $0.00.");
            } else {
                System.out.printf("  [ATM] FAILED: Insufficient funds. Available: $%.2f%n",
                        currentAccount.getBalance());
            }
        }
    }

    private void handleHistory() {
        String[] history = currentAccount.getTransactionHistory();
        System.out.println("\n  ------------------------------------------");
        System.out.println("  |          TRANSACTION HISTORY           |");
        System.out.println("  ------------------------------------------");
        for (int i = 0; i < history.length; i++) {
            System.out.println("  | " + (i + 1) + ". " + history[i]);
        }
        System.out.println("  ------------------------------------------");
    }

    private int getSafeInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("  [ERROR] Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double getSafeDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("  [ERROR] Invalid input. Enter a valid amount: $");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private void printWelcomeBanner() {
        System.out.println("\n  ==========================================");
        System.out.println("  |                  ATM                   |");
        System.out.println("  ==========================================");
    }

    private void printMenu() {
        System.out.println("\n  ----------------------------");
        System.out.println("  |       MAIN MENU          |");
        System.out.println("  ----------------------------");
        System.out.println("  |  1. Check Balance        |");
        System.out.println("  |  2. Deposit              |");
        System.out.println("  |  3. Withdraw             |");
        System.out.println("  |  4. Transaction History  |");
        System.out.println("  |  5. Exit / Eject Card    |");
        System.out.println("  ----------------------------");
    }
}
