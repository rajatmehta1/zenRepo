package zenmobile.beans.vo;

import java.util.Date;

public class RoleVO {
	   private int roleId;
	   private String name;
	   private String description;
	   private String createdBy;
	   private Date createdOn;
	   private String updatedBy;
	   private Date updatedOn;
	   
	   public RoleVO() {
		   
	   }

		public int getRoleId() {
			return roleId;
		}
	
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
	
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
		}
	
		public String getDescription() {
			return description;
		}
	
		public void setDescription(String description) {
			this.description = description;
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
