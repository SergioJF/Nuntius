package com.nuntius.account.repository;

import com.nuntius.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sergiojimenezfemenia on 9/7/17.
 */
@Repository
public interface AccountRepository  extends CrudRepository<Account, String> {
    Account findByName(String name);
}
