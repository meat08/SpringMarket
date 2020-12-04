package ru.geekbrains.springmarket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void accessOrdersWithUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void accessOrdersWithoutUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void accessCartWithUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cart"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    public void accessProfileWithUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/profiles"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void accessProductsWithoutUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
