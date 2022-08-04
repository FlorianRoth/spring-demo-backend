package com.example.demo.core.services;

import com.example.demo.core.models.Todo;
import com.example.demo.core.ports.in.CreateTodoUseCase;
import com.example.demo.core.ports.in.DeleteTodoUseCase;
import com.example.demo.core.ports.in.GetTodoUseCase;
import com.example.demo.core.ports.in.UpdateTodoUseCase;
import com.example.demo.core.ports.out.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
class TodoServiceImpl implements CreateTodoUseCase, UpdateTodoUseCase, DeleteTodoUseCase, GetTodoUseCase {

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Boolean done, String description) {
        log.info("createTodo(done = {}, description = '{}')", done, description);

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
        log.info("updateTodo(id = {}, done = {}, description = '{}')", id, done, description);

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
    public boolean deleteTodo(long id) {
        log.info("deleteTodo(id = {})", id);
        return todoRepository.delete(id);
    }

    @Override
    public Optional<Todo> getTodo(long id) {
        log.info("updateTodo(id = {})", id);
        return todoRepository.findById(id);
    }

    @Override
    public Collection<Todo> getTodos() {
        log.info("getTodos()");
        return todoRepository.getAll();
    }
}
