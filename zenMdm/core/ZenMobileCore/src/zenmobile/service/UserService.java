package zenmobile.service;

import java.util.List;

import zenmobile.beans.vo.RoleVO;
import zenmobile.beans.vo.UserVO;
import zenmobile.utils.ApplicationException;

public interface UserService {
   
	public UserVO addUser(UserVO user) throws ApplicationException;
	
	public boolean deleteUser(int userId) throws ApplicationException;
	
	public boolean deleteUserByUserName(String userName) throws ApplicationException;
	
	public UserVO updateUser(UserVO user) throws ApplicationException;
	
	public List fetchAllUsers() throws ApplicationException;
	
	public UserVO fetchUser(int userId) throws ApplicationException;
	
	public UserVO fetchUserByUserName(String userName) throws ApplicationException;
	
	public List<RoleVO> fetchAllRoles() throws ApplicationException;
}
