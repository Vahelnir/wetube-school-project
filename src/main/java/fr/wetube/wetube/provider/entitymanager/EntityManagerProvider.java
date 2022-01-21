package fr.wetube.wetube.provider.entitymanager;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerProvider {

    @Inject
    EntityManagerFactory entityManagerFactory;

    @Produces
    @RequestScoped
    public EntityManager produceEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
