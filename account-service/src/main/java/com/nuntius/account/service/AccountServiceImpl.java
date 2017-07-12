package com.nuntius.account.service;

import com.nuntius.account.client.AuthServiceClient;
import com.nuntius.account.domain.Account;
import com.nuntius.account.domain.User;
import com.nuntius.account.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by sergiojimenezfemenia on 9/7/17.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private AccountRepository repository;

    @Override
    public Account findByName(String accountName) {
        Assert.hasLength(accountName);

        return repository.findByName(accountName);
    }

    @Override
    public Account create(User user) {

        Account existing = repository.findByName(user.getUsername());
        Assert.isNull(existing, "Account already exist: " + user.getUsername());

        authServiceClient.createUser(user);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setMail("");
        account.setIcon("");
        account.setLastSeen(new Date());

        repository.save(account);

        log.info("New account has been created: " + account.getName());

        return account;
    }

    @Override
    public void saveChanges(String name, Account update) {
        Account account = repository.findByName(name);
        Assert.notNull(account, "Not exist account with name: " + name);

        account.setIcon(update.getIcon());
        account.setMail(update.getMail());
        account.setLastSeen(new Date());

        repository.save(account);

        log.debug("account {} changes has been saved", name);

    }
}
