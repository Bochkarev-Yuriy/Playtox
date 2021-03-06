package ru.playtox.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.playtox.dao.abstr.RoleDao;
import ru.playtox.model.Role;

@Transactional
@Repository
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao {

	public Role getRoleByRoleName(String name) {
		return (Role) getSession().createQuery("FROM Role WHERE name = :name").setParameter("name", name).uniqueResult();
	}
}