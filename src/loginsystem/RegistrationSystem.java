/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginsystem;

/**
 *
 * @author peter
 */
import java.io.*;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegistrationSystem implements Serializable {

    String userFile = "/Users/peter/programs/loginsystem-bitstrips18/users.txt";
    String delimit = "@@";

    public void register(User r) throws IOException {
        BufferedWriter reg = new BufferedWriter(new FileWriter(userFile, true));
        String encryptedPass = this.encrypt(r.getPassword());
        reg.append(r.getUsername() + delimit + encryptedPass);
        reg.close();
    }
    

    public ArrayList<User>  readFile() {
        ArrayList<User> listofUsers = new ArrayList<User>();
        try {
            Scanner re = new Scanner(new File(userFile));
            while (re.hasNextLine()) {
                String x = re.nextLine();
                String[] h = x.split(delimit);
                String userName = h[0];
                String password = h[1];
                User user = new User(userName, password);

                listofUsers.add(user);
               
            }
            re.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return listofUsers;
    }

    public boolean validUsername(String tempuser,ArrayList<User> listofUsers) {
        ListIterator<User> iterator = listofUsers.listIterator();
        while (iterator.hasNext()) {
            User a = iterator.next();
            if (a.getUsername() == tempuser) {
                return false;
            }
        }
        return true;
    }

    public boolean validPassword(String temppass) throws IOException {
        String passFile = "/Users/peter/programs/loginsystem-bitstrips18/dictbadpass.txt";
        // Check for bad password
        try {
            Scanner re = new Scanner(new File(passFile));
            while (re.hasNextLine()) {
                String badPass = re.nextLine();
                if (badPass.equalsIgnoreCase(temppass)) {
                    System.out.println("Bad password found!");
                    return false;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Bad password file not found");
            return false;

        }
        //check password length
        if (temppass.length() < 8 ) {
            System.out.println("The password is too short");
            return false;
        }
        
        //check other requirements
        boolean hasNumber = false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        
        for (int i =0; i < temppass.length(); i++  ) {
            if (Character.isDigit(temppass.charAt(i))) {
                
                //Found a digit , exit
                hasNumber = true;
                
                
            } else if (Character.isUpperCase(temppass.charAt(i))) {
                hasUpperCase = true;
                
            } else if (Character.isLowerCase(temppass.charAt(i))) {
                hasLowerCase = true;
                
            }
            if (hasNumber && hasLowerCase && hasUpperCase) break;
            
        }
        
        if(!hasNumber || !hasLowerCase || !hasUpperCase) {
            return false;
        }
        // Passed all checks
        return true;
    }

    private String encrypt(String password) {
        try {
            //java helper class to perform encryption
            MessageDigest md = MessageDigest.getInstance("MD5");
            //give the helper function the password
            md.update(password.getBytes());
            //perform the encryption
            byte byteData[] = md.digest();
            //To express the byte data as a hexadecimal number (the normal way)
            String encryptedPassword = "";
            for (int i = 0; i < byteData.length; ++i) {
                encryptedPassword += (Integer.toHexString((byteData[i] & 0xFF)
                        | 0x100).substring(1, 3));
            }

            return encryptedPassword;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
