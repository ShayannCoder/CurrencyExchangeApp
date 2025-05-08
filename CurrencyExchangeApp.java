import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//import org.json.JSONObject;

public class CurrencyExchangeApp {
    public static void main(String[] args){
        System.out.println(Exchange.calculateExchange());
        Exchange ex1 = new Exchange();
        System.out.println(ex1.timeStamp);
    }
}

class Exchange {

    private static final Scanner sc = new Scanner(System.in);

    LocalDateTime DateTime = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String timeStamp = DateTime.format(format);

    public static float moneyToBeExchanged() {
        System.out.print("please enter the amount of money you want to exchange: ");
        return sc.nextFloat();
    }
        
    public static float rateGetter() {
        System.out.print("please enter the current exchange rate: ");
        return sc.nextFloat();
    }

    public static float calculateExchange() {
        float money = moneyToBeExchanged();
        float rate = rateGetter();
        return money * rate;
    }
}

class API {
    public static void getRate() {
        try {
            String apiKey = "76abdb90da458f91cb518b75";
            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}