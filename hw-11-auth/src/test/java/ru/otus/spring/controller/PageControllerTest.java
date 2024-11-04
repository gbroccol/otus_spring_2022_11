package ru.otus.spring.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PageController.class)
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"})
    @Test
    public void testAuthenticatedOnAdmin() throws Exception {
        mockMvc.perform(get("/authenticated")).andExpect(status().isOk());
    }

    @Test
    public void testAdminForUserException() throws Exception {
        mockMvc.perform(get("/admin")).andExpect(status().is4xxClientError());
    }

    @WithMockUser(
            username = "user",
            authorities = {"ROLE_USER"})
    @Test
    public void testMainPageForUser() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }
}