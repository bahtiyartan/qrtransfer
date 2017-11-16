package com.yedibit;

import java.util.Vector;

public class Content {

	public Vector<String> Parts = new Vector<String>();
	public String Hash;

	public Content(Vector pParts, String pHash) {
		this.Parts = pParts;
		this.Hash = pHash;
	}

}
