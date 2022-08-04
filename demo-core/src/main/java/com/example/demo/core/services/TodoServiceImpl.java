package com.example.demo.core.services;

import com.example.demo.core.models.Todo;
import com.example.demo.core.ports.in.CreateTodoUseCase;
import com.example.demo.core.ports.in.DeleteTodoUseCase;
import com.example.demo.core.ports.in.ListTodosUseCase;
import com.example.demo.core.ports.in.UpdateTodoUseCase;
import com.example.demo.core.ports.out.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class TodoServiceImpl implements CreateTodoUseCase, UpdateTodoUseCase, DeleteTodoUseCase, ListTodosUseCase {

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Boolean done, String description) {

        var todo = new Todo();

        if(done != null) {
            todo.setDone(done);
        }

        if(description != null) {
            todo.setDescription(description);
        }

        return todoRepository.save(todo);
    }

    @Override
    public Map.Entry<Todo, Boolean> updateTodo(long id, Boolean done, String description) {
        var todoResult = todoRepository.findById(id);

        var todo = todoResult.orElseGet(Todo::new);
        todo.setId(id);

        if(done != null) {
            todo.setDone(done);
        }

        if(description != null) {
            todo.setDescription(description);
        }

        return new AbstractMap.SimpleImmutableEntry<>(
                todoRepository.save(todo),
                todoResult.isEmpty());
    }

    @Override
    public void deleteTodo(long id) throws NoSuchElementException {
        var deleted = todoRepository.delete(id);

        if(!deleted) {
            throw new NoSuchElementException("Todo with id " + id);
        }
    }

    @Override
    public Collection<Todo> getTodos() {
        return todoRepository.getAll();
    }
}
