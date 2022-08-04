package com.example.demo.core.ports.out;

import com.example.demo.core.models.Todo;

import java.util.Collection;
import java.util.Optional;

public interface TodoRepository {

    Optional<Todo> findById(long id);

    Collection<Todo> getAll();

    Todo save(Todo todo);

    boolean delete(long id);
}
