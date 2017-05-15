package ru.playtox.service.abztract;

import ru.playtox.models.users.User;

import java.util.List;

public interface UserService {

	void addUser(User user);

	void deleteUser(Long id);

	void updateUser(User user);

	User getUserById(Long id);

	List<User> getAllUser();

}
