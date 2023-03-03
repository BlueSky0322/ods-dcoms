/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import java.io.Serializable;
/**
 *
 * @author JUN WEI
 */
public class Cart implements Serializable{
    public String customerID;
    public String customerName;
    public int itemID;
    public String itemName;
    public double itemPrice;
    public int itemQuantity;
    public double itemTotalPrice;
    
    public Cart(String customerID, String customerName, int itemID, String itemName, double itemPrice, int itemQuantity, double itemTotalPrice){
        this.customerID = customerID;
        this.customerName = customerName;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemTotalPrice = itemTotalPrice;
    }
    
    public String getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public int getItemID(){
        return itemID;
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public double getItemPrice(){
        return itemPrice;
    }
    
    public int getItemQuantity(){
        return itemQuantity;
    }
    
    public double getItemTotalPrice(){
        return itemTotalPrice;
    }
    
    
}
