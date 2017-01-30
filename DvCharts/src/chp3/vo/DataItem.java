package chp3.vo;

import java.io.Serializable;

public class DataItem implements Serializable {
   private double value;
   private String rowKey;
   private String columnKey;
   public DataItem() {
	   
   }

  public DataItem(String rowKey,double value) {
	   this.rowKey = rowKey;
	   this.value = value;
  }
  
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getRowKey() {
		return rowKey;
	}
	
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	
	public String getColumnKey() {
		return columnKey;
	}
	
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	   
   
}
