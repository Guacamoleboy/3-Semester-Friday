package dk.project;

import dk.project.config.DotEnv;
import dk.project.config.DotEnvLog;
import dk.project.config.HibernateConfig;
import dk.project.server.Server;
import io.javalin.Javalin;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class ATest {

    // Attributes
    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected static Server restServer;
    protected static Javalin restApp;
    protected static final Logger LOGGER = LoggerFactory.getLogger(DotEnvLog.class);

    // ______________________________________________

    @BeforeAll
    protected void setupAll() {
        System.setProperty("set.env", "test");
        DotEnvLog.logEnvInfo();
        emf = HibernateConfig.getEntityManagerFactoryForTest();
    }

    // ______________________________________________

    @BeforeEach
    protected void setup() {
        em = emf.createEntityManager();
        em.clear();
    }

    // ______________________________________________

    @AfterEach
    protected void cleanup() {
        rollbackTransactionIfActive();
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    // ______________________________________________

    @AfterAll
    protected void closeAll() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // ______________________________________________

    @AfterAll
    protected void stopServer() {
        if (restServer != null) {
            restServer.stop();
            restServer = null;
            restApp = null;
        }
    }

    // ______________________________________________

    protected void startServer(String endpoint) {
        if (restServer == null) {
            restServer = new Server();
            restServer.start();
            restApp = restServer.getApp();
            RestAssured.baseURI = DotEnv.getUrlPath();
            RestAssured.port = DotEnv.getServerPort();
            RestAssured.basePath = endpoint;
        }
    }

    // ______________________________________________

    protected void beginTransactionIfNeeded() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    // ______________________________________________

    protected void commitTransactionIfActive() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    // ______________________________________________

    protected void rollbackTransactionIfActive() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

}