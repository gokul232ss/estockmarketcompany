package com.fse.estockmarketcompany.repository;

import com.fse.estockmarketcompany.model.CompanyAll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyAllRepository extends MongoRepository<CompanyAll, Integer> {
}
