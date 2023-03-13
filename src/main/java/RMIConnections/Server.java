package RMIConnections;

import Class.User;
import Class.Item;
import Class.Cart;
import Class.Order;
import Class.utils.DerbyDB;
import Class.utils.Hasher;
import Enum.Role;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    @Override
    public String addCart(Cart newCart) throws Exception {
        String query;
        PreparedStatement ps;
        ResultSet result;
        int itemID = newCart.getItemID();
        double itemPrice = newCart.getItemPrice();
        String itemName = newCart.getItemName();
        int orderQuantity = newCart.getItemQuantity();

        query = """
                SELECT item_id
                FROM CART
                WHERE item_id=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setInt(1, newCart.getItemID());
        result = ps.executeQuery();

        // if item already exists
        if (result.next()) {
            return ("Item with this ID already exists in cart!");
        }
        
        query = """
                SELECT item_id, stock_amount
                FROM ITEM
                WHERE item_id=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setInt(1, newCart.getItemID());
        result = ps.executeQuery();
        if (result.next()) {
            int availableItemAmount = result.getInt("STOCK_AMOUNT");
            int newQuantity = availableItemAmount-orderQuantity;
            query = """
                UPDATE ITEM SET item_name = ?, unit_price=?, stock_amount=?
                WHERE item_id=?
                """;
            ps = DerbyDB.preparedStatement(query);
            ps.setString(1, itemName);
            ps.setDouble(2, itemPrice);
            ps.setInt(3, newQuantity);
            ps.setInt(4, itemID);

            int rowsUpdated = ps.executeUpdate();
            System.out.println(rowsUpdated + " rows updated.");
            DerbyDB.commit();
        }

        query = """
                INSERT INTO CART (customer_id, customer_name, item_id, item_name, unit_price, order_quantity, item_totalprice) VALUES
                    (?, ?, ?, ?, ?, ?, ?)
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newCart.getCustomerID());
        ps.setString(2, newCart.getCustomerName());
        ps.setInt(3, newCart.getItemID());
        ps.setString(4, newCart.getItemName());
        ps.setDouble(5, newCart.getItemPrice());
        ps.setInt(6, orderQuantity);
        ps.setDouble(7, newCart.getItemTotalPrice());

        int rowsInserted = ps.executeUpdate();
        System.out.println(rowsInserted + " rows inserted.");
        DerbyDB.commit();
        return ("Successfully added "+newCart.getItemName()+" into cart!");
    }
    
    @Override
    public boolean searchItem(String searchItem) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        boolean itemExist=true;

        query = """
                SELECT item_name
                FROM ITEM
                WHERE item_name=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, searchItem);

        result = ps.executeQuery();

        // if user exists
        if (result.next()) {
            return itemExist;
        }else{
            return false;
        }
    
    }
    
    @Override
    public boolean searchCart(String searchItem) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        boolean itemExist=true;

        query = """
                SELECT item_name
                FROM CART
                WHERE item_name=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, searchItem);

        result = ps.executeQuery();

        // if user exists
        if (result.next()) {
            return itemExist;
        }else{
            return false;
        }
    
    }
    
    @Override
    public String getSearchItem(String searchItem) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        String searchedItem;
        String item_no="";
        String item_name="";
        String item_price="";
        String item_quantity="";

        query = """
                SELECT item_id,item_name,unit_price,stock_amount
                FROM ITEM
                WHERE item_name=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, searchItem);
        
        result = ps.executeQuery();
        if (result.next()) {
            item_no = result.getString(1);
            item_name = result.getString(2);
            item_price = result.getString(3);
            item_quantity = result.getString(4);
        }
        return(item_no+","+item_name+","+item_price+","+item_quantity);
    }
    
    @Override
    public String getCartItem(String searchItem) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        String searchedItem;
        String item_quantity="";
        String item_name="";
        String item_price="";
        String item_totalprice="";

        query = """
                SELECT item_name,unit_price,order_quantity, item_totalprice
                FROM CART
                WHERE item_name=?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, searchItem);
        
        result = ps.executeQuery();
        if (result.next()) {
            item_name = result.getString(1);
            item_price = result.getString(2);
            item_quantity = result.getString(3);
            item_totalprice = result.getString(4);
        }
        return(item_name+","+item_price+","+item_quantity+","+item_totalprice);
    }
    
    @Override
    public DefaultTableModel viewCart(String userID) {
        ResultSet rs;
        String customerID = userID; 
        String query;
        PreparedStatement ps;
        ResultSet result;
        String[] columnNames = {"Item Name", "Price Per Unit", "Order Quantity", "Item Total Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        model.setRowCount(0);
        try {
            query = """
                SELECT customer_id,item_name,unit_price,order_quantity,item_totalprice
                FROM CART
                WHERE customer_id=?
                """;
            ps = DerbyDB.preparedStatement(query);
            ps.setString(1, customerID);
            
            result = ps.executeQuery();
            while (result.next()) {
                String itemName = result.getString("ITEM_NAME");
                double unitPrice = result.getDouble("UNIT_PRICE");
                int orderQuantity = result.getInt("ORDER_QUANTITY");
                double itemTotalPrice = result.getDouble("ITEM_TOTALPRICE");

                Object[] row = {itemName, unitPrice, orderQuantity, itemTotalPrice};
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
    public boolean removeOrder(int userID, String itemName, int itemQuantity)throws Exception{
        ResultSet rs;
        int customerID = userID; 
        String query;
        PreparedStatement ps;
        ResultSet result;
        boolean orderRemoved = false;
        query = """
                SELECT customer_id,item_name,unit_price,order_quantity,item_totalprice
                FROM CART
                WHERE customer_id=? AND item_name = ?
                """;
        ps=DerbyDB.preparedStatement(query);
        ps.setInt(1, customerID);
        ps.setString(2, itemName);
        result = ps.executeQuery();
        if(result.next()){
            int orderQuantity = result.getInt("ORDER_QUANTITY");
            if(orderQuantity== itemQuantity){
                query = """
                DELETE FROM CART
                WHERE item_name = ? 
                """;
                
                ps = DerbyDB.preparedStatement(query);
                ps.setString(1, itemName);
                int rowsDeleted = ps.executeUpdate();
                System.out.println(rowsDeleted + " rows deleted.");
                DerbyDB.commit();
                
                orderRemoved = true;
            } 
        }
        return orderRemoved;
    }
    
    @Override
    public boolean updateCart(int userID, String itemName, int itemQuantity) throws Exception{
        ResultSet rs;
        int customerID = userID; 
        String updateItemName = itemName;
        String query;
        PreparedStatement ps;
        ResultSet result;
        boolean orderUpdatedStatus = false;
        query = """
                SELECT customer_id,item_name,unit_price,order_quantity,item_totalprice
                FROM CART
                WHERE customer_id=? AND item_name =?
                """;
        ps = DerbyDB.preparedStatement(query);
        ps.setInt(1, customerID);
        ps.setString(2, itemName);
        result = ps.executeQuery();
        if(result.next()){
            int oldOrderQuantity = result.getInt("ORDER_QUANTITY");
            int checkQuantity = oldOrderQuantity-itemQuantity;
            System.out.println(checkQuantity);
            if(checkQuantity<0){
                System.out.println("NEGATIVE");
                query = """
                SELECT item_name, stock_amount
                FROM ITEM
                WHERE item_name =?
                """;
                ps = DerbyDB.preparedStatement(query);
                ps.setString(1, updateItemName);
                result = ps.executeQuery();
                if(result.next()){
                int remainingQuantity = result.getInt("STOCK_AMOUNT");
                System.out.println(remainingQuantity);
                int checkQuantityUpdate = remainingQuantity+checkQuantity;
                if(checkQuantityUpdate>0){
                    query = """
                    UPDATE ITEM SET stock_amount=?
                    WHERE item_name=?
                    """;
                    ps = DerbyDB.preparedStatement(query);
                    ps.setInt(1, checkQuantityUpdate);
                    ps.setString(2, itemName);
                    int rowsUpdated = ps.executeUpdate();
                    System.out.println(rowsUpdated + " rows updated.");
                    
                    query="""
                      UPDATE CART SET order_quantity=?
                      WHERE item_name=?
                      """;
                      ps = DerbyDB.preparedStatement(query);
                      ps.setInt(1, itemQuantity);
                      ps.setString(2, itemName);
                      int cartRowsUpdated = ps.executeUpdate();
                      System.out.println(cartRowsUpdated + " rows updated.");
                      
                    DerbyDB.commit();
                    orderUpdatedStatus = true;
                }else{
                   orderUpdatedStatus = false;
                }
                }
            }else{
                System.out.println("POSITIVE");
                System.out.println(itemName);
                query = """
                        SELECT item_name, stock_amount
                        FROM ITEM
                        WHERE item_name =?
                        """;
                ps = DerbyDB.preparedStatement(query);
                ps.setString(1, itemName);
                ResultSet QueryResult = ps.executeQuery();
                System.out.println(result);
                if(QueryResult.next()){
                    System.out.println("ENTER HERE");
                int remainingQuantity = QueryResult.getInt("STOCK_AMOUNT");
                int newQuantity = remainingQuantity+checkQuantity;
                query = """
                    UPDATE ITEM SET stock_amount=?
                    WHERE item_name=?
                    """;
                    ps = DerbyDB.preparedStatement(query);
                    ps.setInt(1, newQuantity);
                    ps.setString(2, itemName);
                    int rowsUpdated = ps.executeUpdate();
                    System.out.println(rowsUpdated + " rows updated.");
                    
                query="""
                      UPDATE CART SET order_quantity=?
                      WHERE item_name=?
                      """;
                      ps = DerbyDB.preparedStatement(query);
                      ps.setInt(1, itemQuantity);
                      ps.setString(2, itemName);
                      int cartRowsUpdated = ps.executeUpdate();
                      System.out.println(cartRowsUpdated + " rows updated.");
                      DerbyDB.commit();
                    orderUpdatedStatus = true;
            }
            }
        }
        
        return orderUpdatedStatus;
    }

    
    @Override
    public DefaultTableModel viewOrder(String customerID) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        String[] itemArray = {};
        double[] priceArray = {};
        int[] quantityArray = {};
        int[] itemIdArray = {};
        int totalQuantity = 0;
        double[] totalPriceArray = {};
        double totalOrder = 0;
        String tempCustomerName = "";
        String[] columnNames = {"Customer Id", "Customer Name", "Item Id", "Item Name", "Unit Price", "Order Quantity", "Item Total Price", "Order Total Price", "Paid Amount", "Payment Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try {
            query = """
                SELECT customer_id,customer_name,item_id,item_name,unit_price,order_quantity,item_totalprice
                FROM CART
                WHERE customer_id=?
                """;
            ps = DerbyDB.preparedStatement(query);
            ps.setString(1, customerID);
            result = ps.executeQuery();
            while(result.next()){
                String customerId = result.getString("customer_id");
                String customerName = result.getString("customer_name");
                int itemId = result.getInt("item_id");
                String itemName = result.getString("item_name");
                double unitPrice = result.getDouble("unit_price");
                int orderQuantity = result.getInt("order_quantity");
                double totalPrice = result.getDouble("item_totalprice");
            
                itemArray = Arrays.copyOf(itemArray, itemArray.length + 1);
                itemArray[itemArray.length - 1] = itemName;
                priceArray = Arrays.copyOf(priceArray, priceArray.length + 1);
                priceArray[priceArray.length - 1] = unitPrice;
                quantityArray = Arrays.copyOf(quantityArray, quantityArray.length + 1);
                quantityArray[quantityArray.length - 1] = orderQuantity;
                itemIdArray = Arrays.copyOf(itemIdArray, itemIdArray.length + 1);
                itemIdArray[itemIdArray.length - 1] = itemId;
                totalPriceArray = Arrays.copyOf(totalPriceArray, totalPriceArray.length + 1);
                totalPriceArray[totalPriceArray.length - 1] = totalPrice;
                totalOrder = totalOrder + totalPrice;
                tempCustomerName = customerName;
                totalQuantity = totalQuantity + orderQuantity;
            }
            Object[] row = {customerID, tempCustomerName, itemIdArray, itemArray, priceArray, quantityArray, totalQuantity, totalPriceArray, totalOrder};
            model.addRow(row);
            Object value = model.getValueAt(0, 3);
        }
        catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
            return model;
    }
    
    @Override
    public int addOrder(Order newOrder) throws Exception{
        String query;
        PreparedStatement ps;
        String[] orderItemNo = (String[]) newOrder.getOrderItemNo();
        String[] orderItemName = newOrder.getOrderItemName();
        double[] orderItemPrice = newOrder.getOrderItemPrice();
        double[] orderItemTotalPrice = newOrder.getOrderItemTotalPrice();
        int[] orderQuantity = newOrder.getOrderItemQuantity();
        String paymentTime = newOrder.getPaymentTime();
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date PaymentTime = new java.sql.Date(currentDate.getTime());
        PaymentTime.setTime(0);
        
        String joinedItemNo = String.join(",", orderItemNo);
        String joinedItemName = String.join(",", orderItemName);
        String joinedItemPrice = Arrays.toString(orderItemPrice);
        String joinedOrderTotalPrice = Arrays.toString(orderItemTotalPrice);
        String joinedOrderQuantity = Arrays.toString(orderQuantity);
        
        query = """
            INSERT INTO CUSTOMERORDER (customer_id, customer_name, item_id, item_name, unit_price, order_quantity, total_quantity, item_totalprice, order_totalprice, paid_amount, payment_type, payment_time) VALUES
                (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        ps = DerbyDB.preparedStatement(query);
        ps.setString(1, newOrder.getCustomerID());
        ps.setString(2, newOrder.getCustomerName());
        ps.setString(3, joinedItemNo);
        ps.setString(4, joinedItemName);
        ps.setString(5, joinedItemPrice);
        ps.setString(6, joinedOrderQuantity);
        ps.setInt(7, newOrder.getOrderTotalQuantity());
        ps.setString(8, joinedOrderTotalPrice);
        ps.setDouble(9, newOrder.getOrderTotalPrice());
        ps.setDouble(10, newOrder.getPaidAmount());
        ps.setString(11, newOrder.getPaymentType());
        ps.setString(12, paymentTime);
        
        int rowsInserted = ps.executeUpdate();
        System.out.println(rowsInserted + " rows inserted.");
        DerbyDB.commit();
        
        int receiptNo = 0;
        
        try{
            query = """
                SELECT MAX(receipt_no) 
                FROM CUSTOMERORDER
            """;
            ps = DerbyDB.preparedStatement(query);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                receiptNo = result.getInt(1);
            }else {
                System.out.println("No rows returned by query.");
            }
            DerbyDB.commit();
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receiptNo;
    }
    
    @Override
    public DefaultTableModel getReceipt(int receiptID) throws Exception{
        String query;
        PreparedStatement ps;
        ResultSet result;
        System.out.println(receiptID);
        String[] columnNames = {"Receipt Id", "Customer Name", "Item Name",  "Item Price", "Item Quantity", "Total Quantity", "Total Payment", "Payment Time"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        try{
            query = """
                SELECT receipt_no, customer_name, item_name, unit_price, order_quantity, total_quantity, paid_amount, payment_time
                FROM CUSTOMERORDER
                WHERE receipt_no = ?
            """;
            ps = DerbyDB.preparedStatement(query);
            ps.setInt(1, receiptID);
            result = ps.executeQuery();
            if(result.next()){
                int receiptId = result.getInt("receipt_no");
                String customerName = result.getString("customer_name");
                String[] itemName = result.getString("item_name").split(",");
                String[] tempQuantity = result.getString("order_quantity").replaceAll("\\[|\\]", "").split(",");
                String[] tempPrice = result.getString("unit_price").replaceAll("\\[|\\]", "").split(",");
                double totalPayment = result.getDouble("paid_amount");
                String paymentDate = result.getString("payment_time");
                int totalQuantity = result.getInt("total_quantity");
                int[] itemQuantity = new int[itemName.length];
                double[] itemPrice = new double[itemName.length];
                for(int i = 0; i < itemName.length; i++){
                    itemQuantity[i] = Integer.parseInt(tempQuantity[i].trim()); 
                    System.out.println(itemQuantity[i]);    
                    itemPrice[i] = Double.parseDouble(tempPrice[i].trim());
                    System.out.println(itemPrice[i]);
                }
                System.out.println(totalQuantity);
                Object[] row = {receiptId, customerName, itemName, itemPrice, itemQuantity, totalQuantity, totalPayment, paymentDate};
                model.addRow(row);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    @Override
    public DefaultTableModel getReport() throws Exception{
        String[] columnNames = {"Payment Time", "Customer Name", "Item Name", "Total Quantity", "Order Total", "Paid Amount", "Payment Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames,0);
        try{
            String query = "SELECT * FROM CUSTOMERORDER";
            PreparedStatement ps = DerbyDB.preparedStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String paymentTime = rs.getString("payment_time");
                String customerName = rs.getString("customer_name");
                String itemName = rs.getString("item_name");
                int totalQuantity = rs.getInt("total_quantity");
                double orderTotal = rs.getDouble("order_totalprice");
                double paidAmount = rs.getDouble("paid_amount");
                String paymentMethod = rs.getString("payment_type");
        
                Object[] row = {paymentTime, customerName, itemName, totalQuantity, orderTotal, paidAmount, paymentMethod};
                model.addRow(row);
            }
            
            DerbyDB.commit();
            
        }catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }
}
