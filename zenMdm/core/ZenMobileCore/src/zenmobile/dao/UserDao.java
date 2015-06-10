package zenmobile.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import zenmobile.beans.vo.LoginVO;
import zenmobile.beans.vo.RoleVO;
import zenmobile.beans.vo.UserVO;

public interface UserDao {
  /* @Select("SELECT * FROM user") */
   public List<UserVO> fetchAllUsers() throws SQLException;
   
   public UserVO addUser(UserVO userVO) throws SQLException;
   
   public UserVO updateUser(UserVO userVO) throws SQLException;
   
   public boolean deleteUser(int userId) throws SQLException;
   
   public boolean loginUser(LoginVO loginVO) throws SQLException;
   
   public List<RoleVO> fetchAllRoles() throws SQLException;
   
}
