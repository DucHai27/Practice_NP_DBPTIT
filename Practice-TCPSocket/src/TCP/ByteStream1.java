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
public class ByteStream1 {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("36.50.135.242", 2206)) {
            
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        
        //a
        String request = "B23DCCN275;tzYtc7rh";
        out.write(request.getBytes());
        out.flush();
        
        //b
        byte[] buffer = new byte[4096];
        int len = in.read(buffer);
        if (len == -1) {
            System.out.println("Don't receive data");
        }
        
        //c
        String response = new String(buffer, 0, len).trim();
        
        String[] resp = response.split(",");
        List<Integer> list = new ArrayList<>();
        for (String x : resp) {
            Integer tmp = Integer.parseInt(x);
            list.add(tmp);
        }
        Collections.sort(list);
        int min = 1000000001;
        int a = 0, b = 0;
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i+1) - list.get(i) <= min) {
                min = list.get(i+1) - list.get(i);
                a = list.get(i);
                b = list.get(i+1);
            }
        }
        String result = min + "," + a + "," + b;
        out.write(result.getBytes());
        out.flush();
        
        socket.close();
        }
    }
}
