package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.persistence.csvfiles.TodoCsvFilesRepository;
import org.isep.cleancode.presentation.TodoController;

public class Main {
    static TodoCsvFilesRepository repo = new TodoCsvFilesRepository();
    private static final TodoController todoController = new TodoController(repo);

    public static void main(String[] args) {
        port(4567);

        get("/todos", todoController::getAllTodos);

        post("/todos", todoController::createTodo);
    }
}

