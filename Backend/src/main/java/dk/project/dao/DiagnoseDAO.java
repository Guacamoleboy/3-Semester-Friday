package dk.project.dao;

import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseDAO extends EntityManagerDAO {

    // Attributes

    public DiagnoseDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createDiagnose(Diagnose diagnose){
        executeQuery(() -> em.persist(diagnose));
    }

    // ________________________________________

    public void updateDiagnose(Diagnose diagnose){
        executeQuery(() -> em.merge(diagnose));
    }

    // ________________________________________

    public void deleteDiagnose(int id){
        executeQuery(() -> {
            Diagnose diagnose = em.find(Diagnose.class, id);
            if (diagnose != null) em.remove(diagnose);
        });
    }

    // ________________________________________

    public int deleteAllDiagnoses(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM Diagnose x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public Diagnose getDiagnoseById(int id){
        return executeQuery(() -> em.find(Diagnose.class, id));
    }

    // ________________________________________

    public String getNameById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.name FROM Diagnose x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", id)
            .getSingleResult();
        });
    }

    // ________________________________________

    public String getDescriptionById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.description FROM Diagnose x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", id)
            .getSingleResult();
        });
    }

    // ________________________________________

    public List<Diagnose> getAllDiagnoses(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Diagnose x";
            return em.createQuery(JPQL, Diagnose.class)
            .getResultList();
        });
    }

    // ________________________________________

    public boolean existsByName(String name){
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Diagnose x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public Diagnose findByName(String name){
        return executeQuery(() -> em.createQuery(
        "SELECT x FROM Diagnose x WHERE x.name = :name", Diagnose.class)
            .setParameter("name", name)
            .getSingleResult()
        );
    }

}