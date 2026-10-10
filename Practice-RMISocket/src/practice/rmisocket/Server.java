/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.rmisocket;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author Admin
 */
public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Calculator cal = new CalculatorImplement();
            Naming.bind("rmi://localhost/Obj", cal);
            System.out.println("Ser ver RMI da sang sang...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
