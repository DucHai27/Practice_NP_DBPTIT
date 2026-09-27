package practice.udpsocket;

import java.net.*;
import java.util.*;
import java.net.DatagramPacket;

public class UDPDatatype1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(5000);

        //a
        String initMsg = ";B23DCCN275;PZrUWqLt";
        byte[] sendBuf = initMsg.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, InetAddress.getByName("36.50.135.242"), 2207);
        
        socket.send(sendPacket);
        
        
        //b
        byte[] recvBuf = new byte[4096];
        DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
        socket.receive(recvPacket);
        String response = new String(recvPacket.getData(), 0, recvPacket.getLength());
        
        //c
        String[] parts = response.split(";", 3);
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1].trim());
        List<Integer> given = new ArrayList<>();
        if (parts.length > 2 && !parts[2].trim().isEmpty()) {
            for (String s : parts[2].split(",")) {
                given.add(Integer.parseInt(s.trim()));
            }
        }
        Set<Integer> givenSet = new HashSet<>(given);
        List<Integer> missing = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!givenSet.contains(i)) {
                missing.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(requestId).append(";");
        for (int i = 0; i < missing.size(); i++) {
            sb.append(missing.get(i));
            if (i < missing.size() - 1) sb.append(",");
        }
        String resultMsg = sb.toString();

        byte[] resultBuf = resultMsg.getBytes();
        DatagramPacket resultPacket = new DatagramPacket(resultBuf, resultBuf.length, InetAddress.getByName("36.50.135.242"), 2207);
        socket.send(resultPacket);

        //d
        socket.close();
    }
}