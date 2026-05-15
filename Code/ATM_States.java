package ATM_Interface;

class IdleState implements ATM_State {

    @Override
    public void insertCard(ATM atm) {
        System.out.println("\n  [ATM] Card detected. Please enter your PIN.");
        atm.setState(atm.getHasCardState());
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("  [ATM] ERROR: Please insert your card first.");
    }

    @Override
    public void selectTransaction(ATM atm) {
        System.out.println("  [ATM] ERROR: Please insert your card first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("  [ATM] No card to eject.");
    }
}


class HasCardState implements ATM_State {

    private int attempts = 0;
    private static final int MAX_ATTEMPTS = 3;

    @Override
    public void insertCard(ATM atm) {
        System.out.println("  [ATM] Card is already inserted.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        attempts++;
        if (atm.getCurrentAccount().validatePin(pin)) {
            System.out.println("  [ATM] PIN accepted. Welcome, "
                    + atm.getCurrentAccount().getAccountHolder() + "!");
            attempts = 0;
            atm.setState(atm.getAuthenticatedState());
        } else {
            System.out.println("  [ATM] Incorrect PIN. Attempts remaining: "
                    + (MAX_ATTEMPTS - attempts));
            if (attempts >= MAX_ATTEMPTS) {
                System.out.println("  [ATM] Too many wrong attempts. Card ejected.");
                attempts = 0;
                atm.setState(atm.getIdleState());
            }
        }
    }

    @Override
    public void selectTransaction(ATM atm) {
        System.out.println("  [ATM] ERROR: Please enter your PIN first.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("  [ATM] Card ejected.");
        atm.setState(atm.getIdleState());
    }
}


class AuthenticatedState implements ATM_State {

    @Override
    public void insertCard(ATM atm) {
        System.out.println("  [ATM] Card is already inserted and authenticated.");
    }

    @Override
    public void enterPin(ATM atm, int pin) {
        System.out.println("  [ATM] Already authenticated. Please select a transaction.");
    }

    @Override
    public void selectTransaction(ATM atm) {
        System.out.println("  [ATM] Ready for transaction.");
    }

    @Override
    public void ejectCard(ATM atm) {
        System.out.println("  [ATM] Thank you for using DecodeLabs ATM. Card ejected.");
        atm.setState(atm.getIdleState());
    }
}
