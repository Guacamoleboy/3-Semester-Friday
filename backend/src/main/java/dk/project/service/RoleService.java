package dk.project.service;

import dk.project.dao.RoleDAO;
import dk.project.entity.Role;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class RoleService extends EntityManagerService<Role> {

    // Attributes
    private final RoleDAO roleDAO;

    // _________________________________________________

    public RoleService(EntityManager em){
        super(new RoleDAO(em), Role.class);
        this.roleDAO = (RoleDAO) this.entityManagerDAO;
    }

    // _________________________________________________

    public boolean existsByName(String name){
        return existByColumn(name, "name");
    }

    // _________________________________________________

    public Role findByName(String name){;
        return findEntityByColumn(name, "name");
    }

    // _________________________________________________

    public String getNameById(UUID roleId){
        return getColumnById(roleId, "name");
    }

    // _________________________________________________

    public String getDescriptionById(UUID roleId){
        return getColumnById(roleId, "description");
    }

}