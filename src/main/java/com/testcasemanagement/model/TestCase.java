package com.testcasemanagement.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "test_cases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestCase {

    @Id
    private String id;

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private Date createdAt;

    private Date updatedAt;
}