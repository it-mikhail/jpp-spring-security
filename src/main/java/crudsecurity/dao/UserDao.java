package crudsecurity.dao;

import crudsecurity.model.User;
import java.util.List;

public interface UserDao {
     void add(User user);
     void update(User user);
     void delete(User user);
     User getUserById(int userId);
     List<User> getUserList();

     // security update :: >>>
     User getUserByName(String userName);
     // security update :: <<<
}
