package com.nuntius.account.service;

import com.nuntius.account.client.AuthServiceClient;
import com.nuntius.account.domain.Account;
import com.nuntius.account.domain.User;
import com.nuntius.account.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by sergiojimenezfemenia on 11/7/17.
 */
public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AuthServiceClient authServiceClient;

    @Mock
    private AccountRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldFindByName() {

        final Account account = new Account();
        account.setName("test");

        when(accountService.findByName(account.getName())).thenReturn(account);
        Account found = accountService.findByName(account.getName());

        assertEquals(account, found);
    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsEmpty(){

        accountService.findByName("");

    }

    @Test
    public void shouldCreateAccountWithGivenUser() {

        User user = new User();
        user.setUsername("test");

        Account account = accountService.create(user);

        assertEquals(user.getUsername(), account.getName());
        assertEquals("", account.getIcon());
        assertEquals("", account.getMail());
        assertNotNull(account.getLastSeen());

        verify(authServiceClient, times(1)).createUser(user);
        verify(repository, times(1)).save(account);

    }

    @Test
    public void shouldSaveChangesWhenUpdatedAccountGiven() {

        Account update = new Account();
        update.setName("test");
        update.setIcon("icon");
        update.setLastSeen(new Date());

        final Account account = new Account();

        when(accountService.findByName("test")).thenReturn(account);
        accountService.saveChanges("test", update);

        assertEquals(update.getIcon(), account.getIcon());
        assertNotNull(account.getLastSeen());

        verify(repository, times(1)).save(account);


    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNoAccountsExistedWithGivenName() {

        final Account update = new Account();
        update.setIcon(new String());
        update.setMail(new String());

        when(accountService.findByName("test")).thenReturn(null);
        accountService.saveChanges("test", update);

    }
}
