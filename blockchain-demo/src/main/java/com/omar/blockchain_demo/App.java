package com.omar.blockchain_demo;

import java.security.Security;

import com.omar.blockchain_demo.blockchain.Block;
import com.omar.blockchain_demo.blockchain.Blockchain;
import com.omar.blockchain_demo.blockchain.Transaction;
import com.omar.blockchain_demo.blockchain.User;
import com.omar.blockchain_demo.helpers.Helper;

/**
 * 
 * @author Omar
 *
 */
public class App {
	
	public static Transaction genesisTransaction;
	
	public static void main(String[] args) {
		
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); // Setup Bouncey castle as a
																						// Security Provider
		User genesisUser = new User();
		User u1 = new User();
		User u2 = new User();
		
		Blockchain blockchain = new Blockchain();
		
		genesisTransaction = new Transaction(genesisUser.getPublicKey(), u2.getPublicKey(), 30.9);
		Block genesisBlock = new Block("0");
		genesisBlock.addTransactionToBlock(genesisTransaction);
		blockchain.getBlockchain().add(genesisBlock);
		
		
		Block block1 = new Block(genesisBlock.getCurrentBlockHash());
		Block block2 = new Block(block1.getCurrentBlockHash());
		Block block3 = new Block(block2.getCurrentBlockHash());
		
		Transaction tx2 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 100.9);
		Transaction tx3 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 30.9);
		Transaction tx4 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 43.9);
		Transaction tx5 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 50.9);
		
		Transaction tx6 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 12.9);
		Transaction tx7 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 3.9);
		Transaction tx8 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 83.9);
		Transaction tx9 = new Transaction(u1.getPublicKey(), u2.getPublicKey(), 890.9);
		
		
		
		block1.addTransactionToBlock(tx2);
		block1.addTransactionToBlock(tx5);
		block1.addTransactionToBlock(tx4);
		block1.addTransactionToBlock(tx3);
		blockchain.getBlockchain().add(block1);
		
		blockchain.printBlocks();
		
		System.out.println("=============================================================");
		
		block2.addTransactionToBlock(tx9);
		block2.addTransactionToBlock(tx8);
		block2.addTransactionToBlock(tx7);
		block2.addTransactionToBlock(tx6);
		blockchain.getBlockchain().add(block2);
		
		blockchain.printBlocks();
		
		System.out.println("=============================================================");
		
		block3.addTransactionToBlock(u2.sendFunds(u1.getPublicKey(), 95.0));
		blockchain.getBlockchain().add(block3);
		
		blockchain.printBlocks();
		
	}
}
