package ru.playtox.dao.impl;

import ru.playtox.dao.abstr.UserDao;
import ru.playtox.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@Override
	public User getUserByLogin(String email) {
		return (User) getSession().createQuery("FROM User WHERE email = :email").setParameter("email", email).uniqueResult();
	}
}
