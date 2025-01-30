package JDBC;

import java.sql.*;

public class BookDatabaseManagerTest {

    public static final String DB_NAME = "/books";
    public static final String QUERY = "SELECT isbn, title, editionNumber, copyright FROM titles";

    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection(
                    DBProperties.DATABASE_URL + DB_NAME, DBProperties.DATABASE_USER, DBProperties.DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("isbn: " + rs.getString("isbn"));
                System.out.print(", title: " + rs.getString("title"));
                System.out.print(", editionNumber: " + rs.getInt("editionNumber"));
                System.out.println(", copyright: " + rs.getString("copyright"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
