package RMIConnections;

import Class.Customer;
import Class.Item;
import Class.utils.DerbyDB;
import Class.utils.Hasher;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Server extends UnicastRemoteObject implements Interface {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void placeholderMethod() throws RemoteException {
        System.out.println("Hi");
    }

    @Override
    public void login(Customer customer) throws Exception {
        String query = """
                       SELECT username, password
                       FROM Customer
                       WHERE username=?
                       """;
        PreparedStatement ps = DerbyDB.preparedStatement(query);
        ps.setString(1, customer.getUsername());

        ResultSet result = ps.executeQuery();
        // if user not found
        if (!result.next()) {
            throw new Exception("User not found!");
        }

        String hashedPassword = Hasher.sha256(customer.getPassword());
        String password = result.getString("password");
        // if password is incorrect
        if (!hashedPassword.equals(password)) {
            throw new Exception("Incorrect password!");
        }
    }

    @Override
    public void register(Customer newCustomer) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet result;

        query = """
                SELECT username
                FROM Customer
                WHERE username=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCustomer.getUsername());

        result = ps.executeQuery();

        // if user exists
        if (result.next()) {
            throw new Exception("A user with this username already exists!");
        }

        query = """
                SELECT passport
                FROM Customer
                WHERE passport=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCustomer.getPassportNumber().toUpperCase());

        result = ps.executeQuery();
        // if passport number exists
        if (result.next()) {
            throw new Exception("Passport number already exists in the system!");
        }

        query = """
                INSERT INTO Customer (username, password, first_name, last_name, passport) VALUES
                    (?, ?, ?, ?, ?)
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCustomer.getUsername().toLowerCase());
        ps.setString(2, Hasher.sha256(newCustomer.getPassword()));
        ps.setString(3, newCustomer.getFirstName());
        ps.setString(4, newCustomer.getLastName());
        ps.setString(5, newCustomer.getPassportNumber());

        ps.executeUpdate();
        DerbyDB.commit();
    }

    @Override
    public void addItem(Item newItem) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet result;

        query = """
                SELECT item_name
                FROM ITEM
                WHERE item_name=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newItem.getItemName());

        result = ps.executeQuery();

        // if item already exists
        if (result.next()) {
            throw new Exception("Item with this name already exists!");
        }

        query = """
                INSERT INTO ITEM (item_name, unit_price, stock_amount) VALUES
                    (?, ?, ?)
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newItem.getItemName());
        ps.setDouble(2, newItem.getUnitPrice());
        ps.setInt(3, newItem.getStockAmount());

        int rowsInserted = ps.executeUpdate();
        System.out.println(rowsInserted + " rows inserted.");
        DerbyDB.commit();
    }
}
