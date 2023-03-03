/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author JUN WEI
 */
public class Order implements Serializable{
    public String customerID;
    public String customerName;
    public String orderItemNo;
    public Date orderTime;
    public String orderItemName;
    public double orderItemPrice;
    public int orderStockAmount;
    public int orderItemQuantity;
    public double orderTotalPrice;
    public boolean paymentMade;
    public String paymentType;
    public double paidAmount;
    public Date paymentTime;
    
    public Order(String customerID,String customerName,String orderItemNo, Date orderTime,String orderItemName, double orderItemPrice, int orderStockAmount, int orderItemQuantity, double orderTotalPrice, boolean paymentMade, String paymentType,double paidAmount, Date paymentTime){
        this.customerID = customerID;
        this.customerName = customerName;
        this.orderItemNo = orderItemNo;
        this.orderTime = orderTime;
        this.orderItemName = orderItemName;
        this.orderItemPrice = orderItemPrice;
        this.orderStockAmount = orderStockAmount;
        this.orderItemQuantity = orderItemQuantity;
        this.orderTotalPrice = orderTotalPrice;
        this.paymentMade = paymentMade;
        this.paymentType = paymentType;
        this.paidAmount = paidAmount;
        this.paymentTime = paymentTime;
    }
    
    public String getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String getOrderItemNo(){
        return orderItemNo;
    }
    
    public String getOrderItemName(){
        return orderItemName;
    }
    
    public double getOrderItemPrice(){
        return orderItemPrice;
    }
    
    public int getOrderStockAmount(){
        return orderStockAmount;
    }
    
    public int getOrderItemQuantity(){
        return orderItemQuantity;
    }
    
    public double getOrderTotalPrice(){
        return orderTotalPrice;
    }
    
    public boolean getPaymentMade(){
        return paymentMade;
    }
    
    public String getPaymentType(){
        return paymentType;
    }
    
    public double getPaidAmount(){
        return paidAmount;
    }
    
    public Date getOrderTime(){
        return orderTime;
    }
    
    public Date getPaymentTime(){
        return paymentTime;
    }
}
