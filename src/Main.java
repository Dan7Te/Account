import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Account.setAnnualInterestRate(6.5);
//        Account acc = new Account(1155, 300000);
//
//        acc.withdraw(16500);
//        acc.deposit(50000);
//
//        acc.displayAccountInfo();

        Scanner scanner = new Scanner(System.in);
        Account[] accounts = new Account[10];

        for (int i = 0; i < accounts.length; i++){
            accounts[i] = new Account(i, 10000);
        }
        while (true){
            System.out.print("Введите ID счета (0-9): ");
            int id = scanner.nextInt();
            if (id == -1) break;
            if (id < 0 || id >= accounts.length){
                System.out.println("Некорректный ID. Повторите попытку");
                continue;
            }

            Account account = accounts[id];
            account.setAnnualInterestRate(6.5);

            while (true){
                System.out.println("1. Просмотр текущего баланса");
                System.out.println("2. Снятие денег");
                System.out.println("3. Внесение денег");
                System.out.println("4. Выход");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Баланс: " + account.getBalance());
                        System.out.println("Ежемесячный процент: " + account.getMonthlyInterest());
                        System.out.println("Дата создания: " + account.getDateCreated());
                        break;
                    case 2:
                        System.out.println("Введите сумму для снятия: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.println("Введите сумму пополнения: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Некорректный выбор. Повторите попытку!");
                }
                if (choice == 4) break;
            }
        }
        scanner.close();
    }
}

class Account{
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    private Date dateCreated;
    private String name;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(){
        this.dateCreated = new Date();
    }

    public Account(int id, double balance){
        this.id = id;
        this.balance = balance;
//        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public Account(String name, int id, double balance){
        this.name = name;
        this.id = id;
        this.balance = balance;
        this.dateCreated = new Date();
    }

    public String getName(){
        return name;
    }

    public int getId(){return this.id;}

    public void setId(int id){
        this.id = id;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getAnnualInterestRate(){
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double rate){
        this.annualInterestRate = rate;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public double getMonthlyInterest(){
//        double monthlyInterestRate = annualInterestRate / 12;
//        double monthlyInterest = balance * monthlyInterestRate;
//        return monthlyInterest;
//        //this.balance += monthlyInterest;
        return balance * (annualInterestRate / 100) / 12;
    }

    public void withdraw(double amount){
        if(amount <= balance){
            balance -= amount;
            transactions.add(new Transaction("Снятие денег", amount));
        }
        else {
            System.out.println("Недостаточно средств для снятия");
        }
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
            transactions.add(new Transaction("Внесение денег", amount));
        } else {
            System.out.println("Сумма пополнения должна быть положительной");
        }
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    public void displayAccountInfo(){
        System.out.println("ID счета: " + id);
        System.out.println("Баланс: " + balance);
        System.out.println("Ежемесячный процент: " + (balance * getMonthlyInterest()));
        System.out.println("Дата создания: " + dateCreated);
    }
}

class Transaction {
    private Date date;
    private String type;
    private double amount;

    public Transaction(String type, double amount){
        this.date = new Date();
        this.type = type;
        this.amount = amount;
    }

    public Date getDate(){
        return date;
    }

    public String getType(){
        return type;
    }

    public double getAmount() {
        return amount;
    }
}