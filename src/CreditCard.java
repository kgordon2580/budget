import java.util.ArrayList;
import java.util.List;

public class CreditCard extends PaymentMethod{

    private double limit;
    private double balance;
    public static List<Purchase> purchases = new ArrayList<>();
    public static List<CreditCard> creditCards = new ArrayList<>();

    public CreditCard(double limit, String name) {
        super(name);
        this.limit = limit;
        this.balance = 0;
        creditCards.add(this);
    }

    public void setLimit(double limit){
        this.limit = limit;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getLimit(){
        return limit;
    }

    public double getBalance(){
        return balance;
    }

    public void payOff() {
        purchases.clear();
        this.setBalance(0.0);
    }

    @Override
    public void makePurchase(double amount) {
        if(balance + amount > limit){
            throw new IllegalArgumentException("Purchase Failed. Purchase exceeds account limit.");
        }else{
            balance += amount;
        }
    }

    public String toString(){
        return getName() + ": $"+ String.format("%.2f",balance);
    }
}
