import java.util.ArrayList;
import java.util.Scanner;

class BankingApplication {
    public static void main(String[] args) {
        Bank bank = new Bank();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Create a new account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Update an account");
            System.out.println("4. Delete an account");
            System.out.println("5. Deposit an amount into your account");
            System.out.println("6. Withdraw an amount from your account");
            System.out.println("7. Search for account");
            System.out.println("8. Exit");

            System.out.print("Enter your choice (1-8): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.displayAllAccounts();
                    break;
                case 3:
                    bank.updateAccount();
                    break;
                case 4:
                    bank.deleteAccount();
                    break;
                case 5:
                    bank.depositAmount();
                    break;
                case 6:
                    bank.withdrawAmount();
                    break;
                case 7:
                    bank.searchAccount();
                    break;
                case 8:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }
}

class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();
    private int accountNumberCounter = 100000;
    //Method:1--->Creating User Account
    public void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account type (e.g., Saving, Current): ");
        String accountType = scanner.next();

        System.out.println("Enter your name: ");
        String accountName = scanner.next();

        double minimumBalance = 1000;

        System.out.println("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();

        if (initialBalance < minimumBalance) {
            System.out.println("Error: Initial balance must be at least " + minimumBalance);
            return;
        }

        Account newAccount = new Account(accountNumberCounter++, accountType, accountName, initialBalance);
        accounts.add(newAccount);

        System.out.println("Account created successfully!");
    }
    //Method:2--->Displaying the User Accounts created
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts to display.");
            return;
        }

        System.out.println("List of all accounts:");

        for (int i = 0; i < accounts.size(); i++) {
            System.out.println("Account " + String.format("%06d", accounts.get(i).getAccountNumber()) + ":");
            System.out.println("Type: " + accounts.get(i).getType());
            System.out.println("Name: " + accounts.get(i).getName());
            System.out.println("Balance: " + accounts.get(i).getBalance());
            System.out.println("-------------------------------");
        }
    }
    //Method:3--->Updating Accounts Informations
    public void updateAccount() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter account number to update: ");
        int accountNumber = scanner.nextInt();
    
        // Validate account number
        if (accountNumber < 100000 || accountNumber >= accountNumberCounter) {
            System.out.println("Invalid account number.");
            return;
        }
    
        // Assuming you have fields like name, creation date, etc.
        // You can prompt the user to update specific fields based on your requirements.
        System.out.print("Enter new name for the account: ");
        String newName = scanner.next();
        accounts.get(accountNumber - 100000).setName(newName);
    
        System.out.println("Account updated successfully!");
    }
    
    //Method:4--->Deleting the existing User Accounts
    public void deleteAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number to delete: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber < 1 || accountNumber > accounts.size()) {
            System.out.println("Invalid account number.");
            return;
        }
        accounts.remove(accountNumber - 1);

        System.out.println("Account deleted successfully!");
    }
    //Method:5--->Dipositing amount on an Account
    public void depositAmount() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter account number to deposit into: ");
        int accountNumber = scanner.nextInt();
    
        // Validate account number
        if (accountNumber < 100000 || accountNumber >= accountNumberCounter) {
            System.out.println("Invalid account number.");
            return;
        }
    
        System.out.print("Enter amount to deposit: ");
    
        // Validate input is a number
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
    
        double depositAmount = scanner.nextDouble();
    
        // Validate deposit amount
        if (depositAmount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
    
        accounts.get(accountNumber - 100000).deposit(depositAmount);
    
        System.out.println("Amount deposited successfully!");
    }
    //Method:6--->Withdrawing an amount from an Account
    public void withdrawAmount() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter account number to withdraw from: ");
        int accountNumber = scanner.nextInt();
    
        // Validate account number
        if (accountNumber < 100000 || accountNumber >= accountNumberCounter) {
            System.out.println("Invalid account number.");
            return;
        }
    
        System.out.print("Enter amount to withdraw: ");
    
        // Validate input is a number
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
    
        double withdrawAmount = scanner.nextDouble();
    
        // Validate withdrawal amount and minimum balance
        if (withdrawAmount <= 0 || withdrawAmount > accounts.get(accountNumber - 100000).getBalance()) {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
            return;
        }
    
        accounts.get(accountNumber - 100000).withdraw(withdrawAmount);
    
        System.out.println("Amount withdrawn successfully!");
    }
    //Method:7--->Searching an Account
    public void searchAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number to search: ");
        int accountNumber = scanner.nextInt();

        if (accountNumber < 1 || accountNumber > accounts.size()) {
            System.out.println("Invalid account number.");
            return;
        }

        Account foundAccount = accounts.get(accountNumber - 1);

        System.out.println("Account Details:");
        System.out.println("Type: " + foundAccount.getType());
        System.out.println("Balance: " + foundAccount.getBalance());
    }
}

class Account {
    private int accountNumber;
    private String type;
    private String name;
    private double balance;

    public Account(int accountNumber, String type, String name, double balance) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}