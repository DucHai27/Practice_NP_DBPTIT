/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.rmi.*;
/**
 *
 * @author Admin
 */
public interface ObjectService extends java.rmi.Remote {
    java.io.Serializable requestObject(String studentCode, String qCode) throws java.rmi.RemoteException;
    void submitObject(String studentCode, String qCode, java.io.Serializable object) throws java.rmi.RemoteException;
}