package com.testcasemanagement.repository;

import com.testcasemanagement.model.TestCase;
import com.testcasemanagement.model.Status;
import com.testcasemanagement.model.Priority;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository extends MongoRepository<TestCase, String> {
    List<TestCase> findByStatusAndPriority(Status status, Priority priority);
}