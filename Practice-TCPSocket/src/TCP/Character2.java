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
public class Character2 {
    public static void main(String[] args) throws Exception{
        try (Socket socket = new Socket("36.50.135.242",2208)) {
            socket.setSoTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            //a, Send code
            String request = "B23DCCN275;0t6AJ3eU";
            out.write(request);
            out.newLine();
            out.flush();
           
            //b, Receive string
            String response = in.readLine();
            
            //c, service
            int[] x = new int[255];
            for (int i = 0; i < response.length(); i++) {
                char c = response.charAt(i);
                if (Character.isLetterOrDigit(c)) {
                    x[c]++;
                }
            }
            List<String> list = new ArrayList<>();
            for (int i = 0; i < response.length(); i++) {
                char c = response.charAt(i);
                if (x[c] > 1) {
                    list.add(c + ":" + x[c]);
                    x[c] = 0;
                }
            }
            String tmp = String.join(",", list) + ",";
            out.write(tmp);
            out.newLine();
            out.flush();
            
            //d, close
            socket.close();
        }
    }
}
