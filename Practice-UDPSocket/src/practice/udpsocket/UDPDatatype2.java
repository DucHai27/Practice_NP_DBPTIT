/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.udpsocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UDPDatatype2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        //a
        String init = ";B23DCCN275;uPXoxPMd";
        byte[] initBuffer = init.getBytes();
        DatagramPacket sendInit = new DatagramPacket(initBuffer,initBuffer.length,InetAddress.getByName("36.50.135.242"),2207);
        socket.send(sendInit);
        
        //b
        byte[] revBuffer = new byte[4096];
        DatagramPacket revPacket = new DatagramPacket(revBuffer,revBuffer.length);
        socket.receive(revPacket);
        String response = new String(revPacket.getData(),0,revPacket.getLength());
                
        //c
        String[] parts = response.split(";",2);
        String requestId = parts[0];
        List<Integer> listNumber = new ArrayList<>();
        if (parts.length > 1 && !parts[1].trim().isEmpty()) {
            for (String x : parts[1].split(",")) {
                listNumber.add(Integer.parseInt(x));
            }
        }
        Collections.sort(listNumber);
        String result = requestId + ";" + listNumber.get(listNumber.size()-1) + "," + listNumber.get(0);
        DatagramPacket sendPacket = new DatagramPacket(result.getBytes(),result.getBytes().length,InetAddress.getByName("36.50.135.242"),2207);
        socket.send(sendPacket);
        
        //d
        socket.close();
    }
}
