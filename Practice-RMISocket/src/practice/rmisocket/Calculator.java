/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.rmisocket;
import java.rmi.*;
/**
 *
 * @author Admin
 */

public interface Calculator extends Remote {
    public int add(int x, int y) throws RemoteException;
}