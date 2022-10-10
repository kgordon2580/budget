import Enums.Categories;

public class Main {

    public static void main(String args[]) throws Exception {
        Budget myBudget = new Budget(125, 80, 200, 200, 50);
        BankAccount checking = new BankAccount(2300);
        BankAccount savings = new BankAccount(5000);
        DebitCard debitCard = new DebitCard(checking, "Debit Card");
        CreditCard fidelity = new CreditCard(1000, "Fidelity");
        CreditCard discover = new CreditCard(2500, "Discover");
        myBudget.makePurchase(5.04, Categories.ENTERTAINMENT, "Walmart", debitCard);
        myBudget.makePurchase(20.55, Categories.GAS, "QuickTrip", discover);
        myBudget.makePurchase(23, Categories.MISC, "Target", debitCard);
        myBudget.makePurchase(34.55, Categories.GAS, "QuickTrip", fidelity);
        myBudget.makePurchase(100, Categories.ENTERTAINMENT, "Target", fidelity);
        myBudget.makePurchase(23.45, Categories.GROCERY, "Aldi", discover);
        checking.transfer(myBudget.getAmount(Categories.SAVINGS), savings);
        myBudget.setAmount(Categories.SAVINGS, 0);
        myBudget.makePurchase(31.99, Categories.ENTERTAINMENT, "Olive Garden", fidelity);
        myBudget.endOfMonth();
    }
}
