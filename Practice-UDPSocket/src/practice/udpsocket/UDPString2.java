/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practice.udpsocket;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.DatagramPacket;

/**
 *
 * @author Admin
 */
public class UDPString2 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        
        //a
        String request = ";B23DCCN275;Axf4Wc7K";
        DatagramPacket sendRequest = new DatagramPacket(request.getBytes(),request.getBytes().length, InetAddress.getByName("36.50.135.242"),2208);
        socket.send(sendRequest);
        
        //b
        byte[] revBuffer = new byte[4096];
        DatagramPacket revPacket = new DatagramPacket(revBuffer,revBuffer.length);
        socket.receive(revPacket);
        String response = new String(revPacket.getData(),0,revPacket.getLength());
        
        //c
        String[] parts = response.split(";",2);
        String requestId = parts[0];
        String[] data = parts[1].trim().split(" ");
        String result = requestId+";";
        for (String x : data) {
            result += x.substring(0,1).toUpperCase() + x.substring(1).toLowerCase() + " ";
        }
        DatagramPacket sendPacket = new DatagramPacket(result.getBytes(),result.getBytes().length,InetAddress.getByName("36.50.135.242"),2208);
        socket.send(sendPacket);
        
        //d
        socket.close();
    }
}
