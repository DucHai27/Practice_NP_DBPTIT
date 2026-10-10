/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.rmi.registry.*;

/**
 *
 * @author Admin
 */
public class CharacterRMIClient2 {

    public static void main(String[] args) {
        try {
            String scode = "B23DCCN275";
            String qcode = "gKGuULnW";
            //a
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");
            String data = service.requestCharacter(scode, qcode);
            //b
            int[] values = {
                1000, 900, 500, 400, 100, 90,
                50, 40, 10, 9, 5, 4, 1
            };

            String[] symbols = {
                "M", "CM", "D", "CD", "C", "XC",
                "L", "XL", "X", "IX", "V", "IV", "I"
            };
            
            Integer number = Integer.parseInt(data);
            String result = "";
            for (int i = 0; i < values.length; i++) {
                while (number >= values[i]) {
                    result = result + symbols[i];
                    number -= values[i];
                }
            }
            //c
            service.submitCharacter(scode, qcode, result);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
