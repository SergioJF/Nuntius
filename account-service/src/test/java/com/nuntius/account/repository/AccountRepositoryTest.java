package com.nuntius.account.repository;

import com.nuntius.account.AccountServiceApplication;
import com.nuntius.account.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by sergiojimenezfemenia on 11/7/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountServiceApplication.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void shouldFindAccountByName() {
        Account stub = getStubAccount();
        repository.save(stub);

        Account found = repository.findByName(stub.getName());
        assertEquals(stub.getLastSeen(), found.getLastSeen());
        assertEquals(stub.getIcon(), found.getIcon());
        assertEquals(stub.getMail(), found.getMail());
        assertEquals(stub.getName(), found.getName());
    }

    private Account getStubAccount() {
        Account account = new Account();
        account.setName("test");
        account.setMail("test@mail.com");
        account.setIcon("icon");
        account.setLastSeen(new Date());

        return account;
    }
}
