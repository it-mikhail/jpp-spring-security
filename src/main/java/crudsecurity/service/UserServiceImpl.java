package crudsecurity.service;

import crudsecurity.dao.RoleDao;
import crudsecurity.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import crudsecurity.dao.UserDao;
import crudsecurity.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;                                                              // <<< security.update

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {                                  // <<< security.update
        this.userDao = userDao;
        this.roleDao = roleDao;                                                                 // <<< security.update
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    @Transactional
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    // security update :: >>>
    @Transactional
    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.add(role);
    }
    // security update :: <<<

}
