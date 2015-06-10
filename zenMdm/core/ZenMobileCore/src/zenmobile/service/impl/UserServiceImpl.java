package zenmobile.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zenmobile.beans.vo.RoleVO;
import zenmobile.beans.vo.UserVO;
import zenmobile.dao.UserDao;
import zenmobile.service.UserService;
import zenmobile.utils.ApplicationException;
import zenmobile.utils.Messages;


public class UserServiceImpl implements UserService {

	public UserDao userDao;

	@Override
	public UserVO addUser(UserVO uservo) throws ApplicationException {
		try {
			 userDao.addUser(uservo);
		} catch(SQLException e) {
			e.printStackTrace(); // use logging mechanism for handling this later
			throw new ApplicationException(Messages.STANDARD_DATABASE_ERR_MSG);
		}
		return uservo;
	}
	

	@Override
	public boolean deleteUser(int userId) throws ApplicationException {
		try {
			  userDao.deleteUser(userId);
			}catch(SQLException e) {
				e.printStackTrace();
				throw new ApplicationException(Messages.STANDARD_DATABASE_ERR_MSG);
			}
		return true;
	}

	@Override
	public boolean deleteUserByUserName(String userName)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserVO updateUser(UserVO uservo) throws ApplicationException {
		try {
			  userDao.updateUser(uservo);
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(Messages.STANDARD_DATABASE_ERR_MSG);
		}	
		return uservo;
	}

	@Override
	public List fetchAllUsers() throws ApplicationException {
		List<UserVO> result = null;
		try {
			result = userDao.fetchAllUsers();
		}catch(SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return result;
	}

	@Override
	public UserVO fetchUser(int userId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO fetchUserByUserName(String userName)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public List<RoleVO> fetchAllRoles() throws ApplicationException {
		List<RoleVO> rolesList = null;
		try {
			rolesList = userDao.fetchAllRoles();
		}catch(SQLException e) {
			throw new ApplicationException(e.getMessage());
		} 
		return rolesList;
	}
	
	
	

}
