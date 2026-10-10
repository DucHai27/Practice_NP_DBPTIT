/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.rmisocket;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Admin
 */
public class CalculatorImplement extends UnicastRemoteObject implements Calculator{
    
    public CalculatorImplement() throws Exception {
        super();
    }
    
    @Override
    public int add(int x, int y) throws Exception {
        System.out.println("a new rmi client request");
        return x+y;
    }
}
