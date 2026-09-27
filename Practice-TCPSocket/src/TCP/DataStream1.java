/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Admin
 */
public class DataStream1 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("36.50.135.242",2207)) {
            socket.setSoTimeout(5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            
            //a
            String req = "B23DCCN275;Dzfk5xUY";
            out.writeUTF(req);
            out.flush();
            
            //b
            int a = in.readInt();
            int b = in.readInt();
            
            //c
            int sum = a+b;
            int multi = a*b;
            out.writeInt(sum);
            out.writeInt(multi);
            
            socket.close();
            
        }
    }
}
