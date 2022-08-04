package com.example.demo.core.ports.in;

import java.util.NoSuchElementException;

public interface DeleteTodoUseCase {
    void deleteTodo(long id) throws NoSuchElementException;
}
