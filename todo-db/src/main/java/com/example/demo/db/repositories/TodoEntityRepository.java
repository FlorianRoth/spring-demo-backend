package com.example.demo.db.repositories;

import com.example.demo.db.entities.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoEntityRepository extends CrudRepository<TodoEntity, Long> {

    long deleteById(long id);
}
