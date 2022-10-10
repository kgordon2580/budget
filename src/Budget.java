import Enums.Categories;

import java.io.*;
import java.util.*;

public class Budget {

    private double grocery;
    private double gas;
    private double entertainment;
    private double savings;
    private double misc;
    private double previousMonthRemainingTotal;
    private Map<Categories, List<Purchase>> purchases = new HashMap<>();
    private List<Double> originalAmounts;
    private String dash = "-----------------------------------------------------------------------";


    public Budget(double grocery, double gas, double entertainment, double savings, double misc){
        this.grocery = grocery;
        this.gas = gas;
        this.entertainment = entertainment;
        this.savings = savings;
        this.misc = misc;

        originalAmounts = Arrays.asList(grocery, gas, entertainment, savings, misc);

        for(Categories category: Categories.values()){
            purchases.put(category, new ArrayList<>());
        }

    }

    public void setAmount(Categories category, double amount){
        switch(category) {
            case GROCERY -> grocery = amount;
            case GAS -> gas = amount;
            case ENTERTAINMENT -> entertainment = amount;
            case SAVINGS -> savings = amount;
            case MISC -> misc = amount;
        }
    }

    public double getAmount(Categories category){
        switch(category) {
            case GROCERY -> {
                return grocery;
            }
            case GAS -> {
                return gas;
            }
            case ENTERTAINMENT -> {
                return entertainment;
            }
            case SAVINGS -> {
                return savings;
            }
            case MISC -> {
                return misc;
            }
        }
        return 0;
    }

    public double getTotalBudgetBalance(){
        return grocery+gas+entertainment+savings+misc;
    }

    public double getPreviousMonthRemainingTotal(){
        return previousMonthRemainingTotal;
    }

    public String getCategoryBalances(){
        String categoryBalances = "";
        for (Categories category : purchases.keySet()) {
            switch(category) {
                case GROCERY -> categoryBalances += String.format("Grocery: $%.2f%n",grocery);
                case GAS -> categoryBalances += String.format("Gas: $%.2f%n",gas);
                case ENTERTAINMENT -> categoryBalances += String.format("Entertainment: $%.2f%n",entertainment);
                case SAVINGS -> categoryBalances += String.format("Savings: $%.2f%n",savings);
                case MISC -> categoryBalances += String.format("Miscellaneous: $%.2f%n",misc);
            }
        }
        return categoryBalances;
    }

    public void makePurchase(double amount, Categories category, String store, PaymentMethod paymentMethod)
            throws Exception {

        switch(category) {
            case GROCERY -> {
                if(grocery - amount < 0){
                    throw new Exception("Purchase Exceeds Budget.");
                }else{
                    grocery -= amount;
                }
            }
            case GAS -> {
                if(gas - amount < 0){
                    throw new Exception("Purchase Exceeds Budget.");
                }else{
                    gas -= amount;
                }
            }
            case ENTERTAINMENT -> {
                if(entertainment - amount < 0){
                    throw new Exception("Purchase Exceeds Budget.");
                }else{
                    entertainment -= amount;
                }
            }
            case SAVINGS -> {
                if(savings - amount < 0){
                    throw new Exception("Purchase Exceeds Budget.");
                }else{
                    savings -= amount;
                }
            }
            case MISC -> {
                if(misc - amount < 0){
                    throw new Exception("Purchase Exceeds Budget.");
                }else{
                    misc -= amount;
                }
            }
        }
        Purchase newestPurchase = new Purchase(amount, category, store, paymentMethod);
        if(paymentMethod instanceof CreditCard){
            CreditCard.purchases.add(newestPurchase);
        }
        purchases.get(category).add(newestPurchase);
    }

    public void resetBudget(){
        grocery = originalAmounts.get(0);
        gas = originalAmounts.get(1);
        entertainment = originalAmounts.get(2);
        savings = originalAmounts.get(3);
        misc = originalAmounts.get(4);
    }

    public void endOfMonth(){

        //formats and outputs budget report file
        try {
            PrintWriter budgetReport = new PrintWriter("budgetReport.txt");
            budgetReport.print(String.format("%s%n%s%n%s%n%n", "BUDGET REPORT",dash,
                    "CREDIT CARD BALANCES:"));
            for(CreditCard creditCard: CreditCard.creditCards){
                budgetReport.print(creditCard.toString()+"\n");
            }
            budgetReport.print("\n"+dash+"\n"+"REMAINING CATEGORY BALANCES:"+"\n"+"\n" +getCategoryBalances()
                    +"\n"+"TOTAL: $"+String.format("%.2f",getTotalBudgetBalance())+"\n\n"+dash+"\n"+"PURCHASES:\n\n");
            for(Categories category : purchases.keySet()) {
                budgetReport.print(category + ": \n\n");
                if(category == Categories.SAVINGS) {
                    if(savings == 0) {budgetReport.print("Transfer Completed" + "\n");}
                    else {budgetReport.print("Transfer Not Completed" + "\n");}
                }
                Collections.reverse(purchases.get(category)); // to see the newest purchase first
                for (Purchase purchase : purchases.get(category)) {
                    budgetReport.print(String.format("%s%n", purchase.toString()));
                }
                budgetReport.print("\n");
            }

            budgetReport.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        previousMonthRemainingTotal = getTotalBudgetBalance(); //saving remaining total from previous month

        resetBudget();

        for(CreditCard creditCard: CreditCard.creditCards){
            creditCard.payOff();
        }
    }
}
