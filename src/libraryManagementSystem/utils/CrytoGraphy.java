package libraryManagementSystem.utils;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CrytoGraphy {

	KeyStore keyStore=null;
	FileInputStream fileInputStream =null;
	Key key=null;
	
	public CrytoGraphy() {
		try {
			keyStore=KeyStore.getInstance("JCEKS");
			fileInputStream=new FileInputStream("/media/suraj/HardDisk/eclipse/libraryManagementSystem/resources/mykeystore.jks");
			keyStore.load(fileInputStream,"123456".toCharArray(	));
			key=keyStore.getKey("mykey","123456".toCharArray());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public String encryptionUsingAES(String data,byte[] key) {
		String encryptionUsingAES=null;
		try {
			SecretKeySpec secKey=new SecretKeySpec(key, "AES");
			Cipher cipher=Cipher.getInstance("AES");
			cipher.init(cipher.ENCRYPT_MODE,secKey);
			
			byte[] newBytes=cipher.doFinal(data.getBytes());
			
			encryptionUsingAES= Base64.getEncoder().encodeToString(newBytes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return encryptionUsingAES;
	}
	
	public String decryptionUsingAES(String data,byte[] key) {
		String decryptionUsingAES=null;
		try {
			SecretKeySpec secKey=new SecretKeySpec(key, "AES");
			Cipher cipher=Cipher.getInstance("AES");
			cipher.init(cipher.DECRYPT_MODE,secKey);
			
			byte[] newBytes=cipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
			
			decryptionUsingAES= new String(newBytes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return decryptionUsingAES;
	}
	
	public String setEncrpytedData (String password) {
		String getEncrpytedData=null;
		try {
			getEncrpytedData=encryptionUsingAES(password, key.getEncoded());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return getEncrpytedData;
	}
	
	public String getDecrpytedData (String encryptedPassword) {
		String getDecrpytedData=null;
		try {
			getDecrpytedData=decryptionUsingAES(encryptedPassword, key.getEncoded());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return getDecrpytedData;
	}
}
