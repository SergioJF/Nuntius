package com.nuntius.auth.repository;

import com.nuntius.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sergiojimenezfemenia on 9/7/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, String>{
}
