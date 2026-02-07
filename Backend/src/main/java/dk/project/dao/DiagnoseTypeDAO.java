package dk.project.dao;

import dk.project.entity.DiagnoseType;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseTypeDAO extends EntityManagerDAO {

    // Attributes

    public DiagnoseTypeDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createDiagnoseType(DiagnoseType diagnoseType){
        executeQuery(() -> em.persist(diagnoseType));
    }

    // ________________________________________

    public void updateDiagnoseType(DiagnoseType diagnoseType){
        executeQuery(() -> em.merge(diagnoseType));
    }

    // ________________________________________

    public void deleteDiagnoseType(int id){
        executeQuery(() -> {
            DiagnoseType diagnoseType = em.find(DiagnoseType.class, id);
            if (diagnoseType != null) em.remove(diagnoseType);
        });
    }

    // ________________________________________


    public int deleteAllDiagnoseTypes(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM DiagnoseType x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public DiagnoseType getDiagnoseTypeById(int id){
        return executeQuery(() -> em.find(DiagnoseType.class, id));
    }

    // ________________________________________

    public String getNameById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.name FROM DiagnoseType x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", id)
            .getSingleResult();
        });
    }

    // ________________________________________

    public List<DiagnoseType> getAllDiagnoseTypes(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseType x";
            return em.createQuery(JPQL, DiagnoseType.class)
            .getResultList();
        });
    }

    // ________________________________________

    public boolean existsByName(String name){
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM DiagnoseType x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public DiagnoseType findByName(String name){
        return executeQuery(() -> em.createQuery(
        "SELECT x FROM DiagnoseType x WHERE x.name = :name", DiagnoseType.class)
            .setParameter("name", name)
            .getSingleResult()
        );
    }

}