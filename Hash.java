package sqlite_db;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class Hash {

    public static String hashed;
    public static String fsalt;
    public static String hash(String str, String salt) throws NoSuchAlgorithmException {
        StringBuilder psalt = new StringBuilder();
        Random rand = new Random();
        int s;
        if (salt.equals(""))
        {
            for (int i = 0;i<16;i++)
            {
                s = rand.nextInt();
                psalt.append(s);
            }
            hashed = str+ psalt;
            fsalt = String.valueOf(psalt);
        }else{
            fsalt = salt;
            hashed = str+ salt;
        }
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(hashed.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i =0;i<bytes.length;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        hashed = sb.toString();
        //System.out.println(hashed);
        return hashed;
    }

    public static boolean comp_hash(String nhash, String hash, String salt) throws NoSuchAlgorithmException {
        return hash(nhash, salt).equals(hash);
    }

    public static String senha(){
        System.out.println("Digite a senha q deseja");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
