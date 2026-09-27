/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.udpsocket;

import UDP.Customer;
import UDP.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;


public class LT {
    public static void main(String[] args) throws Exception  {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        //a
        String request = ";B23DCCN275;MpKnDwny";
        DatagramPacket sendRequest = new DatagramPacket(request.getBytes(),request.getBytes().length,InetAddress.getByName("36.50.135.242"),2209);
        socket.send(sendRequest);
        //b
        byte[] buffer = new byte[65536];
        DatagramPacket rev = new DatagramPacket(buffer,buffer.length);
        socket.receive(rev);
        
        byte[] data = rev.getData();
        int len = rev.getLength();
        
        String requestId = new String(data,0,8);
        ByteArrayInputStream bais = new ByteArrayInputStream(data,8,len-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Customer customer = (Customer)ois.readObject();
        ois.close();
        
        //c
        customer.setUserName(taoUserName(customer.getName()));
        customer.setName(chuanHoaTen(customer.getName()));
        customer.setDayOfBirth(chuanHoaNS(customer.getDayOfBirth()));
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(customer);
        oos.flush();
        byte[] objByte = baos.toByteArray();
        oos.close();
        
        byte[] sendData = new byte[8+objByte.length];
        System.arraycopy(requestId.getBytes(), 0, sendData, 0, 8);
        System.arraycopy(objByte, 0, sendData, 8, objByte.length);
        
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName("36.50.135.242"),2209);
        socket.send(sendPacket);
        
        //d
        socket.close();
    }
    
    public static String chuanHoaTen(String name) {
        String[] parts = name.trim().split(" ");
        String firstName = parts[parts.length-1].toUpperCase();
        String lastName = "";
        for (int i = 0; i < parts.length-1; i++) {
            if (i > 0) {
                lastName += " ";
            }
            lastName += parts[i].substring(0,1).toUpperCase() + parts[i].substring(1).toLowerCase();
        }
        return firstName + ", " + lastName;
    }
    
    
    public static String chuanHoaNS(String date) {
        String[] parts = date.trim().split("-");
        return parts[1] + "/" + parts[0] + "/" + parts[2];
    }
    
    public static String taoUserName(String name) {
        String[] parts = name.trim().split("\\s+");
        String username = "";
        for (int i = 0; i < parts.length-1; i++) {
            username += parts[i].toLowerCase().charAt(0);
        }
        username += parts[parts.length-1].toLowerCase();
        return username;
    }
}
