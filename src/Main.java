import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Account.setAnnualInterestRate(6.5);
        Account acc = new Account(1155, 300000);

        acc.withdraw(16500);
        acc.deposit(50000);

        acc.displayAccountInfo();

        Scanner scanner = new Scanner(System.in);
        Account[] accounts = new Account[10];

        for (int i = 0; i < accounts.length; i++){
            accounts[i] = new Account(i, 10000);
        }
        while (true){
            System.out.print("Введите ID счета: ");
            int id = scanner.nextInt();
            if (id < 0 || id > 9){
                System.out.println("Некорректный ID. Повторите попытку");
                continue;
            }
            while (true){
                System.out.println("1. Просмотр текущего баланса");
                System.out.println("2. Снятие денег");
                System.out.println("3. Внесение денег");
                System.out.println("4. Выход");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Баланс: " + accounts[id].getBalance());
                        break;
                    case 2:
                        System.out.println("Введите сумму для снятия: ");
                        double withdrawAmount = scanner.nextDouble();
                        accounts[id].withdraw(withdrawAmount);
                        break;
                    case 3:
                        System.out.println("Введите сумму пополнения: ");
                        double depositAmount = scanner.nextDouble();
                        accounts[id].deposit(depositAmount);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Некорректный выбор. Повторите попытку!");
                }
                if (choice == 4) break;
            }
        }
    }
}

class Account{
    private int id = 0;
    private double balance = 0;
    private static double annualInterestRate = 0;
    private Date dateCreated;

    public Account(){
        this.dateCreated = new Date();
    }

    public Account(int id, double balance){
        this.id = id;
        this.balance = balance;
//        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public int getId(){return this.id;}

    public double getBalance(){
        return this.balance;
    }

    public static double getAnnualInterestRate(){
        return annualInterestRate;
    }

    public Date getDateCreated(){
        return dateCreated;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public static void setAnnualInterestRate(double rate){
        annualInterestRate = rate;
    }

    public static double getMonthlyInterest(){
//        double monthlyInterestRate = annualInterestRate / 12;
//        double monthlyInterest = balance * monthlyInterestRate;
//        return monthlyInterest;
//        //this.balance += monthlyInterest;
        return (annualInterestRate /100) /12;
    }

    public void withdraw(double amount){
        if(amount <= 0){
            balance -= amount;
        }
        else {
            System.out.println("Недостаточно средств для списания");
        }
    }

    public void deposit(double amount){
        if (amount > 0){
            balance += amount;
        } else {
            System.out.println("Сумма пополнения должна быть положительной");
        }
    }

    public void displayAccountInfo(){
        System.out.println("ID счета: " + id);
        System.out.println("Баланс: " + balance);
        System.out.println("Ежемесячный процент: " + (balance * getMonthlyInterest()));
        System.out.println("Дата создания: " + dateCreated);
    }
}