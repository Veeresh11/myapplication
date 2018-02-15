package com.example.myapplication;

public class User {
	
	String user_id;
	boolean isProcessRunning;
	String finalValue;
	
	private static final User instance = new User();
    
    //private constructor to avoid client applications to use constructor
    private User(){
    	
    }

    public static User getInstance(){
        return instance;
    }
	
    
    
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isProcessRunning() {
		return isProcessRunning;
	}
	public void setProcessRunning(boolean isProcessRunning) {
		this.isProcessRunning = isProcessRunning;
	}
	public String getFinalValue() {
		return finalValue;
	}
	public void setFinalValue(String finalValue) {
		this.finalValue = finalValue;
	}
	
}
