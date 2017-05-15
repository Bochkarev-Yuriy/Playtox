package ru.playtox.dao.abztract;


import ru.playtox.models.roles.Role;

public interface RoleDao extends GenericDao<Long, Role> {

	Role getRoleByRoleName(String roleName);

}