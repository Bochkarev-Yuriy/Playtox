package ru.playtox.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import ru.playtox.dao.abstr.UserDao;
import ru.playtox.dao.impl.exceptions.MergeException;
import ru.playtox.dao.impl.exceptions.PersistException;
import ru.playtox.dao.impl.exceptions.RemoveException;
import ru.playtox.model.users.User;
import ru.playtox.service.abstr.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(User user) {
		try {
			userDao.persist(user);
			logger.info("Added : " + user);
		} catch (HibernateException e) {
			logger.error("Failed to add an user " + user);
			throw new PersistException("Failed to add an purchase", e);
		}
	}

	@Override
	public void deleteUser(Long id) {
		try {
			userDao.deleteByKey(id);
			logger.info("Deleted user id=" + id);
		} catch (HibernateException e) {
			logger.error("Failed to deleted an user id=" + id);
			throw new RemoveException("Failed to deleted an user", e);
		}
	}

	@Override
	public void updateUser(User user) {
		try {
			userDao.update(user);
			logger.info("Update : " + user);
		} catch (HibernateException e) {
			logger.error("Failed to update an user " + user);
			throw new MergeException("Failed to update an user", e);
		}
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
