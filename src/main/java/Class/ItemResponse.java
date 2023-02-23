/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import static Class.utils.GUID.Generate;

/**
 *
 * @author ryann
 */
public class ItemResponse extends Item {

    private String RequestID;
    private int Code;
    private int ErrorType;
    private String ErrorDetail;

    public ItemResponse(String name, double unitPrice, int stockAmount, String RequestID, int Code, int ErrorType, String ErrorDetail) {
        super(name, unitPrice, stockAmount);
        this.RequestID = Generate().toString();
        this.Code = Code;
        this.ErrorType = ErrorType;
        this.ErrorDetail = ErrorDetail;
    }
}
