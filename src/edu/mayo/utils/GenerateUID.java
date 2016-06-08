package edu.mayo.utils;

import java.util.UUID;

public class GenerateUID {
	
	public GenerateUID(){}
	
	public String getUID(){
		//generate random UUIDs
	    UUID uid = UUID.randomUUID();
	    Long us = uid.getMostSignificantBits();
	    Long aus = Math.abs(us);
	    
	    return aus.toString();
	}

}
