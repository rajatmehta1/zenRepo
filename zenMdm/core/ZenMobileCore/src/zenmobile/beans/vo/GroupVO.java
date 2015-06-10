package zenmobile.beans.vo;

import java.util.Date;

public class GroupVO {
   private String grpName;
   private int grpId;
   private String createdBy;
   private Date createdOn;
   private String updatedBy;
   private Date updatedOn;
   
   public GroupVO() {
	   
   }

	public String getGrpName() {
		return grpName;
	}
	
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	
	public int getGrpId() {
		return grpId;
	}
	
	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	   
   
}
