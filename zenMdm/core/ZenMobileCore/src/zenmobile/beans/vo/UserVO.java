package zenmobile.beans.vo;

import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable{
   private String lastName;
   private String firstName;
   private String secretQuestion;
   private String secretPassword;
   private Date expirationDate;
   private String email;
   private String userName;
   private String email1;
   private String email2;
   private String password;
   private String createdBy;
   private Date createdOn;
   private String updatedBy;
   private Date updatedOn;
   private int userId;
   
   private int[] roles;
   private int[] groups;
   
   private RoleVO[] rolesArr;
   private GroupVO[] groupsArr;
   
   public UserVO() {
	   
   }

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail1() {
		return email1;
	}
	
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	
	public String getEmail2() {
		return email2;
	}
	
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getSecretPassword() {
		return secretPassword;
	}

	public void setSecretPassword(String secretPassword) {
		this.secretPassword = secretPassword;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int[] getRoles() {
		return roles;
	}

	public void setRoles(int[] roles) {
		this.roles = roles;
	}

	public int[] getGroups() {
		return groups;
	}

	public void setGroups(int[] groups) {
		this.groups = groups;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public RoleVO[] getRolesArr() {
		return rolesArr;
	}

	public void setRolesArr(RoleVO[] rolesArr) {
		this.rolesArr = rolesArr;
	}

	public GroupVO[] getGroupsArr() {
		return groupsArr;
	}

	public void setGroupsArr(GroupVO[] groupsArr) {
		this.groupsArr = groupsArr;
	}
 	
}
