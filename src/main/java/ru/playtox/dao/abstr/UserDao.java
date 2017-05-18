package ru.playtox.dao.abstr;


import ru.playtox.models.users.User;

public interface UserDao extends GenericDao<Long, User> {

	User getUserByLogin(String login);
}
