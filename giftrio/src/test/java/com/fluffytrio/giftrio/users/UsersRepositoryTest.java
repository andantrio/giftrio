package com.fluffytrio.giftrio.users;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanup() {
        usersRepository.deleteAll();
    }

    @Test
    public void createUser() {
        //given
        String userId = "user01";
        String userName = "nickname";
        String password = "password";

        usersRepository.save(Users.builder().userId(userId).userName(userName).password(password).build());

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users01 = usersList.get(0);
        assertThat(users01.getUserId()).isEqualTo(userId);
        assertThat(users01.getUserName()).isEqualTo(userName);
        assertThat(users01.getPassword()).isEqualTo(password);
    }
}
