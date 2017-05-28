package ru.playtox.service.abstr;

import ru.playtox.model.User;

import java.util.List;

public interface UserService {

	void addUser(User user);

	void deleteUser(Long id);

	void updateUser(User user);

	User getUserById(Long id);

	List<User> getAllUser();

	User getUserByUsername(String username);
}
