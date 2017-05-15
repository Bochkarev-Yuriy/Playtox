package ru.playtox.dao.abztract;


import ru.playtox.models.users.User;

public interface UserDao extends GenericDao<Long, User> {

	User getUserByLogin(String login);
}
