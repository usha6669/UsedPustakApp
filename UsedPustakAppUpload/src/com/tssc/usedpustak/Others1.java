package com.tssc.usedpustak;

import java.io.Serializable;

public class Others1 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name,email;
	
	public Others1(String name)
	{
		this.name=name;
		
	}
	public void Others(final String name) {
		
		  this.name=name;
		 	}
		
		public String getName() {
			return this.name;
		}
		
	
	
	
}
