package zenmobile.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import zenmobile.beans.vo.AccountDTO;
import zenmobile.beans.vo.LoginVO;
import zenmobile.beans.vo.RoleVO;
import zenmobile.beans.vo.UserVO;
import zenmobile.dao.UserDao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	 private SqlSession sqlSession;
	 
	public List fetchAllUsers() throws SQLException {
		List<UserVO> users = sqlSession.selectList("zenmobile.dao.UserDao.fetchAllUsers");
		//for (UserVO user : users) {
		//	System.out.println("name of user is -----> " + user.getEmail1());
		//}
		return users;
	}
	
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	@Override
	//add roles and groups
	public UserVO addUser(UserVO userVO) throws SQLException {
		sqlSession.insert("zenmobile.dao.UserDao.addUser",userVO);
		return userVO;
	}


	@Override
	public UserVO updateUser(UserVO userVO) throws SQLException {
		sqlSession.update("zenmobile.dao.UserDao.updateUser",userVO);
		return userVO;
	}


	@Override
	public boolean deleteUser(int userId) throws SQLException {
		sqlSession.delete("zenmobile.dao.UserDao.deleteUser","" + userId);
		return true;
	}


	@Override
	public boolean loginUser(LoginVO loginVO) throws SQLException {
		Object rst = sqlSession.selectOne("zenmobile.dao.UserDao.loginUser",loginVO);
		Integer rstInt = new Integer(rst.toString());
		if(rstInt.intValue() > 0 ) return true;
		else return false;
	}


	@Override
	public List<RoleVO> fetchAllRoles() throws SQLException {
		List<RoleVO> rolesVO = sqlSession.selectList("zenmobile.dao.UserDao.fetchAllRoles");
			return rolesVO;
	}

}
