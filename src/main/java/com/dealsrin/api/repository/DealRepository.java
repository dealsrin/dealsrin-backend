package com.dealsrin.api.repository;

import com.dealsrin.api.model.Deal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends MongoRepository<Deal, String> {
    // Custom queries can be added here if needed
}
