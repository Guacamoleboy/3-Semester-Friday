package dk.project.controller.impl;

import dk.project.entity.Role;
import dk.project.service.internal.EntityManagerService;
import dk.project.mapper.response.RoleResponseMapper;

public class RoleController extends CRUDController<Role> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public RoleController(EntityManagerService<Role> service) {
        super(service, Role.class, RoleResponseMapper::toDTO);
    }

}