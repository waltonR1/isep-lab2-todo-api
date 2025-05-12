package org.isep.cleancode.persistence;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository implements ITodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    @Override
    public void add(Todo todo) {
        todos.add(todo);
    }

    @Override
    public List<Todo> getAll() {
        return todos;
    }

    @Override
    public boolean existsByName(String name) {
        return todos.stream().anyMatch(t -> t.getName().equals(name));
    }
}
