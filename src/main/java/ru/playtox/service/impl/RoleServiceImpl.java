package ru.playtox.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abztract.RoleDao;
import ru.playtox.models.roles.Role;
import ru.playtox.service.abztract.RoleService;
import ru.playtox.service.exceptions.NotFoundException;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	public void addRole(Role role) {
		roleDao.persist(role);
	}

	public Role getRoleByRoleName(String roleName) {
		Role roleFromDB = roleDao.getRoleByRoleName(roleName);

		if (roleFromDB == null) {
			throw new NotFoundException("The role is not found.");
		}

		return roleFromDB;
	}

	public Role getRoleById(Long id) {
		return roleDao.getEntityByKey(id);
	}

	public List<Role> getAllRoles() {
		return roleDao.getAllEntity();
	}

	public void updateRoles(Role role) {
		roleDao.update(role);
	}

	public void deleteRoleById(Long id) {
		roleDao.deleteByKey(id);
	}
}
