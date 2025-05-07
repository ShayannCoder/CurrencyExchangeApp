import java.util.*;

public class CurrencyExchangeApp {
    public static void main(String[] args){
        System.out.println(Exchange.calculateExchange());
    }
}

class Exchange {
    
    public static float moneyToBeExchanged() {
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the amount of money you want to exchange: ");
        float money = sc.nextFloat();
        return money;
    }
        
    public static float getRate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the current exchange rate: ");
        Float rate = sc.nextFloat();
        return rate;
    }
    public static float calculateExchange() {
        float money = moneyToBeExchanged();
        float rate = getRate();
        return money * rate;
    }
}