package com.orz.common.util.secure;

import java.security.MessageDigest;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Sha256 {
	public String generateSalt() {
        Random random = new Random();
        
        byte[] salt = new byte[8];
        
        random.nextBytes(salt);
        
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < salt.length; i++) {
            sb.append(String.format("%02x",salt[i]));
        }
        
        return sb.toString();
	}
	
	public String getEncrypt(String password, String salt) throws Exception {
	    return getEncrypt(password, salt.getBytes());
	 }
	
	public static String getEncrypt(String password, byte[] salt) throws Exception {

	    byte[] bPassword = password.getBytes();
	    byte[] bytes = new byte[bPassword.length + salt.length];
	    
	    System.arraycopy(bPassword, 0, bytes, 0, bPassword.length);
	    System.arraycopy(salt, 0, bytes, bPassword.length, salt.length);
	    
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(bytes);
	    
	    byte[] byteData = md.digest();
	    
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < byteData.length; i++) {
	        sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
	    }
	    
	    return sb.toString();
	 }
}
