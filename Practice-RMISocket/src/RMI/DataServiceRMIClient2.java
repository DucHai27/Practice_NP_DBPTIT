/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author Admin
 */
public class DataServiceRMIClient2 {
    public static void main(String[] args) {
        try {
            String studentCode = "B23DCCN275";
            String qCode = "k2KvEzJv";
            //b
            Registry registry = LocateRegistry.getRegistry("36.50.135.242",1099);
            DataService service = (DataService)registry.lookup("RMIDataService");
            String resp = (String)service.requestData(studentCode, qCode);
            //c
            String[] parts = resp.trim().split(",");
            double average = 0;
            for (String x : parts) {
                average += Double.parseDouble(x);
            }
            average /= parts.length;
            String avg = String.format("%.2f", average);
            
            double p = 0;
            List<Double> list = new ArrayList<>();
            for (String x : parts) {
                double x_i = Double.parseDouble(x);
                p += (x_i-average)*(x_i-average);
                list.add(x_i);
            }
            p /= parts.length;
            String sd = String.format("%.2f", Math.sqrt(p));
            Collections.sort(list);
            
            int index = (int)Math.ceil(parts.length*0.95)-1;
            String p95 = String.format("%.2f", list.get(index));

            String result = "average=" + avg + ";stddev=" + sd + ";p95=" + p95;
            
            service.submitData(studentCode, qCode, result);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
