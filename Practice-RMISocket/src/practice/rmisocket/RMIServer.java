/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practice.rmisocket;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;

/**
 *
 * @author Admin
 */
public class RMIServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // TODO code application logic here
        LocateRegistry.createRegistry(1099);
        System.out.println("rmi registry started 1099");
        
        CalculatorImplement cal = new CalculatorImplement();
        Naming.bind("rmi://localhost/cal2632",cal);
        System.out.println("cal rmi server is listening");
    }
    
}
