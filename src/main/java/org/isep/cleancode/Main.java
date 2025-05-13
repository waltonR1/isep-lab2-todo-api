package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.application.ITodoRepository;
import org.isep.cleancode.persistence.csvfiles.TodoCsvFilesRepository;
import org.isep.cleancode.persistence.inmemory.TodoInMemoryRepository;
import org.isep.cleancode.presentation.TodoController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        port(4567);

        String repoType = "INMEMORY";

        try (FileInputStream fis = new FileInputStream("config.properties")) {
            Properties props = new Properties();
            props.load(fis);
            String configValue = props.getProperty("repo");
            if (configValue != null) {
                repoType = configValue.toUpperCase();
            }
        } catch (IOException e) {
            System.out.println("Can't read config.properties, using default configuration INMEMORY");
        }

        ITodoRepository repo;
        switch (repoType) {
            case "CSV":
                System.out.println("Using CSV file repository.");
                repo = new TodoCsvFilesRepository();
                break;
            case "INMEMORY":
            default:
                System.out.println("Using in-memory repository.");
                repo = new TodoInMemoryRepository();
                break;
        }

        TodoController todoController = new TodoController(repo);

        get("/todos", todoController::getAllTodos);

        post("/todos", todoController::createTodo);
    }
}

