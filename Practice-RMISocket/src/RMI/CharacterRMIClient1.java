/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.URI;
import java.net.URISyntaxException;
/**
 *
 * @author Admin
 */
public class CharacterRMIClient1 {
    public static void main(String[] args) {
        try {
            String scode = "B23DCCN275";
            String qcode = "m5D6vMa6";
            //a
            Registry registry = LocateRegistry.getRegistry("36.50.135.242", 1099);
            CharacterService service = (CharacterService) registry.lookup("RMICharacterService");
            String data = service.requestCharacter(scode, qcode);
            //b
            String encode = new URI(null,null,data,null).toASCIIString();
            //c
            service.submitCharacter(scode, qcode, encode);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
