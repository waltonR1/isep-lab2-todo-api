package org.isep.cleancode.persistence.csvfiles;

import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class TodoCsvFilesRepository implements ITodoRepository {
    private final Path filePath;

    public TodoCsvFilesRepository() {
        String baseDir = System.getenv("APPDATA");
        if (baseDir == null) {
            baseDir = System.getProperty("user.home");
        }
        this.filePath = Paths.get(baseDir, "todo-list.csv");

        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Todo todo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile(), true))) {
            writer.write(todo.getName() + "," + (todo.getDueDate() != null ? todo.getDueDate() : "") + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String name = parts[0];
                String dueDate = parts.length > 1 ? parts[1] : null;
                todos.add(new Todo(name, dueDate));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todos;
    }

    @Override
    public boolean existsByName(String name) {
        return getAll().stream().anyMatch(t -> t.getName().equals(name));
    }
}
