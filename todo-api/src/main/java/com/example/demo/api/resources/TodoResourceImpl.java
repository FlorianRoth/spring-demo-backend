package com.example.demo.api.resources;

import com.example.demo.api.dto.TodoDto;
import com.example.demo.api.mapper.DemoDtoMapper;
import com.example.demo.core.ports.in.CreateTodoUseCase;
import com.example.demo.core.ports.in.DeleteTodoUseCase;
import com.example.demo.core.ports.in.GetTodoUseCase;
import com.example.demo.core.ports.in.UpdateTodoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class TodoResourceImpl implements TodoResource {

    private final DemoDtoMapper demoMapper;
    private final CreateTodoUseCase createTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;
    private final GetTodoUseCase getTodoUseCase;

    @Override
    public ResponseEntity<TodoDto> get(long id) {
        return this.getTodoUseCase.getTodo(id)
                .map(demoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<TodoDto>> getAll() {
        var demos = this.getTodoUseCase.getTodos();

        var result = demos
                .stream()
                .map(demoMapper::toDto)
                .collect(Collectors.toList());

        return ok(result);
    }

    public ResponseEntity<TodoDto> post(TodoDto body) {
        var demo = this.createTodoUseCase.createTodo(body.getDone(), body.getDescription());

        var result = demoMapper.toDto(demo);

        return created(result);
    }

    public ResponseEntity<TodoDto> put(TodoDto body) {
        var updateResult = this.updateTodoUseCase.updateTodo(
                body.getId(),
                body.getDone(),
                body.getDescription());

        var result = demoMapper.toDto(updateResult.getKey());

        return updateResult.getValue()
                ? created(result)
                : ok(result);
    }

    public ResponseEntity<Void> delete(long id) {
        var deleted = this.deleteTodoUseCase.deleteTodo(id);

        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private ResponseEntity<TodoDto> created(TodoDto todo) {
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(todo.getId())
                .toUri();

        return ResponseEntity.created(uri).body(todo);
    }
}
