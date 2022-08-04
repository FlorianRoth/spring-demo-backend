package com.example.demo.core.ports.in;

import com.example.demo.core.models.Todo;

import java.util.Collection;

public interface ListTodosUseCase {
    Collection<Todo> getTodos();
}
