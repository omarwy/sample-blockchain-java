package com.omar.blockchain_demo.blockchain;

import java.util.ArrayList;

public class BlockMining {

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private Blockchain chain = null;

	public BlockMining(Blockchain chain) {
		this.chain = chain;
	}

//	public void mine (Transaction tx) {
//		transactions.add(tx);
//		if (transactions.size() > ) {
//			
//		}
//	}
//
//	// Increases nonce value until hash target is reached.
//	public void mineBlock(int difficulty) {
//		merkleRoot = StringUtil.getMerkleRoot(transactions);
//		String target = StringUtil.getDificultyString(difficulty); // Create a string with difficulty * "0"
//		while (!hash.substring(0, difficulty).equals(target)) {
//			nonce++;
//			hash = calculateHash();
//		}
//		System.out.println("Block Mined!!! : " + hash);
//	}

}
