package com.mycompany.exceptions;

public class UserNotFoundException  extends Throwable{

	public UserNotFoundException(String message){
		super(message);
	}
}
