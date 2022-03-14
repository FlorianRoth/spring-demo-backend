package com.example.demo.db.repositories;

import com.example.demo.db.entities.DemoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoEntityRepository extends CrudRepository<DemoEntity, Long> {
}
