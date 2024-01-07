package Common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class Common {
	
	
	//GET RANDOM ID
	private static String RandomString(int size, boolean lowerCase)
    {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        char ch ;
        for(int i=0; i<size; i++)
        {
        	
        	ch = (char)((int)Math.floor(26 * random.nextDouble() + 65 )); 
            builder.append(ch); 
        }
        if(lowerCase)
            return builder.toString().toLowerCase();
        return builder.toString();
    }
    
    public static String getRanDomId() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append(RandomString(11,false));
        builder.append(random.nextInt(1000000,999999999));
        return builder.toString();
    }
    
    
    //ENCODING PASSWORD
    
    public static String getSha256(String plainText) throws Exception{
    	String salt = "/l,/241w,./;XLOCD-2103=XACAMLI";
    	String result = "";
    	plainText = plainText + salt;
    	
    	try {
			byte [] bytes = plainText.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			result = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(md.digest(bytes));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
    
  
}
