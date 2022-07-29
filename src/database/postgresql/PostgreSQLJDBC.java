package database.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {
    public Connection createConnection(){
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/TMPS", "postgres", "Valik98-lab");
        }catch (ExceptionInInitializerError | Exception exception){
            exception.printStackTrace();
            System.err.println(exception.getClass().getName()+": "+exception.getMessage());
        }

        System.out.println("Connected to postgresql successful !");
        return connection;
    }
}
