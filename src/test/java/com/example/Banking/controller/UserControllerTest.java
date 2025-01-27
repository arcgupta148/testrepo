import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import com.example.Banking.BankingApplication;
import org.springframework.test.web.servlet.MvcResult;


@SpringBootTest(classes = BankingApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnJwtForValidLogin() throws Exception {
        // Arrange: Add a user to the database using @DataJpaTest or another setup

        // Act & Assert
       MvcResult result = mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"arcgupta148\", \"password\": \"1234\"}"))
                .andReturn(); // Convert ResultActions to MvcResult
        System.out.println("Hello "+result.getResponse().getContentAsString());
            // Perform assertions 
        mockMvc.perform(post("/api/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"arcgupta148\", \"password\": \"1234\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.jwt").isNotEmpty());
    }
    

    @Test
    void shouldReturnUnauthorizedForInvalidLogin() throws Exception {
        mockMvc.perform(post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"invaliduser\", \"password\": \"wrongpassword\"}"))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.error").value("Invalid credentials"));
    }

    @Test
    void shouldReturnListOfUsers() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/users")
                .header("Authorization", "Bearer VALID_JWT_TOKEN"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(5)); // Assuming 3 users exist
    }
}
