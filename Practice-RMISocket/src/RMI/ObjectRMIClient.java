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
public class ObjectRMIClient {

    public static void main(String[] args) {
        try {
            String studentCode = "B23DCCN275";
            String qCode = "D3JX1Gok";
            //b
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            ObjectService service = (ObjectService) registry.lookup("RMIObjectService");
            TicketSla ts = (TicketSla) service.requestObject(studentCode, qCode);
            if (ts.getOpenedHoursAgo() > 2) {
                ts.setBreached(true);
            }
            if (ts.getOpenedHoursAgo() <= 2) {
                ts.setAction("MONITOR");
            } else {
                if (ts.getPriority().equals("CRITICAL") || ts.getOpenedHoursAgo() > 96) ts.setAction("ESCALATE_L2");
                else ts.setAction("ESCALATE_L1");
            }
            service.submitObject(studentCode, qCode, ts);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
