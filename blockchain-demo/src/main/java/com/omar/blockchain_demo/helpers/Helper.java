package com.omar.blockchain_demo.helpers;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

/**
 * 
 * @author Omar
 *
 */
public class Helper {

	/**
	 * This method is used by adapted from various developers. Check out github.
	 * @param input
	 * @return
	 */
	// Applies Sha256 to a string and returns the result.
	public static String generateHash(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			// Applies sha256 to our input,
			byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
			StringBuilder sbHex = new StringBuilder(); // This will contain hash as hexidecimal
			for (int i = 0; i < hashBytes.length; i++) {
				String hex = Integer.toHexString(0xff & hashBytes[i]);
				if (hex.length() == 1)
					sbHex.append('0');
				sbHex.append(hex);
			}
			return sbHex.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	
	/**
	 * This method is used by adapted from various developers. Check out github.
	 * @param privateKey
	 * @param input
	 * @return
	 */
	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output = new byte[0];
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			byte[] strByte = input.getBytes();
			dsa.update(strByte);
			byte[] realSig = dsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}

}
