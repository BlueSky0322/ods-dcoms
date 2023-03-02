/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author handikaharianto
 */
public class DerbyDB {

    private final static String DATABASE_URL = "jdbc:derby://localhost:1527/ODS";
    private final static String DATABASE_USERNAME = "ods";
    private final static String DATABASE_PASSWORD = "ods";
    private static Connection dbConnection;

    public static void connectDB() {
        try {
            dbConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            dbConnection.setAutoCommit(false);
            System.out.println("Database is running...");
            
            //initialise all tables
            initialiseItemTable(createStatement());
            initialiseUserTable(createStatement());
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

    public static void commit() throws SQLException {
        dbConnection.commit();
    }

    public static void close() throws SQLException {
        dbConnection.close();
    }

    public static void initialiseItemTable(Statement stmt) throws SQLException {
        String createItemTableQuery;
        ResultSet rs;
        try {
            rs = dbConnection.getMetaData().getTables(null, null, "ITEM", null);
            if (!rs.next()) {
                createItemTableQuery
                        = "CREATE TABLE Item ("
                        + "item_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "item_name VARCHAR(255) NOT NULL,"
                        + "unit_price DECIMAL(10,2) NOT NULL,"
                        + "stock_amount INT NOT NULL)";
                stmt.executeUpdate(createItemTableQuery);
                System.out.println("Table 'Item' created successfully.");
                commit();

                // Insert static values
                String insertValuesQuery = "INSERT INTO Item (item_name, unit_price, stock_amount) "
                        + "VALUES "
                        + "('T-Shirt', 15.99, 50), "
                        + "('Sneakers', 89.99, 25), "
                        + "('Jeans', 29.99, 75), "      
                        + "('Hoodie', 35.99, 30), "
                        + "('Backpack', 49.99, 20)";
                stmt.executeUpdate(insertValuesQuery);
                System.out.println("Static values added to 'Item' table.");
                commit();
            } else {
                System.out.println("Table 'Item' already exists. No new table created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error creating/checking table: " + ex.getMessage());
        }
    }

    public static void initialiseUserTable(Statement stmt) throws SQLException {
        String createCustomerTableQuery;
        ResultSet rs;
        try {
            rs = dbConnection.getMetaData().getTables(null, null, "ODSUSER", null);
            if (!rs.next()) {
                createCustomerTableQuery
                        = "CREATE TABLE OdsUser ("
                        + "user_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "username VARCHAR(15) NOT NULL UNIQUE,"
                        + "password VARCHAR(255) NOT NULL,"
                        + "first_name VARCHAR(45) NOT NULL,"
                        + "last_name VARCHAR(45) NOT NULL,"
                        + "passport VARCHAR(9) NOT NULL UNIQUE,"
                        + "role VARCHAR(20) NOT NULL)";
                stmt.executeUpdate(createCustomerTableQuery);
                System.out.println("Table 'OdsUser' created successfully.");
                commit();
                
                String customerPassword = Hasher.sha256("johndoe123");
                String adminPassword = Hasher.sha256("administrator");
                
                // Insert static values
                String insertValuesQuery = "INSERT INTO OdsUser (username, password, first_name, last_name, passport, role) "
                        + "VALUES "
                        + "('administrator', '" + adminPassword + "', 'Alexander', 'Brown', 'G12345678', 'ADMIN'), "
                        + "('johndoe', '" + customerPassword + "', 'John', 'Doe', 'J12345678', 'CUSTOMER')";
                stmt.executeUpdate(insertValuesQuery);
                System.out.println("Static values added to 'Item' table.");
                commit();
            } else {
                System.out.println("Table 'OdsUser' already exists. No new table created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error creating/checking table: " + ex.getMessage());
        }
    }

    public static void initialiseOrderTable(Statement stmt) throws SQLException {
        String createOrderTableQuery;
        ResultSet rs;
        try {
            rs = dbConnection.getMetaData().getTables(null, null, "ITEM", null);
            if (!rs.next()) {
                createOrderTableQuery
                        = "CREATE TABLE Order ("
                        + "receipt_no INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "order_date TIMESTAMP NOT NULL,"
                        + "customer_name VARCHAR(255) NOT NULL,"
                        + "item_name VARCHAR(255) NOT NULL,"
                        + "unit_price DECIMAL(10,2) NOT NULL,"
                        + "order_quantity INT NOT NULL,"
                        + "item_totalprice DECIMAL(10,2) NOT NULL,"
                        + "order_totalprice DECIMAL(10,2) NOT NULL,"
                        + "payment_type VARCHAR(40) NOT NULL,"
                        + "payment_time TIMESTAMP NOT NULL)";
                stmt.executeUpdate(createOrderTableQuery);
                System.out.println("Table 'Order' created successfully.");
                commit();
            } else {
                System.out.println("Table 'Order' already exists. No new table created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error creating/checking table: " + ex.getMessage());
        }
    }
}
