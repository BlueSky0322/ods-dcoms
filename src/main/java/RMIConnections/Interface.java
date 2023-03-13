package RMIConnections;

import Class.Cart;
import Class.User;
import Class.Item;
import Class.Order;
import java.rmi.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RyanNg
 */
public interface Interface extends Remote{
    public void placeholderMethod() throws RemoteException;
    public User login(User user) throws Exception;
    public void register(User newUser) throws Exception;
    
    public void addItem(Item newItem) throws Exception;
    public void updateItem(int itemId, Item updatedItem) throws Exception;
    public void deleteItem(Item currentItem) throws Exception;
    public DefaultTableModel viewTable() throws Exception;
    public Item retrieveItemByID(String itemID) throws Exception;
    public ArrayList<String> retrieveAllItemID() throws Exception;
    
    public String addCart(Cart newCart) throws Exception;
    public boolean searchItem(String searchItem) throws Exception;
    public String getSearchItem(String searchItem) throws Exception;
    public DefaultTableModel viewCart(String userID) throws Exception;
    public boolean searchCart(String searchItem) throws Exception;
    public String getCartItem(String searchItem) throws Exception;
    public boolean removeOrder(int userID, String itemName, int itemQuantity)throws Exception;
    public boolean updateCart(int userID, String itemName, int itemQuantity) throws Exception;
    public DefaultTableModel viewOrder(String customerID) throws Exception;
    public int addOrder(Order newOrder) throws Exception;
    public DefaultTableModel getReceipt(int receiptID) throws Exception;
    public DefaultTableModel getReport() throws Exception;
}
