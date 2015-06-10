package zenmobile.mvc.controllers;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import zenmobile.beans.vo.RoleVO;
import zenmobile.beans.vo.UserVO;
import zenmobile.dao.UserDao;
import zenmobile.service.UserService;
import zenmobile.utils.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value="/user")
public class UserController {
	
	public UserDao userDao;
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET) 
	public String getUserMainPage(Model model) { 
		model.addAttribute("uservo", new UserVO());
		return "user/createUser"; 
	} 
/*
	@RequestMapping(method=RequestMethod.POST)
	public String addUser(UserVO uservo, BindingResult result) throws ApplicationException {
		if (result.hasErrors()) {
			return "user/createUser";
		}
		userService.addUser(uservo);
		System.out.println(uservo.getLastName());
		return "user/userMain";
	}
*/
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody UserVO addUser(HttpServletResponse response,@RequestBody UserVO uservo) throws ApplicationException {
		System.out.println("request come here");
		System.out.println("request come here --> " + uservo.getFirstName());
		userService.addUser(uservo);
		System.out.println(uservo.getLastName());
		return uservo;
	}
	
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	public String updateUser(UserVO uservo, BindingResult result) throws ApplicationException {
		if (result.hasErrors()) {
			return "user/createUser";
		}
		userService.updateUser(uservo);
		System.out.println(uservo.getLastName());
		return "user/createUser";
	}
	
	@RequestMapping(value="deleteUser",method=RequestMethod.GET)
	public String deleteUser(UserVO uservo, BindingResult result) throws ApplicationException {
		if (result.hasErrors()) {
			return "user/createUser";
		}
			userService.deleteUser(uservo.getUserId());
		System.out.println(uservo.getLastName());
		return "user/createUser";
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
    @ModelAttribute("rolesList")
    public Collection<RoleVO> populateRoles() throws ApplicationException {
        return userService.fetchAllRoles();
    }

	
	
}
