package RMIConnections;

import Class.Customer;
import Class.Item;
import java.rmi.*;

/**
 *
 * @author RyanNg
 */
public interface Interface extends Remote{
    public void placeholderMethod() throws RemoteException;
    public void login(Customer customer) throws Exception;
    public void register(Customer newCustomer) throws Exception;
    public void addItem(Item newItem) throws Exception;
}
