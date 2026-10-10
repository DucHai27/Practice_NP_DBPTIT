/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

/**
 *
 * @author Admin
 */
public class ByteRMIClient {
    public static void main(String[] args) {
        try {
            //a
            Registry registry = LocateRegistry.getRegistry("36.50.135.242",1099);
            ByteService service = (ByteService) registry.lookup("RMIByteService");
            byte[] data = service.requestData("B23DCCN275", "g5iTXuee");
            
            //b
            Map<Byte, Integer> count = new LinkedHashMap<>();
            for (byte b : data) {
                count.put(b, count.getOrDefault(b, 0)+1);
            }
            byte value = 0;
            int min = Integer.MAX_VALUE;
            for (Map.Entry<Byte, Integer> entry : count.entrySet()) {
                if (entry.getValue() < min) {
                    value = entry.getKey();
                    min = entry.getValue();
                }
            }
            
            byte[] result = new byte[] {value, (byte)min};
            
            service.submitData("B23DCCN275", "g5iTXuee", result);
            
        } catch (Exception e) {
            System.out.println(e);
        }    
    }
}
