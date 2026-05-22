import database.DatabaseConnection;

import javax.annotation.processing.SupportedSourceVersion;
import java.sql.*;

public class Main {
    public static void main(String[] args){
        DatabaseConnection db = DatabaseConnection.getInstance();
        try {
            db.connect("/home/student/Dokumenty/oop26/oop26/site_v2/users.db");
            insert("Jan", "12312");
            select();
            db.disconnect();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
    public static void select() throws SQLException {
        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query = "SELECT * FROM account";

        PreparedStatement statement = conn.prepareStatement(query);
        statement.execute();
        ResultSet rs = statement.getResultSet();

        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("username");
            String pass = rs.getString("password");
            System.out.println("ID: " + id + " NAME: " + name + " PASSWORD: " + pass);
        }
    }
    public static void insert(String name, String password) throws SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        String query = "INSERT INTO account (username, password) VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        preparedStatement.execute();

        ResultSet resultSet= preparedStatement.getGeneratedKeys();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            System.out.println("Created ID: " + id);

        }
    }

}
