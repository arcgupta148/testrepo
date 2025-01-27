import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.model.User;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Banking.BankingApplication;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(classes = BankingApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByUsername() {
        // Arrange
        User user = new User("testuser1", "password123");
        userRepository.save(user);

        // Act
        Optional<User> result = userRepository.findByUsername("testuser");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
    }
}
