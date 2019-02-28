package cryptonite624.android.apps.com.cryptonitescout.Utils;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncyptionUtils {

    public static String encrypt(String s,String strKey){
        return reverse(s);
    }

    public static String reverse(String s){
        String temp = "";
        for(int i = s.length()-1;i>=0;i--){
            temp+=s.charAt(i);
        }
        return temp;
    }

    public static String decrypt(String s,String strKey){
        return  reverse(s);
    }
}
