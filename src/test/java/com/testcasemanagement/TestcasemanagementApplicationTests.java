package com.testcasemanagement;

import com.testcasemanagement.model.TestCase;
import com.testcasemanagement.model.Status;
import com.testcasemanagement.model.Priority;
import com.testcasemanagement.repository.TestCaseRepository;
import com.testcasemanagement.exception.ResourceNotFoundException;
import com.testcasemanagement.service.TestCaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestCaseServiceTest {

	@Mock
	private TestCaseRepository testCaseRepository;

	@InjectMocks
	private TestCaseService testCaseService;

	private TestCase testCase1, testCase2;

	@BeforeEach
	void setUp() {
		testCase1 = new TestCase("1", "Login Test", "Check login functionality", Status.PENDING, Priority.HIGH, new Date(), new Date());
		testCase2 = new TestCase("2", "Signup Test", "Check signup functionality", Status.IN_PROGRESS, Priority.MEDIUM, new Date(), new Date());
	}

	@Test
	void testGetAllTestCases() {
		when(testCaseRepository.findAll()).thenReturn(Arrays.asList(testCase1, testCase2));

		List<TestCase> result = testCaseService.getAllTestCases();
		assertEquals(2, result.size());
		verify(testCaseRepository, times(1)).findAll();
	}

	@Test
	void testGetTestCaseById_Success() {
		when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase1));

		TestCase result = testCaseService.getTestCaseById("1");
		assertNotNull(result);
		assertEquals("Login Test", result.getTitle());
		verify(testCaseRepository, times(1)).findById("1");
	}

	@Test
	void testGetTestCaseById_NotFound() {
		when(testCaseRepository.findById("99")).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> testCaseService.getTestCaseById("99"));
	}

	@Test
	void testCreateTestCase() {
		when(testCaseRepository.save(any(TestCase.class))).thenReturn(testCase1);

		TestCase result = testCaseService.createTestCase(testCase1);
		assertNotNull(result);
		assertEquals("Login Test", result.getTitle());
		verify(testCaseRepository, times(1)).save(any(TestCase.class));
	}

	@Test
	void testUpdateTestCase() {
		when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase1));
		when(testCaseRepository.save(any(TestCase.class))).thenReturn(testCase1);

		testCase1.setStatus(Status.PASSED);
		TestCase updated = testCaseService.updateTestCase("1", testCase1);

		assertNotNull(updated);
		assertEquals(Status.PASSED, updated.getStatus());
		verify(testCaseRepository, times(1)).save(any(TestCase.class));
	}

	@Test
	void testDeleteTestCase() {
		when(testCaseRepository.findById("1")).thenReturn(Optional.of(testCase1));
		doNothing().when(testCaseRepository).delete(testCase1);

		testCaseService.deleteTestCase("1");
		verify(testCaseRepository, times(1)).delete(testCase1);
	}
}
