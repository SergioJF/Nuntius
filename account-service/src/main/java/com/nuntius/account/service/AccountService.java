package com.nuntius.account.service;

import com.nuntius.account.domain.Account;
import com.nuntius.account.domain.User;

/**
 * Created by sergiojimenezfemenia on 9/7/17.
 */
public interface AccountService {


    /**
     * Finds account by given name
     * @param accountName
     * @return found account
     */
    Account findByName(String accountName);

    /**
     * Checks if account with the same name already exists
     * Invokes Auth Service user creation
     * Creates new account with default parameters
     * @param user
     * @return create account
     */
    Account create(User user);

    /**
     * Validates and applies incoming account updates
     * @param name
     * @param account
     */
    void saveChanges(String name, Account account);
}
