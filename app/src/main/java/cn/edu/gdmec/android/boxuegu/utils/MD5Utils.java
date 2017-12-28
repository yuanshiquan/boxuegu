package cn.edu.gdmec.android.boxuegu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by apple on 17/12/28.
 */

public class MD5Utils {

   // md5  加密
    public static  String  md5(String tex){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result=digest.digest(tex.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result){
                int number = b & 0xff;
                String  hex = Integer.toHexString(number);
                if(hex.length()==1){
                    sb.append("0"+hex);
                }else{
                    sb.append(hex);
                }

            }
            return  sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
