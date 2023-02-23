package RMIConnections;

import Class.Customer;
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
        
        query = """
                SELECT username
                FROM Customer
                WHERE username=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCustomer.getUsername());

        ResultSet result = ps.executeQuery();

        // if user exists
        if (result.next()) {
            throw new Exception("A user with this username already exists!");
        }

        query = """
                INSERT INTO Customer (username, password, first_name, last_name, passport) VALUES
                    (?, ?, ?, ?, ?)
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCustomer.getUsername());
        ps.setString(2, Hasher.sha256(newCustomer.getPassword()));
        ps.setString(3, newCustomer.getFirstName());
        ps.setString(4, newCustomer.getLastName());
        ps.setString(5, newCustomer.getPassportNumber());
        
        ps.executeUpdate();
        DerbyDB.commit();
    }
}
