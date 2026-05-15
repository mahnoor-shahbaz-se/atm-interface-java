package ATM_Interface;

public interface ATM_State {
    void insertCard(ATM atm);
    void enterPin(ATM atm, int pin);
    void selectTransaction(ATM atm);
    void ejectCard(ATM atm);
}