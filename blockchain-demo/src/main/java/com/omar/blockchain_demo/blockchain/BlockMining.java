package com.omar.blockchain_demo.blockchain;

import java.util.ArrayList;

public class BlockMining {

	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	private Blockchain chain = null;

	public BlockMining(Blockchain chain) {
		this.chain = chain;
	}

}
