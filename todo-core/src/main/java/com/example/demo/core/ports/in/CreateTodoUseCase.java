package com.example.demo.core.ports.in;

import com.example.demo.core.models.Todo;

public interface CreateTodoUseCase {
    Todo createTodo(Boolean done, String description);
}
