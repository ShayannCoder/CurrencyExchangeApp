import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CurrencyExchangeApp {
    public static void main(String[] args){
        System.out.println(Exchange.calculateExchange());
        Exchange ex1 = new Exchange();
        System.out.println(ex1.timeStamp);
    }
}

class Exchange {

    LocalDateTime DateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String timeStamp = DateTime.format(format);

    public static float moneyToBeExchanged() {
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the amount of money you want to exchange: ");
        float money = sc.nextFloat();
        sc.close();
        return money;
    }
        
    public static float getRate() {
        Scanner sc = new Scanner(System.in);
        System.out.print("please enter the current exchange rate: ");
        Float rate = sc.nextFloat();
        sc.close();
        return rate;
    }

    public static float calculateExchange() {
        float money = moneyToBeExchanged();
        float rate = getRate();
        return money * rate;
    }
}