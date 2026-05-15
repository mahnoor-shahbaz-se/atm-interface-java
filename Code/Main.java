package ATM_Interface;

/*How to run:
        - Run this file
        - When asked for PIN, enter: 1234
        - Then pick from the menu (1 to 5)

Test account:
        - Account No : ACC-001
        - Name       : Ali Hassan
        - Balance    : $1500.00
        - PIN        : 1234
*/

public class Main {
    public static void main(String[] args) {

        BankAccount account1 = new BankAccount(
                "Ali Hassan",
                "ACC-001",
                1500.00,
                1234
        );

        BankAccount account2 = new BankAccount(
                "Sara Khan",
                "ACC-002",
                500.00,
                5678
        );

        ATM atm = new ATM();
        atm.startSession(account1);
    }
}
