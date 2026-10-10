/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class DataServiceRMIClient1 {

    public static void main(String[] args) {
        try {
            String studentCode = "B23DCCN275";
            String qCode = "839f5USi";
            //b
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            DataService service = (DataService) registry.lookup("RMIDataService");
            int[] data =  (int[])service.requestData(studentCode, qCode);
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                if (i == 0) {
                    if (data[i] > data[i+1]) {
                        result.add(i+1);
                    }
                }
                else if (i == data.length-1) {
                    if (data[i] > data[i-1]) {
                        result.add(i+1);
                    }
                }
                else {
                    if (data[i] > data[i-1] && data[i] > data[i+1]) {
                        result.add(i+1);
                    }
                }
            }
            
            
            service.submitData(studentCode, qCode, result);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
