package dk.project.dao.impl;

import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;

public class SideEffectDAO extends EntityManagerDAO<SideEffect> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public SideEffectDAO(EntityManager em){
        super(em, SideEffect.class);
    }

    // _________________________________________________________________________________________________________________

}