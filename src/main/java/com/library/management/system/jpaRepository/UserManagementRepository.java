package com.library.management.system.jpaRepository;

import com.library.management.system.jpaEntity.Book;
import com.library.management.system.jpaEntity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserManagementRepository extends CrudRepository<User, String> {

    public User getUserByUserId(String userId);

    public User save(User user);

}
