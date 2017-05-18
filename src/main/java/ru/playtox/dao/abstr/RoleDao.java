package ru.playtox.dao.abstr;


import ru.playtox.model.roles.Role;

public interface RoleDao extends GenericDao<Long, Role> {

	Role getRoleByRoleName(String roleName);

}