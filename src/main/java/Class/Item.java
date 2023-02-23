/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author ryann
 */
public class Item {
    private String name;
    private double unitPrice;
    private int stockAmount;

    public Item(String name, double unitPrice, int stockAmount) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.stockAmount = stockAmount;
    }

    public String getName() {
        return name;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }    
}
