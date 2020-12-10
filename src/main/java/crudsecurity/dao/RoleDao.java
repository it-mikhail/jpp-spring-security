package crudsecurity.dao;

import crudsecurity.model.Role;

public interface RoleDao {

    void add(Role role);
    Role get(String role);

}
