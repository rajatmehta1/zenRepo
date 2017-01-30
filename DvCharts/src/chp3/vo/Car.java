package chp3.vo;

import java.io.Serializable;

//"make_id":"caterham","make_display":"Caterham","make_is_common":"0","make_country":"UK"
public class Car implements Serializable{
  private String make_id;
  private String make_display;
  private String make_is_common;
  private String make_country;
  
  public Car() {
	  
  }

public String getMake_id() {
	return make_id;
}

public void setMake_id(String make_id) {
	this.make_id = make_id;
}

public String getMake_display() {
	return make_display;
}

public void setMake_display(String make_display) {
	this.make_display = make_display;
}

public String getMake_is_common() {
	return make_is_common;
}

public void setMake_is_common(String make_is_common) {
	this.make_is_common = make_is_common;
}

public String getMake_country() {
	return make_country;
}

public void setMake_country(String make_country) {
	this.make_country = make_country;
}
  
  
}
