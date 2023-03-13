/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.Serializable;

/**
 *
 * @author putubgs
 */
public class Order implements Serializable{

    public int receiptId;
    public String customerID;
    public String customerName;
    public String[] orderItemNo;
    public String[] orderItemName;
    public double[] orderItemPrice;
    public double[] orderItemTotalPrice;
    public int[] orderItemQuantity;
    public int orderTotalQuantity;
    public double orderTotalPrice;
    public String paymentType;
    public double paidAmount;
    public String paymentTime;
    public int indexCounter;
    
    public Order(String customerID,String customerName,String[] orderItemNo,String[] orderItemName, double[] orderItemPrice, double[] orderItemTotalPrice, int[] orderItemQuantity, int orderTotalQuantity, double orderTotalPrice, String paymentType,double paidAmount, String paymentTime, int indexCounter){
        this.customerID = customerID;
        this.customerName = customerName;
        this.orderItemNo = new String[indexCounter];
        this.orderItemName = new String[indexCounter];
        this.orderItemPrice = new double[indexCounter];
        this.orderItemTotalPrice = new double[indexCounter];
        this.orderItemQuantity = new int[indexCounter];
        this.orderTotalQuantity = orderTotalQuantity;
        this.orderTotalPrice = orderTotalPrice;
        this.paymentType = paymentType;
        this.paidAmount = paidAmount;
        this.paymentTime = paymentTime;
        
        for(int i = 0; i < indexCounter; i++){
            this.orderItemNo[i] = orderItemNo[i];
            this.orderItemName[i] = orderItemName[i];
            this.orderItemPrice[i] = orderItemPrice[i];
            this.orderItemTotalPrice[i] = orderItemTotalPrice[i];
            this.orderItemQuantity[i] = orderItemQuantity[i];
        }
    }

    public Order(int receiptId){
        this.receiptId = receiptId;
    }
    
    public int getReceiptID(){
        return receiptId;
    }
    
    public String getCustomerID(){
        return customerID;
    }
    
    public String getCustomerName(){
        return customerName;
    }
    
    public String[] getOrderItemNo(){
        return orderItemNo;
    }
    
    public String[] getOrderItemName(){
        return orderItemName;
    }
    
    public double[] getOrderItemPrice(){
        return orderItemPrice;
    }
    
    public double[] getOrderItemTotalPrice(){
        return orderItemTotalPrice;
    }
    
    public int[] getOrderItemQuantity(){
        return orderItemQuantity;
    }
    
    public int getOrderTotalQuantity(){
        return orderTotalQuantity;
    }
    
    public double getOrderTotalPrice(){
        return orderTotalPrice;
    }
    
    public String getPaymentType(){
        return paymentType;
    }
    
    public double getPaidAmount(){
        return paidAmount;
    }
    
    public String getPaymentTime(){
        return paymentTime;
    }
    
    public int getIndexCounter(){
        return indexCounter;
    }
}
