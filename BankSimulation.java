import java.util.ArrayList;
import java.util.List;

class Account {
    protected String accountHolder;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for " + accountHolder + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountHolder, double initialBalance, double interestRate) {
        super(accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

 
    public void withdraw(double amount) {
        if (balance - amount >= 500) { 
            super.withdraw(amount);
        } else {
            System.out.println("Cannot withdraw. Minimum balance of 500 required.");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        deposit(interest);
        transactionHistory.add("Interest added: " + interest);
    }
}

public class BankSimulation {
    public static void main(String[] args) {
        SavingsAccount acc1 = new SavingsAccount("Vivekraj Singh", 2000, 5);

        acc1.deposit(1000);
        acc1.withdraw(300);
        acc1.addInterest();
        acc1.withdraw(2500); // Should fail due to minimum balance rule
        acc1.printTransactionHistory();
    }
}

