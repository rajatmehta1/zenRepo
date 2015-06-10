package zenmobile.tests;

import java.util.Date;
import java.util.List;

import zenmobile.beans.vo.UserVO;
import zenmobile.dao.UserDao;
import zenmobile.dao.impl.UserDaoImpl;
import zenmobile.service.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestDB {
  
	public static void main(String[] args) throws Exception {
		System.out.println("Test database connection");
		ApplicationContext ctx = new FileSystemXmlApplicationContext("C:/Harpreet/workspace/zenmobilePrj/WebContent/WEB-INF/applicationContext.xml");
		//UserDaoImpl userDaoI = (UserDaoImpl) ctx.getBean("userDaoImpl");
		//UserDaoImpl userDaoI = (UserDaoImpl) ctx.getBean("userDaoImpl");
		//System.out.println(userDaoI.getAccounts(1));
		/* UserService userService = (UserService) ctx.getBean("userService");
		List<UserVO> users = userService.fetchAllUsers();
		for (UserVO userVO : users) {
			System.out.println(userVO.getUserName());
		} */
		
		UserDao userDaoObj = (UserDaoImpl) ctx.getBean("userDao");
		List<UserVO> users = userDaoObj.fetchAllUsers();
		for (UserVO userVO : users) {
			System.out.println(userVO.getUserName());
		}
		
		UserVO userVO = new UserVO();
		   userVO.setCreatedBy("rajat");
		   userVO.setUpdatedBy("rajat");
		   userVO.setUpdatedOn(new Date());
		   userVO.setCreatedOn(new Date());
		   userVO.setEmail("rajat.me@gmail.com");
		   userVO.setExpirationDate(new Date());
		   userVO.setFirstName("Rajat");
		   userVO.setLastName("Mehtta");
		   userVO.setPassword("testpwd");
		   userVO.setUserName("userName");
		   userVO.setSecretQuestion("Secret question goes here");
		   userVO.setSecretPassword("secreate pwd");
		   
		   userDaoObj.addUser(userVO);
		   
		   System.out.println("siccessfully inserted record");
		//System.out.println(userService.fetchAllUsers().size());
	}
	
	//prints all users from users table
	public void printAllUsers() {
		
	}
}
