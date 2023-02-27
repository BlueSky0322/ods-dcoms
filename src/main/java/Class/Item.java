/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.Serializable;

/**
 *
 * @author ryann
 */
public class Item implements Serializable {

    private String itemName;
    private double unitPrice;
    private int stockAmount;

    public Item(String name, double unitPrice, int stockAmount) {
        this.itemName = name;
        this.unitPrice = unitPrice;
        this.stockAmount = stockAmount;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

}
