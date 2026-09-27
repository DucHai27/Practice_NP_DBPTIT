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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class UDPString1 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);
        
        //a
        String request = ";B23DCCN275;Lnbk0SzL";
        DatagramPacket sendRequest = new DatagramPacket(request.getBytes(),request.getBytes().length,InetAddress.getByName("36.50.135.242"),2208);
        socket.send(sendRequest);
       
        //b
        byte[] buffer = new byte[4096];
        DatagramPacket revPacket = new DatagramPacket(buffer,buffer.length);
        socket.receive(revPacket);
        String response = new String(revPacket.getData(),0,revPacket.getLength());
        
        //c
        String[] parts = response.split(";");
        String requestId = parts[0];
        String data = parts[1].trim();
        int count[] = new int[256];
        for (int i = 0; i < data.length(); i++) {
            count[data.charAt(i)]++;
        }
        int max = -1;
        for (int i = 0; i < 256; i++) {
            if (count[i] > max) {
                max = count[i];
            }
        }
        String result = requestId + ";";
        Character ch = null; int flag = 0;
        for (int i = 0; i < data.length(); i++) {
            if (count[data.charAt(i)] == max) {
                if (flag == 0) {
                    ch = data.charAt(i);
                    result += String.valueOf(ch) + ":";
                    flag = 1;
                }
                if (data.charAt(i) == ch) {
                    result += (i+1) + ",";
                }
            }
        }
        DatagramPacket sendPacket = new DatagramPacket(result.getBytes(),result.getBytes().length,InetAddress.getByName("36.50.135.242"),2208);
        socket.send(sendPacket);
        
        //d
        socket.close();
    }
    
}
