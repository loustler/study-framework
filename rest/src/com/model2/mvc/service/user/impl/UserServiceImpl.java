package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDao;
import com.model2.mvc.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	/// Field
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;

	/// Constructor
	public UserServiceImpl() {
	}

	/// Method
	public int addUser(User user) throws Exception {
		return userDao.addUser(user);
	}

	public User loginUser(User user) throws Exception {
		User dbUser = userDao.getUser(user.getUserId());

		if (!dbUser.getPassword().equals(user.getPassword())) {
			throw new Exception("로그인에 실패했습니다.");
		}

		return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}

	public Map<String, Object> getUserList(Search search) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", userDao.countData(search));
		map.put("list", userDao.getUserList(search));
		return map;
	}

	public int updateUser(User user) throws Exception {
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return userDao.removeUser(userId);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result = true;
		User user = userDao.getUser(userId);
		if (user != null) {
			result = false;
		}
		return result;
	}

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}