import java.util.ArrayList;
import java.util.List;

public class PaymentMethod {

    private String name;
    public static List<PaymentMethod> paymentMethods = new ArrayList<>();

    public PaymentMethod(String name){
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void makePurchase(double amount){}
}
