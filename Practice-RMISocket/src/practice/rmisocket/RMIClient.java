/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.rmisocket;
import java.net.MalformedURLException;
import java.rmi.*;
/**
 *
 * @author Admin
 */
public class RMIClient {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, RemoteException, RemoteException, RemoteException {
        Calculator cal = (Calculator)Naming.lookup("rmi://localhost/cal2632");
        
        System.out.println("sum from rmi server: " + cal.add(10,25));
    }
}
