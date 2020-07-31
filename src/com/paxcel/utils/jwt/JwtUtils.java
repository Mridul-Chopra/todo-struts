package com.paxcel.utils.jwt;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Collections;

public class JwtUtils {

	private static String SECRET_KEY ="oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI";
	
	
	public static String generateToken(String id, String issuer, Map<String,Object> claims , long ttlMillis){
		
		/*Signature algorithm to sign the jwt*/
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		/*Get the current time*/
		 long nowMillis = System.currentTimeMillis(); // get current time in milli seconds
		 Date now = new Date(nowMillis); // convert the millis to date format
		 
		 System.out.println(now);
		 
		 byte [] apiSecretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);	//	convert the key to array of bytes
		 Key signingKey = new SecretKeySpec(apiSecretKeyBytes , signatureAlgorithm.getJcaName()); // construct the secret key using given byte array and the algorithm
		 
		 /*
		  * Building the jwt  
		  */
		 JwtBuilder builder = Jwts.builder() ;	// get instance of jwt builder
				 							
		 // set all the claims in the jwt header
		 if(!Collections.isEmpty(claims)) {
			 
			 for(Map.Entry<String, Object> entry : claims.entrySet() ) {
				 builder.claim(entry.getKey() , entry.getValue());
			 }
		 }
		 
		 builder.setId(id) 			// 	the unique id of the jwt			
			.setIssuedAt(now)	// set the time issued at
			.setIssuer(issuer)	// sets the jwt issuer
			.setHeaderParam("typ","JWT") // set the type of token in the header
			.signWith(signatureAlgorithm , signingKey); // sign with our secret key
		 
		 
		 // set time of expiration if specifiedd
		 if(ttlMillis >0 ) {
			 
			 long expMillis = nowMillis + ttlMillis; 
			 Date exp = new Date(expMillis);
			 builder.setExpiration(exp);
		 }
				 								
		 return builder.compact(); // build the jwt and convert to a string
	}
	
	
    public static Claims decodeJWT(String jwt) throws Exception{

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
    
    
	public static int extractIdfromToken(String token) {
		try {
			Claims claims= JwtUtils.decodeJWT(token); // get all the claims of the user from the token
			int id = (Integer)claims.get("id" , Object.class); // get the id from the jwt claims
			return id;
		}catch(Exception e) {
			System.out.println("Invalid token"); // in case of invalid token
			return -1;
		}
	}
	
}
