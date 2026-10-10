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
public class ByteRMIClient2 {
    public static void main(String[] args) {
        try {
            String msv = "B23DCCN275";
            String qcode = "PsRklp5s";
            //a
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            ByteService service = (ByteService) registry.lookup("RMIByteService");
            byte[] data = service.requestData(msv, qcode);
            //b
            Map<Byte, Integer> mp = new LinkedHashMap<>();
            for (byte b : data) {
                mp.put(b, mp.getOrDefault(b,0)+1);
            }
            byte val = 0;
            int max = Integer.MIN_VALUE;
            for (Map.Entry<Byte,Integer> e : mp.entrySet()) {
                if (e.getValue() > max) {
                    max = e.getValue();
                    val = e.getKey();
                }
            }
            byte[] result = new byte[] {val,(byte)max};
            
            //c
            service.submitData(msv, qcode, result);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
