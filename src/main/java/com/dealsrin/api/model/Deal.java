package com.dealsrin.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "deals")
public class Deal {

    @Id
    private String id;

    private String title;
    private String description;
    private String url;
    private double price;
    private String category;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}