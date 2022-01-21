package fr.wetube.wetube.database.repository;

import fr.wetube.wetube.database.model.User;
import fr.wetube.wetube.database.model.Video;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VideoRepository extends Repository<Video> {

    public List<Video> getRecentVideos(int limit) {
        return this.getRecentVideos(limit, 0);
    }

    public List<Video> getRecentVideos(int limit, int offset) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Video> query = criteriaBuilder.createQuery(Video.class);
        Root<Video> root = query.from(Video.class);
        query.select(root)
            .orderBy(
                criteriaBuilder.desc(root.get("created"))
            );
        return getEntityManager().createQuery(query)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }

    public List<Video> getUserRecentVideos(User user, int limit) {
        return getUserRecentVideos(user, limit, 0);
    }

    public List<Video> getUserRecentVideos(User user, int limit, int offset) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Video> query = criteriaBuilder.createQuery(Video.class);
        Root<Video> root = query.from(Video.class);
        query.select(root)
            .orderBy(
                criteriaBuilder.desc(root.get("created"))
            )
            .where(
                criteriaBuilder.equal(root.get("author_id"), user.getId())
            );

        return getEntityManager()
            .createQuery(query)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }

    public long getVideoCount() {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Video> root = query.from(Video.class);
        query.select(criteriaBuilder.count(root));
        return getEntityManager().createQuery(query)
            .getSingleResult();
    }

    public long getUserVideoCount(User user) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Video> root = query.from(Video.class);
        query.select(criteriaBuilder.count(root))
            .where(
                criteriaBuilder.equal(root.get("author"), user)
            );
        return getEntityManager()
            .createQuery(query)
            .getSingleResult();
    }

    public Optional<Video> findById(UUID id) {
        return Optional.ofNullable(getEntityManager().find(Video.class, id));
    }
}
