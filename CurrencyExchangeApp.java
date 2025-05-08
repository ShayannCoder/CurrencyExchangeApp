import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CurrencyExchangeApp {
    public static void main(String[] args){
        System.out.println("Type 1 for calculatin exchange, 2 for viewing data: ");
        Scanner sc=new Scanner(System.in);
        int operationType= sc.nextInt();
        if (operationType==1){//should change this to a while loop
            System.out.println(Exchange.calculateExchange());
        }else if(operationType==2) {
            database.viewData();
        }else{
            System.out.println("Type 1 or 2.");
        }
        
        //Exchange ex1 = new Exchange();
        //System.out.println(ex1.timeStamp);
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
        //sc.close(); closing the scanner here was causing issues
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
        float result = money * rate;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));// generates the timestamp at the time of the calculation

        database.saveExchangeLog(money, rate, result, timestamp);

        return result;
    }
}
class database {
    public static void viewData() {
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exchange_schema", "root", "yadbruh1");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM exchange_log");
            while(resultSet.next()) {
                System.out.println("ID: "+resultSet.getString("idexchange_log"));
                System.out.println(resultSet.getString("amount"));
                System.out.println(resultSet.getString("rate"));
                System.out.println(resultSet.getString("result"));
                System.out.println(resultSet.getString("timestamp"));
                //connection.close(); this returns an error (figure out why)
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }   

    public static void saveExchangeLog(float money, float rate, float result, String timestamp) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/exchange_schema", "root", "yadbruh1");
            String query = "INSERT INTO exchange_log (amount, rate, result, timestamp) VALUES (?, ?, ?, ?)"; //i dont understand this tbh its black magic
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1, money);
            preparedStatement.setFloat(2, rate);
            preparedStatement.setFloat(3, result);
            preparedStatement.setString(4, timestamp);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("Saved data to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}