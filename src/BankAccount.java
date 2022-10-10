public class BankAccount {

    private double balance;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void withdraw(double amount) throws IllegalArgumentException{
        if(balance - amount < 0){
            throw new IllegalArgumentException("Withdraw failed. Withdraw would result in negative balance.");
        }else{
            balance -= amount;
        }
    }

    public void deposit(double amount){
        balance += amount;
    }

    public void transfer(double amount, BankAccount destination){
        this.withdraw(amount);
        destination.deposit(amount);
    }


}
