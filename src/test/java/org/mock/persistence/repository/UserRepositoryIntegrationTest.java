package org.mock.persistence.repository;

import com.movieReservation.models.User;
import com.movieReservation.services.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mock.DataProvider;
import org.mockito.internal.matchers.GreaterThan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    private UserRepository userRepository;
    @Autowired
    TestEntityManager entityManager;
    private DataProvider dataProvider;
    @Test
    @DisplayName("JUnit test for save users operation")
    public void givenUserObject_whenSave_thenReturnSaveUser(){

        // When : Action of behavious that we are going to test
        User saveUser = userRepository.save(DataProvider.newUser());

        // Then : Verify the output

        Assertions.assertNotNull(saveUser);
        Assertions.assertTrue(saveUser.getUserId() >0 ,"User ID should be greater than 0");


    }
    @Test
    @DisplayName("JUnit test for delete user operation")
    public void givenUserObject_whenDelete_thenRemoveUser() {
        // Given: prepare a user to delete
        User user = entityManager.persist(DataProvider.newUser());

        // When: deleting the user
        userRepository.delete(user);
        Optional<User> deletedUser = userRepository.findById(user.getUserId());

        // Then: Verify the user is deleted
        Assertions.assertFalse(deletedUser.isPresent(), "User should be deleted");
    }

    @Test
    @DisplayName("JUnit test for findByNameAndEmail operation")
    public void givenUserNameAndEmail_whenFindByNameAndEmail_thenReturnUser() {
        // Given: prepare and save a user
        User user = entityManager.persist(DataProvider.newUser());

        // When: find the user by name and email
        User foundUser = userRepository.findByNameAndEmail(user.getName(), user.getEmail());

        // Then: Verify the user is found
        Assertions.assertNotNull(foundUser, "User should be found");
        Assertions.assertEquals(user.getName(), foundUser.getName());
        Assertions.assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    @DisplayName("JUnit test for findByEmail operation")
    public void givenEmail_whenFindByEmail_thenReturnUser() {
        // Given: prepare and save a user
        User user = entityManager.persist(DataProvider.newUser());

        // When: find the user by email
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());

        // Then: Verify the user is found
        Assertions.assertTrue(foundUser.isPresent(), "User should be found by email");
        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
    }

    @Test
    @DisplayName("JUnit test for findByNameAndEmail operation")
    public void givenNameAndEmail_whenFindByNameAndEmail_thenReturnUser() {
        // Given: prepare and save a user
        User user = entityManager.persist(DataProvider.newUser());

        // When: find the user by name and Email
        User foundUser = userRepository.findByNameAndEmail(user.getName(), user.getEmail());

        // Then: Verify the user is found by name and Email
        Assertions.assertNotNull(foundUser, "User should be found by name and email");
        Assertions.assertEquals(user.getEmail(), foundUser.getEmail());
        Assertions.assertEquals(user.getName(), foundUser.getName());
    }
}
