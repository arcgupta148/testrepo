package com.example.service;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import java.util.Optional;

import com.example.exception.InvalidCredentialsException;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.Banking.BankingApplication;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(classes = BankingApplication.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenCredentialsAreValid() {
        // Arrange
        User user = new User("testuser", "password123");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Act
        User result = userService.validateUser("testuser", "password123");

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void shouldThrowExceptionWhenCredentialsAreInvalid() {
        // Arrange
        when(userRepository.findByUsername("invaliduser")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> {
            userService.validateUser("invaliduser", "password");
        });
    }
}
