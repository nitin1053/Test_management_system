package com.testcasemanagement.controller;
import com.testcasemanagement.model.TestCase;
import com.testcasemanagement.service.TestCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
@Tag(name = "Test Case Management", description = "API for managing test cases")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @Operation(summary = "Get all test cases", description = "Retrieve a list of all test cases")
    @GetMapping
    public List<TestCase> getAllTestCases() {
        return testCaseService.getAllTestCases();
    }

    @Operation(summary = "Get test case by ID", description = "Retrieve a single test case by its ID")
    @GetMapping("/{id}")
    public TestCase getTestCaseById(@PathVariable String id) {
        return testCaseService.getTestCaseById(id);
    }

    @Operation(summary = "Create a new test case", description = "Create a new test case with required fields")
    @PostMapping
    public TestCase createTestCase(@RequestBody TestCase testCase) {
        return testCaseService.createTestCase(testCase);
    }

    @Operation(summary = "Update a test case", description = "Update an existing test case by ID")
    @PutMapping("/{id}")
    public TestCase updateTestCase(@PathVariable String id, @RequestBody TestCase testCase) {
        return testCaseService.updateTestCase(id, testCase);
    }

    @Operation(summary = "Delete a test case", description = "Delete a test case by ID")
    @DeleteMapping("/{id}")
    public String deleteTestCase(@PathVariable String id) {
        testCaseService.deleteTestCase(id);
        return "Test case deleted successfully!";
    }
}