/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginsystem;

/**
 *
 * @author michael.roy-diclemen
 */
public class LoginSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //register r = new register("Name", "phoneNumber");
        loginframe logi = new loginframe();
       registerframe regi = new registerframe();
       logi.setVisible(true);
       logi.pack();
       logi.setLocationRelativeTo(null);
       regi.setVisible(true);
       regi.pack();
       regi.setLocationRelativeTo(null);
       
    }
    
}
