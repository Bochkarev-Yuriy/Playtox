package ru.playtox.service.impl;

import ru.playtox.dao.abztract.UserDao;
import ru.playtox.models.users.User;
import ru.playtox.service.abztract.UserService;
import ru.playtox.service.exceptions.UserDuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		String login = user.getUsername();

		if (userDao.getUserByLogin(login) != null) {
			throw new UserDuplicateException("User '" + login + "' already exists");
		}

		userDao.persist(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteByKey(id);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getEntityByKey(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllEntity();
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByLogin(username);
	}
}
