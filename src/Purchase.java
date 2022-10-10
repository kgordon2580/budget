import Enums.Categories;

public class Purchase {

    private double amount;
    private Categories category;
    private String store;
    private PaymentMethod paymentMethod;

    public Purchase(double amount, Categories category, String store, PaymentMethod paymentMethod){
        this.amount = amount;
        this.category = category;
        this.store = store;
        this.paymentMethod = paymentMethod;

        this.paymentMethod.makePurchase(amount);
    }

    public double getAmount(){
        return amount;
    }

    public Categories getCategory(){
        return category;
    }

    public String getStore() {
        return store;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return String.format("$%-10s %-20s %-15s %-15s", amount, category, store, paymentMethod.getName());
    }
}
