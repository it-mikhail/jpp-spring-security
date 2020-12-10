package crudsecurity.dao;

import org.springframework.stereotype.Repository;

import crudsecurity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> getUserList() {
        return entityManager.createQuery("select u from User u")
                .getResultList();
    }

    // security update :: >>>
    @Override
    public User getUserByName(String userName) {
        return (User) entityManager.createQuery("select u from User u where name=:name")
                .setParameter("name", userName)
                .getSingleResult();
    }
    // security update :: <<<
}
