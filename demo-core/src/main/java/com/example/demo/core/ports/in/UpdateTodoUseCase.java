package com.example.demo.core.ports.in;

import com.example.demo.core.models.Todo;

import java.util.Map;

public interface UpdateTodoUseCase {
    Map.Entry<Todo, Boolean> updateTodo(long id, Boolean done, String description);
}
