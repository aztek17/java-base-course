import exception.UserNotFoundException;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UserRepository;
import repositories.impl.UserRepositoryFileImpl;

import java.util.ArrayList;
import java.util.List;

public class RepositoryFileImplTests {

    @BeforeEach
    void clear() {
        User.setListIds(new ArrayList<>());
        new UserRepositoryFileImpl().deleteAll();
    }

    @Test
    void createUserPositive() {
        UserRepository repository = new UserRepositoryFileImpl();
        User testUser = new User("f5a8a211-4ac9-11yP-8a65-c424e129b9d2|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        repository.create(testUser);
        List<User> listUsers = repository.findAll();
        Assertions.assertTrue(listUsers.contains(testUser));
    }

    @Test
    void createUserNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new User("f5a8a3cb-4ac9-4b3b-8a65-f434e129b9d2|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25"));
    }

    @Test
    void searchByUserId() {
        UserRepository repository = new UserRepositoryFileImpl();
        User testUser = new User("f5a8a231-50Ra-4b3b-8a65-c424e1296Y0p|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        repository.create(testUser);
        Assertions.assertEquals(testUser, repository.findById("f5a8a231-50Ra-4b3b-8a65-c424e1296Y0p"));
    }

    @Test
    void testDeleteUser() {
        UserRepository repository = new UserRepositoryFileImpl();
        User testUser = new User("t5d8a3cb-4ac9-4y4W-8a65-c424e1296Y0p|2023-12-25T17:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        repository.create(testUser);
        repository.deleteById("t5d8a3cb-4ac9-4y4W-8a65-c424e1296Y0p");
        Assertions.assertThrows(UserNotFoundException.class, () -> repository.findById("t5d8a3cb-4ac9-4y4W-8a65-c424e1296Y0p"));
    }
}