import java.util.Date;

public class Main {
    public static void main(String[] args) {

    }
}

class Account{
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account(){
        this.id = 0;
        this.balance = 0;
        this.annualInterestRate = 0;
        this.dateCreated = new Date("01.01.2020");
    }

    public Account(int id, double balance, double annualInterestRate, Date dateCreated){
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = dateCreated;
    }

    public int getId(){
        return this.id;
    }

    public double getBalance(){
        return this.balance;
    }

    public double getAnnualInterestRate(){
        return this.annualInterestRate;
    }

    public Date getDateCreated(){
        return this.dateCreated;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterest(){
        double monthlyInterestRate = this.annualInterestRate / 12;
        double monthlyInterest = this.balance * monthlyInterestRate;
        return monthlyInterest;
        //this.balance += monthlyInterest;
    }

    public void withdraw(double amount){
        if(this.balance - amount < 0) System.out.println("Недостаточно средств для списания");
        else this.balance -= amount;
    }

    public void deposit(double amount){
        this.balance += amount;
    }

}