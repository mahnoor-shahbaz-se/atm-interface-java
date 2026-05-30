# 🏧 ATM Interface

A console-based Java ATM simulation implementing the **State Design Pattern** with secure PIN authentication, deposits, withdrawals, balance inquiry, and transaction history.

---

## 📸 Sample Output

<img width="632" height="1847" alt="carbon (5)" src="https://github.com/user-attachments/assets/d451f843-b1d2-484d-b539-77647620baa0" />

---

## ✨ Features

- 🔐 **PIN authentication** — 3 attempts before card ejection
- 💰 **Deposit & Withdrawal** — with balance validation
- 📋 **Transaction history** — tracks all account activity
- 💳 **Balance inquiry** — formatted account summary
- 🔄 **State Design Pattern** — Idle, HasCard, Authenticated states
- ✅ **Input validation** — handles invalid inputs gracefully
- 👥 **Multiple accounts** — supports multiple bank accounts

---

## 🏗️ Project Structure

```
ATM_Interface/
|
|-- Main.java              - Entry point, creates accounts and starts ATM session
|-- ATM.java               - Core ATM logic and transaction handling
|-- ATM_State.java         - State interface
|-- ATM_States.java        - IdleState, HasCardState, AuthenticatedState
|-- BankAccount.java       - Account data, PIN validation, transaction history
```

---

## 🎯 Design Pattern Used

This project implements the **State Design Pattern**:

| State | Description |
|-------|-------------|
| IdleState | ATM is waiting — no card inserted |
| HasCardState | Card inserted — waiting for PIN |
| AuthenticatedState | PIN verified — ready for transactions |

---

## 🛠️ Tech & Concepts Used

| Concept | Applied |
|--------|---------|
| State Design Pattern | ATM state management |
| OOP | Classes, interfaces, encapsulation |
| Multiple Classes | Separated into 5 files |
| Input Validation | Scanner with hasNextInt/hasNextDouble |
| Arrays | Transaction history storage |
| Encapsulation | Private fields with getters |

---

## 🚀 How to Run

### ✅ Step 1 — Install Java (One Time Only)
1. Go to 👉 https://www.java.com/en/download/
2. Click **Download Java**
3. Install it like any normal program (Next → Next → Finish)
4. **Restart your computer** after installing

---

### ✅ Step 2 — Download the Project
Open **Command Prompt** (search CMD in Windows search bar) and paste this:

```bash
git clone https://github.com/mahnoor-shahbaz-se/atm-interface-java.git
```

---

### ✅ Step 3 — Go Into the Project Folder

```bash
cd atm-interface-java
```

---

### ✅ Step 4 — Compile All Files

```bash
javac ATM_Interface/*.java
```

---

### ✅ Step 5 — Run the Program

```bash
java ATM_Interface.Main
```

---

### 🔑 Test Account Details

| Field | Value |
|-------|-------|
| Account No | ACC-001 |
| Name | Ali Hassan |
| Balance | $1500.00 |
| PIN | 1234 |

---

## 🔮 Future Improvements

- [ ] Multiple account switching at ATM
- [ ] Card block after failed attempts
- [ ] Mini statement printing
- [ ] GUI version using Java Swing
- [ ] Database integration for real accounts

---

## 👩‍💻 Author

**Mahnoor Shahbaz**
🔗 [LinkedIn](https://www.linkedin.com/in/mahnoor-shahbaz-se/) &nbsp;|&nbsp; 📧 [mahnoorshahbaz86@gmail.com](mailto:mahnoorshahbaz86@gmail.com)
