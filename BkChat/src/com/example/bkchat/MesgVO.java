package com.example.bkchat;

public class MesgVO {
   private String message;
   private String location;
   
   public MesgVO() {
	   
   }

   public MesgVO(String message, String location) {
	   this.message = message;
	   this.location = location;
   }
   
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	   
   
}
