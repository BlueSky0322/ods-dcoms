package RMIConnections;

import Class.Customer;
import java.rmi.*;
import java.util.Map;

/**
 *
 * @author RyanNg
 */
public interface Interface extends Remote{
    public void placeholderMethod() throws RemoteException;
    public Map<String, Object> login(Customer customer) throws RemoteException;
    public void register(Customer newCustomer) throws Exception;
}
