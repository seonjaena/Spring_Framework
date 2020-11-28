package com.sj.spring.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashService {

	public String Encrypt(String file_name) {
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = new byte[16];
		
		random.nextBytes(bytes);
		String salt = new String(Base64.getEncoder().encode(bytes));
	
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update(salt.getBytes());
		md.update(file_name.getBytes());
		String hex = String.format("%064x", new BigInteger(1, md.digest()));
		return hex;
	}
	
}
