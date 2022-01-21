package fr.wetube.wetube.database.repository;

import fr.wetube.wetube.database.model.User;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;
import java.util.UUID;

public class UserRepository extends Repository<User> {

    public Optional<User> findByUsername(String username) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root)
            .where(criteriaBuilder.equal(root.get("username"), username));

        try {
            return Optional.of(
                getEntityManager()
                    .createQuery(query)
                    .getSingleResult()
            );
        } catch (NoResultException err) {
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root)
            .where(criteriaBuilder.equal(root.get("email"), email));

        try {
            return Optional.of(
                getEntityManager()
                    .createQuery(query)
                    .getSingleResult()
            );
        } catch (NoResultException err) {
            return Optional.empty();
        }
    }

    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(getEntityManager().find(User.class, id));
    }
}
