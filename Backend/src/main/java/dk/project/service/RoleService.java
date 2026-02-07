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
        validateNotBlank(role.getName(), "Role.name");
        roleDAO.createRole(role);
    }

    // ____________________________________________________________

    public void updateRole(Role role){
        validateNotBlank(role.getName(), "Role.name");
        roleDAO.updateRole(role);
    }

    // ____________________________________________________________

    public void deleteRole(UUID roleId){
        validateNotNull(roleId, "roleId");
        roleDAO.deleteRole(roleId);
    }

    // ____________________________________________________________

    public int deleteAllRoles(){
        return roleDAO.deleteAllRoles();
    }

    // ____________________________________________________________

    public Role getRoleById(UUID roleId){
        validateNotNull(roleId, "roleId");
        return roleDAO.getRoleById(roleId);
    }

    // ____________________________________________________________

    public String getNameById(UUID roleId){
        validateNotNull(roleId, "roleId");
        return roleDAO.getNameById(roleId);
    }

    // ____________________________________________________________

    public String getDescriptionById(UUID roleId){
        validateNotNull(roleId, "roleId");
        return roleDAO.getDescriptionById(roleId);
    }

    // ____________________________________________________________

    public Role findByName(String name){
        validateNotBlank(name, "Role.name");
        return roleDAO.findByName(name);
    }

    // ____________________________________________________________

    public boolean existsByName(String name){
        validateNotBlank(name, "Role.name");
        return roleDAO.existsByName(name);
    }

    // ____________________________________________________________

    public List<Role> getAllRoles(){
        List<Role> roles = roleDAO.getAllRoles();
        return roles;
    }

    // ____________________________________________________________
    // Validate blank

    private void validateNotBlank(String paramValue, String fieldName) {
        if (paramValue == null || paramValue.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

    // ____________________________________________________________
    // Validate null

    private void validateNotNull(Object paramValue, String fieldName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
    }

}