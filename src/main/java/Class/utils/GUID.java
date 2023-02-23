/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.util.UUID;

/**
 *
 * @author ryann
 */
public class GUID {
    public static UUID Generate() {
        UUID guid = UUID.randomUUID();
        return guid;
    }
}
