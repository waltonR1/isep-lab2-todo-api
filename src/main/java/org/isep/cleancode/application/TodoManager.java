package org.isep.cleancode.application;

import org.isep.cleancode.Todo;
import java.util.List;

public class TodoManager {
    private final ITodoRepository repository;

    public TodoManager(ITodoRepository repository) {
        this.repository = repository;
    }

    public String addTodo(Todo todo) {
        if (!todo.isValid()) {
            return "Invalid name: required and < 64 characters";
        }

        if (!todo.isDueDateValid()) {
            return "Invalid due date format. Expected format: YYYY-MM-DD";
        }

        if (repository.existsByName(todo.getName())) {
            return "Todo with the same name already exists";
        }

        repository.add(todo);
        return null;
    }

    public List<Todo> getAllTodos() {
        return repository.getAll();
    }
}
