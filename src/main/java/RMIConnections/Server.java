package RMIConnections;

import Class.User;
import Class.Item;
import Class.utils.DerbyDB;
import Class.utils.Hasher;
import Enum.Role;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.dbutils.DbUtils;

public class Server extends UnicastRemoteObject implements Interface {

    public Server() throws RemoteException {
        super();
    }

    @Override
    public void placeholderMethod() throws RemoteException {
        System.out.println("Hi");
    }

    @Override
    public User login(User user) throws Exception {
        String query = """
                       SELECT username, password, role
                       FROM OdsUser
                       WHERE username=?
                       """;
        PreparedStatement ps = DerbyDB.preparedStatement(query);
        ps.setString(1, user.getUsername());

        ResultSet result = ps.executeQuery();
        // if user not found
        if (!result.next()) {
            throw new Exception("User not found!");
        }

        String hashedPassword = Hasher.sha256(user.getPassword());
        String password = result.getString("password");
        // if password is incorrect
        if (!hashedPassword.equals(password)) {
            throw new Exception("Incorrect password!");
        }
        String username = result.getString("username");
        Role role = Role.valueOf(result.getString("role"));

        return new User(username, password, role);
    }

    @Override
    public void register(User newUser) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet result;

        query = """
                SELECT username
                FROM OdsUser
                WHERE username=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newUser.getUsername());

        result = ps.executeQuery();

        // if user exists
        if (result.next()) {
            throw new Exception("A user with this username already exists!");
        }

        query = """
                SELECT passport
                FROM OdsUser
                WHERE passport=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newUser.getPassportNumber().toUpperCase());

        result = ps.executeQuery();
        // if passport number exists
        if (result.next()) {
            throw new Exception("Passport number already exists in the system!");
        }

        query = """
                INSERT INTO OdsUser (username, password, first_name, last_name, passport, role) VALUES
                    (?, ?, ?, ?, ?, ?)
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newUser.getUsername().toLowerCase());
        ps.setString(2, Hasher.sha256(newUser.getPassword()));
        ps.setString(3, newUser.getFirstName());
        ps.setString(4, newUser.getLastName());
        ps.setString(5, newUser.getPassportNumber());
        ps.setString(6, Role.CUSTOMER.name());

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

    @Override
    public DefaultTableModel displayTable() {
        ResultSet rs;
        String[] columnNames = {"ID", "Item Name", "Unit Price", "Stock Amount"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.setRowCount(0);
        try {

            rs = DerbyDB.createStatement().executeQuery("SELECT * FROM ITEM");

            while (rs.next()) {
                int itemID = rs.getInt("ITEM_ID");
                String itemName = rs.getString("ITEM_NAME");
                double unitPrice = rs.getDouble("UNIT_PRICE");
                int stockAmount = rs.getInt("STOCK_AMOUNT");

                Object[] row = {itemID, itemName, unitPrice, stockAmount};
                model.addRow(row);
            }
            //commit changes to database
            DerbyDB.commit();

        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }

}
