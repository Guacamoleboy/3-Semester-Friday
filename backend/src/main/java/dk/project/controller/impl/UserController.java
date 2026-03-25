package dk.project.controller.impl;

import dk.project.entity.User;
import dk.project.mapper.response.UserResponseMapper;
import dk.project.service.EntityManagerService;

public class UserController extends CRUDController<User> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public UserController(EntityManagerService<User> service) {
        super(service, User.class, UserResponseMapper::toDTO);
    }

}