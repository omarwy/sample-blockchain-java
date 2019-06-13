package com.omar.blockchain_demo.blockchain;

import java.util.*;

import com.omar.blockchain_demo.helpers.Helper;


/**
 * 
 * @author Omar
 *
 */
public class Block {
	private String currentBlockHash;
	private String previousBlockHash;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private String merkleRoot;
	private String nonce = "000000"; //needed to implement the mining method
	private long timeStamp;

	public Block(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
		this.timeStamp = new Date().getTime();
		this.currentBlockHash = generateBlockhash();
	}
	
	public boolean addTransactionToBlock(Transaction transaction) {
		if (transaction == null) {
			return false;		
		} else {
			transactions.add(transaction);
			generateMerkleTree();
			System.out.println("Transaction Successfully added to Block");
			return true;
		}
	}
	
	public void getTransactionsInBlock() {
		for(int i=0; i<=transactions.size(); i++) {
			System.out.println(transactions.get(i).toString());
		}
	}

	private String generateBlockhash() {
		String generatedBlockHash = Helper
				.generateHash(previousBlockHash + Long.toString(timeStamp) + nonce + merkleRoot);

		return generatedBlockHash;
	}

	/*
	 * 
	 * This method was adapted from the https://github.com/bitcoinj/bitcoinj project
	 * Copyright 2011 Google Inc. Copyright 2014 Andreas Schildbach
	 * 
	 * The Merkle root is based on a tree of hashes calculated from the
	 * transactions:
	 * 
	 * root / \ A B / \ / \ t1 t2 t3 t4
	 * 
	 * The tree is represented as a list: t1,t2,t3,t4,A,B,root where each entry is a
	 * hash.
	 * 
	 * The hashing algorithm is SHA-256. The leaves are a hash of the serialized
	 * contents of the transaction. The interior nodes are hashes of the
	 * concatenation of the two child hashes.
	 * 
	 * This structure allows the creation of proof that a transaction was included
	 * into a block without having to provide the full block contents. Instead, you
	 * can provide only a Merkle branch. For example to prove tx2 was in a block you
	 * can just provide tx2, the hash(tx1) and A. Now the other party has everything
	 * they need to derive the root, which can be checked against the block header.
	 * These proofs aren't used right now but will be helpful later when we want to
	 * download partial block contents.
	 * 
	 * Note that if the number of transactions is not even the last tx is repeated
	 * to make it so (see tx3 above). A tree with 5 transactions would look like
	 * this:
	 * 
	 * root / \ 1 5 / \ / \ 2 3 4 4 / \ / \ / \ t1 t2 t3 t4 t5 t5
	 * 
	 */
	public List<String> generateMerkleTree() {
		ArrayList<String> tree = new ArrayList<String>();
		for (Transaction tx : transactions) {
			tree.add(tx.getTransactionHash());
		}

		int levelOffset = 0; // first level

		// Iterate through each level, stopping when we reach the root (levelSize
		// == 1).
		for (int levelSize = transactions.size(); levelSize > 1; levelSize = (levelSize + 1) / 2) {
			// For each pair of nodes on that level:
			for (int left = 0; left < levelSize; left += 2) {
				// The right hand node can be the same as the left hand, in the
				// case where we don't have enough
				// transactions.
				int right = Math.min(left + 1, levelSize - 1);
				String tleft = tree.get(levelOffset + left);
				String tright = tree.get(levelOffset + right);
				tree.add(Helper.generateHash(tleft + tright));
			}
			// Move to the next level.
			levelOffset += levelSize;
		}
		return tree;
	}

	public String getCurrentBlockHash() {
		return currentBlockHash;
	}
	
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	private void printTransactionValues() {
		for(Transaction t : transactions) {
			System.out.println(t.getValue());
		}
	}

	@Override
	public String toString() {
//		printTransactionValues();
		return "Block [\n\t hash=" + currentBlockHash + ",\n\t previousHash=" + previousBlockHash + ", \n\t timeStamp="
				+ timeStamp + ",\n\t nonce=" + nonce + "\n\t]\n";
	}

}
