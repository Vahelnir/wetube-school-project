package fr.wetube.wetube.provider.entitymanager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryProvider {

    @Produces
    @ApplicationScoped
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("wetube");
}
