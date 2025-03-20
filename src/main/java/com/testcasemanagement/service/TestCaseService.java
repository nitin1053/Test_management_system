package com.testcasemanagement.service;

import com.testcasemanagement.model.TestCase;
import com.testcasemanagement.model.Status;
import com.testcasemanagement.model.Priority;
import com.testcasemanagement.repository.TestCaseRepository;
import com.testcasemanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestCaseService {

    @Autowired
    private TestCaseRepository testCaseRepository;

    public List<TestCase> getAllTestCases() {
        return testCaseRepository.findAll();
    }

    public TestCase getTestCaseById(String id) {
        return testCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TestCase not found with id: " + id));
    }

    public TestCase createTestCase(TestCase testCase) {
        testCase.setCreatedAt(new Date());
        testCase.setUpdatedAt(new Date());
        return testCaseRepository.save(testCase);
    }

    public TestCase updateTestCase(String id, TestCase updatedTestCase) {
        TestCase existingTestCase = getTestCaseById(id);
        existingTestCase.setTitle(updatedTestCase.getTitle());
        existingTestCase.setDescription(updatedTestCase.getDescription());
        existingTestCase.setStatus(updatedTestCase.getStatus());
        existingTestCase.setPriority(updatedTestCase.getPriority());
        existingTestCase.setUpdatedAt(new Date());
        return testCaseRepository.save(existingTestCase);
    }

    public void deleteTestCase(String id) {
        TestCase testCase = getTestCaseById(id);
        testCaseRepository.delete(testCase);
    }
}
