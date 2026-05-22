package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    private static DatabaseConnection instance =null;

    private DatabaseConnection(){}

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }else {

        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
    public void connect(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:"+path);
    }

    public void disconnect() throws SQLException {
        if(connection!=null && !connection.isClosed()){
            connection.close();
        }
    }
}
