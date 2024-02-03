package com.snhu.sslserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXED: added a RESTful checkpoint to access secure web page
@RestController
class ServerController{
    @RequestMapping("/hash")
    public String myHash(){
    	// declare MessageDigest object
    	MessageDigest messageDigest = null;
    	// testing with name & some unique text
    	String data = "Janai Cano: security check";
    	//checkSum value initialized as null
    	String checkSum = null;
    	
    	// error catching
    	try {
    		// initializing MessageDigest object using SHA-256 cipher
    		messageDigest = MessageDigest.getInstance("SHA-256");
    	}
    	catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	
    	// passing data to messageDigest object
    	messageDigest.update(data.getBytes());
    	
    	//computing messageDigest
    	byte[] digest = messageDigest.digest();
    	
    	//create hash value
    	checkSum = this.bytesToHex(digest);
    	
    	//return formatted string
    	return "<p>data:" +data + "<br>Name of the cipher used: SHA-256" + 
    			"<br>Checksum hash value: " + checkSum + "</p>";
    }
    
    // function that converts bytes to a hexadecimal string
    public String bytesToHex(byte[] bytes) {
    	StringBuilder springBuilder = new StringBuilder();
    	
    	//loop through byte array
    	for (byte HashByte : bytes) {
    		int intVal = 0xff & HashByte;
    		if (intVal < 0) {
    			springBuilder.append('0');
    		}
    		springBuilder.append(Integer.toHexString(intVal));
    	}
    	//return hexadecimal string
    	return springBuilder.toString();
    		}
    	
    }

