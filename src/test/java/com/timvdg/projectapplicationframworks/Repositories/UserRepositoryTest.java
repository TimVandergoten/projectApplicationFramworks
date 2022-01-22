package com.timvdg.projectapplicationframworks.Repositories;

import static org.assertj.core.api.Assertions.assertThat;
import com.timvdg.projectapplicationframworks.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("tim.vandergoten@student.ehb.be");
        user.setPassword("timapp22");
        user.setFirstName("Tim");
        user.setLastName("Vandergoten");

        User savedUser = repository.save(user);

        User existedUser = testEntityManager.find(User.class,savedUser.getId());

        assertThat(existedUser.getEmail()).isEqualTo(user.getEmail());
    }
    @Test
    public void testFindUserbyEmail(){
        String email = "tim.vandergoten@student.ehb.be";
        User user = repository.findByEmail(email);

        assertThat(user).isNotNull();

    }
}