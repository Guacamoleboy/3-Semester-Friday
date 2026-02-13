package dk.project.service;

import dk.project.dao.RoleDAO;
import dk.project.entity.Role;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class RoleService {

    // Attributes
    private final RoleDAO roleDAO;

    // ____________________________________________________________

    public RoleService(EntityManager em){
        this.roleDAO = new RoleDAO(em);
    }

    // ____________________________________________________________

    public void createRole(Role role){
        validateNotEmpty(role.getName(), "Role.name");
        roleDAO.create(role);
    }

    // ____________________________________________________________

    public void updateRole(Role role){
        validateNotEmpty(role.getName(), "Role.name");
        roleDAO.update(role);
    }

    // ____________________________________________________________

    public void deleteRole(UUID roleId){
        validateNotEmpty(roleId, "roleId");
        roleDAO.deleteById(roleId);
    }

    // ____________________________________________________________

    public void deleteAllRoles(){
        roleDAO.deleteAll();
    }

    // ____________________________________________________________

    public Role getRoleById(UUID roleId){
        validateNotEmpty(roleId, "roleId");
        return roleDAO.getById(roleId);
    }

    // ____________________________________________________________

    public String getNameById(UUID roleId){
        validateNotEmpty(roleId, "roleId");
        return roleDAO.getColumnById(roleId, "name");
    }

    // ____________________________________________________________

    public String getDescriptionById(UUID roleId){
        validateNotEmpty(roleId, "roleId");
        return roleDAO.getColumnById(roleId, "description");
    }

    // ____________________________________________________________

    public Role findByName(String name){
        validateNotEmpty(name, "Role.name");
        return roleDAO.findByName(name);
    }

    // ____________________________________________________________

    public boolean existsByName(String name){
        validateNotEmpty(name, "Role.name");
        return roleDAO.existsByName(name);
    }

    // ____________________________________________________________

    public List<Role> getAllRoles(){
        List<Role> roles = roleDAO.getAll();
        return roles;
    }

    // ____________________________________________________________

    private void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}