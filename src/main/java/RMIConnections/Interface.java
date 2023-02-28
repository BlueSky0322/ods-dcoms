package RMIConnections;

import Class.User;
import Class.Item;
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
}
