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
public class ByteStream2 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("36.50.135.242", 2206)) {
            socket.setSoTimeout(5000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            
            //a
            String req = "B23DCCN275;0aZXVQu6";
            out.write(req.getBytes());
            out.flush();
            
            //b
            byte[] buffer = new byte[4096];
            int len = in.read(buffer);
            if (len == -1) {
                System.out.println("Don't receive data");
                return;
            }
            
            //c
            String resp = new String(buffer,0,len);
            String[] parts = resp.split(",");
            List<Integer> list = new ArrayList<>();
            for (String x : parts) {
                Integer number = Integer.parseInt(x);
                list.add(number);
            }
            Map<Integer,Integer> mp = new HashMap<>();
            Integer max1 = -1, max2 = -1;
            for (int i = 0; i < list.size(); i++) {
                mp.put(list.get(i), i);
                if (list.get(i) > max1) { 
                    max2 = max1;
                    max1 = list.get(i);
                } else if (list.get(i) > max2 && list.get(i) < max1) {
                    max2 = list.get(i);
                }
            }
            
            String result = max2 + "," + mp.get(max2);            
            out.write(result.getBytes());
            out.flush();
            
            socket.close();
        }
    }
}
