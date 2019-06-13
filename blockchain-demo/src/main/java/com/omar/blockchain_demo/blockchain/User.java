package com.omar.blockchain_demo.blockchain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class User {
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public User() {
		generateAsymmetricKeys();
	}

	public void generateAsymmetricKeys() {
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

			keyGen.initialize(ecSpec, random); // 256
			KeyPair keyPair = keyGen.generateKeyPair();

			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}

	}

	public Transaction sendFunds(PublicKey recipient, double valueToTransfer) {
		Transaction newTx = new Transaction(publicKey, recipient, valueToTransfer);
		newTx.generateSignature(privateKey);
		return newTx;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

}
