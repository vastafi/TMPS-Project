package patterns.creational.singleton;

import database.postgresql.PostgreSQLJDBC;
import java.sql.Connection;
import java.sql.Statement;

public class SingletonDB {
    public static Connection connection;

    private SingletonDB(){
        connection = new PostgreSQLJDBC().createConnection();
    }

    public static Connection getInstance(){
        if (connection == null){
            connection = new PostgreSQLJDBC().createConnection();
        }
        return connection;
    }

    public void query(String sql){
        Statement statement;
        try{
            statement = connection.createStatement();
            statement.executeQuery(sql);
            System.out.println("\tRequest was completed");
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
