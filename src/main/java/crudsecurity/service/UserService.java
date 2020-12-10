package crudsecurity.service;

import crudsecurity.model.Role;
import crudsecurity.model.User;

import java.util.List;

public interface UserService {

    void add(User user);
    void update(User user);
    void delete(User user);
    User getUserById(int userId);
    List<User> getUserList();

    // security update :: >>>
    User getUserByName(String userName);
    void addRole(Role role);
    // security update :: <<<

}
