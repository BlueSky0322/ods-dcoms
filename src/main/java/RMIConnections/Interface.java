package RMIConnections;

import Class.User;
import java.rmi.*;
import java.util.Map;

/**
 *
 * @author RyanNg
 */
public interface Interface extends Remote{
    public void placeholderMethod() throws RemoteException;
    public Map<String, Object> login(User user) throws RemoteException;

}
