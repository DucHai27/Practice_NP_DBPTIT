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

class Laptop implements Serializable {
    private static final long serialVersionUID = 20150711L;
    private int id;
    private String code;
    private String name;
    private int quantity;

    public Laptop() {
    }

    public Laptop(int id, String code, String name, int quantity) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}

public class ObjectStream1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket("36.50.135.242", 2209)) {
            socket.setSoTimeout(5000);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            
            //a
            String req = "B23DCCN275;knHcCz2j";
            out.writeObject(req);
            out.flush();
            
            //b
            Laptop laptop = (Laptop)in.readObject();
            
            //c
            String[] name = laptop.getName().split(" ");
            String tmp = name[0];
            name[0] = name[name.length-1];
            name[name.length-1] = tmp;
            String result = String.join(" ", name);
            laptop.setName(result);
            
            int num = laptop.getQuantity();
            int pow = 0;
            while (num > 0) {
                pow = pow * 10 + (num % 10);
                num /= 10;
            }
            laptop.setQuantity(pow);
            
            out.writeObject(laptop);
            out.flush();
            
            socket.close();
        }
    }
}
