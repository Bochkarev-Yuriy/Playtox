package ru.playtox.dao.abstr;

import ru.playtox.model.User;

public interface UserDao extends GenericDao<Long, User> {

	User getUserByLogin(String login);
}
