package RMIConnections;

import Class.User;
import Class.Item;
import java.rmi.*;
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
    public DefaultTableModel displayTable() throws Exception;
}
