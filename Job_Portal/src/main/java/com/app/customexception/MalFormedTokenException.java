package com.app.customexception;

public class MalFormedTokenException extends Exception{
	
	public MalFormedTokenException(String mgs) {
		super(mgs);
	}

}
