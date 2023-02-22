package RMIConnections;

import Class.Customer;
import Class.utils.DerbyDB;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;

public class Server extends UnicastRemoteObject implements Interface {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void placeholderMethod() throws RemoteException {
        System.out.println("Hi");
    }

    @Override
    public Map<String, Object> login(Customer customer) throws RemoteException {
        JDialog jDialog = new JDialog();
        jDialog.setAlwaysOnTop(true);
        
        // to store login response
        Map<String, Object> response = new HashMap<>();
        
        try {
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
                response.put("message", "User not found!");
                response.put("success", false);
                return response;
            }
            
            String password = result.getString("password");
            // if password is incorrect
            if (!customer.getPassword().equals(password)) {
                response.put("message", "Incorrect password!");
                response.put("success", false);
                return response;
            }
            // if login successful
            response.put("message", "Login successful!");
            response.put("success", true);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return response;
    }

}
