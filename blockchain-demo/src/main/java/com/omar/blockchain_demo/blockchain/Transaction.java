package com.omar.blockchain_demo.blockchain;

import java.security.*;

import com.omar.blockchain_demo.helpers.Helper;

/**
 * 
 * @author Omar
 *
 */
public class Transaction {
	public static int transactionCounter = 0;
	private PublicKey senderPubKey;
	private PublicKey recipientPubKey;
	private double value;
	public byte[] signature;
	private String transactionHash;

	public Transaction(PublicKey from, PublicKey to, double value) {
		this.senderPubKey = from;
		this.recipientPubKey = to;
		this.value = value;
		this.transactionHash = generateTransactionHash();
	}
	
	private String generateTransactionHash() {
		transactionCounter++; // this will be needed later, when setting limit to block size
		return Helper.generateHash(Helper.getStringFromKey(senderPubKey) + Helper.getStringFromKey(recipientPubKey)
				+ Double.toString(value) + transactionCounter);
	}
	
	public void generateSignature(PrivateKey pk) {
		String data = Helper.getStringFromKey(senderPubKey) + Helper.getStringFromKey(recipientPubKey) + Double.toString(value);
		signature = Helper.applyECDSASig(pk, data);
	}
	
	public String getTransactionHash() {
		return transactionHash;
	}
	
	public Double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Transaction [senderPubKey=" + senderPubKey + ", recipientPubKey=" + recipientPubKey + ", value=" + value
				+ ", transactionHash=" + transactionHash + "]";
	}
	
	
	
}
