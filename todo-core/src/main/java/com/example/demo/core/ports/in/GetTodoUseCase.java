package com.example.demo.core.ports.in;

import com.example.demo.core.models.Todo;

import java.util.Collection;
import java.util.Optional;

public interface GetTodoUseCase {

    Optional<Todo> getTodo(long id);

    Collection<Todo> getTodos();
}
