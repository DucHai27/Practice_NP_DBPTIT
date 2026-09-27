/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP;

import java.io.*;
import java.net.*;
import java.util.*;
        

public class Character1 {

        public static void main(String[] args) throws IOException {
            try (Socket socket = new Socket("36.50.135.242",2208)) {
                socket.setSoTimeout(5000);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                //a, Gửi mã sv và câu hỏi định dạng studentCode;qCode
                String request = "B23DCCN275;vaNwgwaY" ;
                out.write(request);
                out.newLine();
                out.flush();
                
                //b, Nhận 1 chuỗi ngẫu nhiên danh sách tên miền từ server
                String response = in.readLine();
                if (response== null) {
                    System.out.println("Don't receive data from Server");
                    return;
                }
                
                //c, Lọc các tên miền
                String[] domains = response.split(",");
                List<String> listDomains = new ArrayList<>();
                for (String x : domains) {
                    String d =  x.trim();
                    if (d.endsWith(".edu")) {
                        listDomains.add(d);
                    }
                }
                //Gửi lên server
                String result = String.join(", ", listDomains);
                out.write(result);
                out.newLine();
                out.flush();
                
                //d, Đóng kết nối
                socket.close();
                
            }
    }
    
}
