package dk.project.service;

import dk.project.dao.impl.RoleDAO;
import dk.project.entity.Role;
import jakarta.persistence.EntityManager;
import java.util.UUID;

public class RoleService extends EntityManagerService<Role> {

    // Attributes
    private final RoleDAO roleDAO;

    // _________________________________________________________________________________________________________________

    public RoleService(EntityManager em) {
        super(new RoleDAO(em), Role.class);
        this.roleDAO = (RoleDAO) this.entityManagerDAO;
    }

    // _________________________________________________________________________________________________________________

    public boolean existsByName(String name) {
        return existByColumn(name, Role.Columns.NAME);
    }

    // _________________________________________________________________________________________________________________

    public Role findByName(String name) {
        return findEntityByColumn(name, Role.Columns.NAME);
    }

    // _________________________________________________________________________________________________________________

    public String getNameById(UUID roleId) {
        return getColumnById(roleId, Role.Columns.NAME);
    }

    // _________________________________________________________________________________________________________________

    public String getDescriptionById(UUID roleId) {
        return getColumnById(roleId, Role.Columns.DESCRIPTION);
    }

}

