package practice.udpsocket;

import UDP.Student;
import java.io.*;
import java.net.*;
import java.net.DatagramPacket;

public class UDPObject1 {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        //a.
        String request = ";B23DCCN275;ZWDBVpqM";
        byte[] reqData = request.getBytes();
        DatagramPacket sendRequest = new DatagramPacket(reqData, reqData.length, InetAddress.getByName("36.50.135.242"), 2209);
        socket.send(sendRequest);

        //b. Nhận: 8 byte đầu = requestId, phần còn lại = object Student
        byte[] buffer = new byte[65536];
        DatagramPacket revPacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(revPacket);

        byte[] data = revPacket.getData();
        int len = revPacket.getLength();

        String requestId = new String(data, 0, 8);                 //8 byte đầu

        ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, len - 8); // phần còn lại
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student student = (Student) ois.readObject();
        ois.close();

        //c. Xử lý
        //chuẩn hóa tên - áp dụng cho từng từ
        String[] rawTokens = student.getName().trim().split("\\s+");
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < rawTokens.length; i++) {
            String word = rawTokens[i].toLowerCase();
            word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
            if (i > 0) {
                nameBuilder.append(" ");
            }
            nameBuilder.append(word);
        }
        String name = nameBuilder.toString();
        student.setName(name);

        // c2: tạo email - dùng lại rawTokens (không phân biệt hoa/thường vì sẽ lowercase)
        String firstName = rawTokens[rawTokens.length - 1].toLowerCase();
        StringBuilder initials = new StringBuilder();
        for (int i = 0; i < rawTokens.length - 1; i++) {
            initials.append(Character.toLowerCase(rawTokens[i].charAt(0)));
        }
        student.setEmail(firstName + initials + "@ptit.edu.vn");

        // Serialize lại object đã sửa
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(student);
        oos.flush();
        byte[] objBytes = baos.toByteArray();
        oos.close();

        // Ghép 8 byte requestId (giữ nguyên) + object bytes
        byte[] sendData = new byte[8 + objBytes.length];
        System.arraycopy(requestId.getBytes(), 0, sendData, 0, 8);
        System.arraycopy(objBytes, 0, sendData, 8, objBytes.length);

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("36.50.135.242"), 2209);
        socket.send(sendPacket);

        //d. Đóng socket
        socket.close();
    }
}
