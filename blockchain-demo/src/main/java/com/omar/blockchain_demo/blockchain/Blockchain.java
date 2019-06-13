package com.omar.blockchain_demo.blockchain;

import java.util.ArrayList;

/**
 * 
 * @author Omar
 *
 */

public class Blockchain {

	public static final int BLOCK_SIZE = 10; //needed in later stage to specify transactions limitation in a block
	private ArrayList<Block> blockchain = new ArrayList<Block>();

	
	public int getChainSize() {
		int i = blockchain.size()-1;
		return i;
	}
	
	public void printBlocks() {
		for(Block b : blockchain) {
			System.out.println(b.toString());
		}
	}
	
	public ArrayList<Block> getBlockchain() {
		return blockchain;
	}

//	@Override
//	public String toString() {
//		return "Blockchain: \n\t " + "genesis block: " + .toString() + "\n\t block: " + block1.toString();
//	}

}
