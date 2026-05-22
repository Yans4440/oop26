import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        DatabaseConnection db = DatabaseConnection.getInstance();
        try {
            db.connect("/home/student/Dokumenty/oop26/oop26/site_v2/users.db");
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

}
