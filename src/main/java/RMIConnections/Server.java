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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

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
                       SELECT username, password, first_name, last_name, passport, role
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
        String firstName = result.getString("first_name");
        String lastName = result.getString("last_name");
        String passport = result.getString("passport");
        Role role = Role.valueOf(result.getString("role"));

        return new User(username, firstName, lastName, passport, role);
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
    public DefaultTableModel viewTable() {
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

    @Override
    public void updateItem(int itemId, Item updatedItem) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet result;

        query = """
                UPDATE ITEM SET item_name = ?, unit_price=?, stock_amount=?
                WHERE item_id=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, updatedItem.getItemName());
        ps.setDouble(2, updatedItem.getUnitPrice());
        ps.setInt(3, updatedItem.getStockAmount());
        ps.setInt(4, itemId);

        int rowsUpdated = ps.executeUpdate();
        System.out.println(rowsUpdated + " rows updated.");
        DerbyDB.commit();
    }

    @Override
    public void deleteItem(Item currentItem) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet rs;

        query = """
                SELECT COUNT(*) FROM ITEM 
                WHERE item_name = ? AND unit_price = ? AND stock_amount = ?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, currentItem.getItemName());
        ps.setDouble(2, currentItem.getUnitPrice());
        ps.setInt(3, currentItem.getStockAmount());

        rs = ps.executeQuery();

        // if item does not exist
        if (!rs.next()) {
            throw new Exception("Cannot delete item that does not exist!");
        }
        int count = rs.getInt(1);
        if (count == 0) {
            throw new Exception("Cannot delete item that does not exist!");
        }

        query = """
                DELETE FROM ITEM
                WHERE item_name = ? AND unit_price = ? AND stock_amount = ?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, currentItem.getItemName());
        ps.setDouble(2, currentItem.getUnitPrice());
        ps.setInt(3, currentItem.getStockAmount());
        int rowsDeleted = ps.executeUpdate();
        System.out.println(rowsDeleted + " rows deleted.");
        DerbyDB.commit();
    }

    @Override
    public Item retrieveItemByID(String itemID) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet rs;
        Item item = null;

        // get items
        query = """
                SELECT * from ITEM
                WHERE item_id = ?
                """;

        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, itemID);
        rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            int stock = rs.getInt(4);
            item = new Item(name, price, stock);
        }

        DerbyDB.commit();

        return item;
    }

    @Override
    public ArrayList<String> retrieveAllItemID() throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<String> itemIDs = new ArrayList<>();

        // get items
        query = """
                SELECT item_id from ITEM
                """;

        ps = DerbyDB.preparedStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            String itemID = rs.getString(1);
            itemIDs.add(itemID);
        }

        DerbyDB.commit();

        return itemIDs;
    }

}
