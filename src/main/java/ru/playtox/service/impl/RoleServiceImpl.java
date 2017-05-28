package ru.playtox.service.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.playtox.dao.abstr.RoleDao;
import ru.playtox.dao.impl.exceptions.MergeException;
import ru.playtox.dao.impl.exceptions.PersistException;
import ru.playtox.dao.impl.exceptions.RemoveException;
import ru.playtox.model.Role;
import ru.playtox.service.abstr.RoleService;
import ru.playtox.service.exceptions.NotFoundException;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	private final static Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	public void addRole(Role role) {
		try {
			roleDao.persist(role);
			logger.info("Added : " + role);
		} catch (HibernateException e) {
			logger.error("Failed to add an role " + role);
			throw new PersistException("Failed to add an role", e);
		}
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
		try {
			roleDao.update(role);
			logger.info("Update : " + role);
		} catch (HibernateException e) {
			logger.error("Failed to update an role " + role);
			throw new MergeException("Failed to update an role", e);
		}
	}

	public void deleteRoleById(Long id) {
		try {
			roleDao.deleteByKey(id);
			logger.info("Deleted role id=" + id);
		} catch (HibernateException e) {
			logger.error("Failed to deleted an role id=" + id);
			throw new RemoveException("Failed to deleted an role", e);
		}
	}
}
