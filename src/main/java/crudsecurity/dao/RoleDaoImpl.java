package crudsecurity.dao;

import crudsecurity.model.Role;
import crudsecurity.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role get(String roleName) {
        return (Role) entityManager.createQuery("select r from Role r where role=:roleName")
                .setParameter("roleName", roleName)
                .getSingleResult();
    }

}
