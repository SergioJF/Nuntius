package com.nuntius.auth.service;

import com.nuntius.auth.domain.User;
import com.nuntius.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by sergiojimenezfemenia on 9/7/17.
 */
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");

        userService.create(user);
        verify(repository, times(1)).save(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUserAlreadyExist() {
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");

        when(repository.findOne(user.getUsername())).thenReturn(new User());
        userService.create(user);
    }
}
