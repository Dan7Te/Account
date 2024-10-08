import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account myAccount = new Account("Семен", 1233, 1100);
        myAccount.setAnnualInterestRate(7.5);

        while (true){
            System.out.println("\nГлавное меню:");
            System.out.println("1. Показать информацию о счете");
            System.out.println("2. Внести деньги");
            System.out.println("3. Снять деньги");
            System.out.println("4. Выход");
            System.out.println("----------------");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Имя владельца счета: " + myAccount.getName());
                    System.out.println("Годовая процентная ставка: " + myAccount.getAnnualInterestRate() + "%");
                    System.out.println("Текущий баланс: " + myAccount.getBalance());
                    System.out.println("Транзакции: ");
                    for (Transaction transaction : myAccount.getTransactions()){
                        System.out.println(transaction.getType() + ": " + transaction.getAmount() + " на " + transaction.getDate());
                    }
                    break;
                case 2:
                    System.out.println("Введите сумму для пополнения: ");
                    double depositAmount = scanner.nextDouble();
                    myAccount.deposit(depositAmount);
                    System.out.println("Сумма " + depositAmount + " успешно внесена на счет!");
                    break;
                case 3:
                    System.out.println("Введите сумму для снятия: ");
                    double withdrawAmount = scanner.nextDouble();
                    myAccount.withdraw(withdrawAmount);
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный выбор. Повторите попытку!");
            }
            if (choice > 4 || choice < 1) break;
        }
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