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
public class DataStream2 {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("36.50.135.242", 2207)) {
            socket.setSoTimeout(5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            //a
            String req = "B23DCCN275;0MSYtHPN";
            out.writeUTF(req);
            out.flush();

            //b
            String caesar = in.readUTF();
            int s = in.readInt();

            //c
            String result = "";
            int shift = ((s % 26) + 26) % 26;
            for (int i = 0; i < caesar.length(); i++) {
                char ch = caesar.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    result += (char) ('a' + ((ch - 'a' - shift + 26) % 26));
                } else if (ch >= 'A' && ch <= 'Z') {
                    result += (char) ('A' + ((ch - 'A' - shift + 26) % 26));
                } else {
                    result += ch;
                }
            }

            System.out.println(result);

            out.writeUTF(result);
            out.flush();

            socket.close();
        }
    }
}
