public class DebitCard extends PaymentMethod{

    private BankAccount connectedAccount;

    public DebitCard(BankAccount connectedAccount, String name){
        super(name);
        this.connectedAccount = connectedAccount;
    }

    public BankAccount getConnectedAccount() {
        return connectedAccount;
    }

    @Override
    public void makePurchase(double amount) {
        connectedAccount.withdraw(amount);
    }
}
