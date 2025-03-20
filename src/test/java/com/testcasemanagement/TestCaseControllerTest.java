package com.testcasemanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcasemanagement.controller.TestCaseController;
import com.testcasemanagement.model.TestCase;
import com.testcasemanagement.model.Status;
import com.testcasemanagement.model.Priority;
import com.testcasemanagement.service.TestCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TestCaseController.class)
class TestCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestCaseService testCaseService;

    private TestCase testCase1, testCase2;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        testCase1 = new TestCase("1", "Login Test", "Check login functionality", Status.PENDING, Priority.HIGH, new Date(), new Date());
        testCase2 = new TestCase("2", "Signup Test", "Check signup functionality", Status.IN_PROGRESS, Priority.MEDIUM, new Date(), new Date());
    }

    @Test
    void testGetAllTestCases() throws Exception {
        List<TestCase> testCases = Arrays.asList(testCase1, testCase2);
        Mockito.when(testCaseService.getAllTestCases()).thenReturn(testCases);

        mockMvc.perform(get("/api/testcases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    void testGetTestCaseById() throws Exception {
        Mockito.when(testCaseService.getTestCaseById("1")).thenReturn(testCase1);

        mockMvc.perform(get("/api/testcases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Login Test")));
    }

    @Test
    void testCreateTestCase() throws Exception {
        Mockito.when(testCaseService.createTestCase(Mockito.any(TestCase.class))).thenReturn(testCase1);

        mockMvc.perform(post("/api/testcases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Login Test")));
    }

    @Test
    void testUpdateTestCase() throws Exception {
        testCase1.setTitle("Updated Login Test");
        Mockito.when(testCaseService.updateTestCase(Mockito.eq("1"), Mockito.any(TestCase.class))).thenReturn(testCase1);

        mockMvc.perform(put("/api/testcases/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testCase1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Login Test")));
    }

    @Test
    void testDeleteTestCase() throws Exception {
        Mockito.doNothing().when(testCaseService).deleteTestCase("1");

        mockMvc.perform(delete("/api/testcases/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Test case deleted successfully!"));
    }
}
