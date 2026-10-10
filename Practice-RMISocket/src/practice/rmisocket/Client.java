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
public class Client {
    public static void main(String[] args) {
        try {
            Calculator cal = (Calculator)Naming.lookup("rmi://localhost/Obj");
            int a = 5, b = 5;
            System.out.println("Tong 2 so a va b la: " + cal.add(a, b));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
