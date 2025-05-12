package org.isep.cleancode.persistence;

import org.isep.cleancode.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    public void add(Todo todo) {
        todos.add(todo);
    }

    public List<Todo> getAll() {
        return todos;
    }

    public boolean existsByName(String name) {
        return todos.stream().anyMatch(t -> t.getName().equals(name));
    }
}
