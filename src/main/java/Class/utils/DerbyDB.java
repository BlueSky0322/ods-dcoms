/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author handikaharianto
 */
public class DerbyDB {
    private final static String DATABASE_URL = "jdbc:derby://localhost:1527/ODS";
    private static Connection dbConnection;
    
    public static void connectDB() {
        try {
            dbConnection = DriverManager.getConnection(DATABASE_URL);
            dbConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public static Statement createStatement() throws SQLException {
        return dbConnection.createStatement();
    }
    
    public static PreparedStatement preparedStatement(String query) throws SQLException {
        return dbConnection.prepareStatement(query);
    }
}
