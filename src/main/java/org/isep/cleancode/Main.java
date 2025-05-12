package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.persistence.TodoRepository;
import org.isep.cleancode.presentation.TodoController;

public class Main {
    static TodoRepository repo = new TodoRepository();
    private static final TodoController todoController = new TodoController(repo);

    public static void main(String[] args) {
        port(4567);

        get("/todos", todoController::getAllTodos);

        post("/todos", todoController::createTodo);
    }
}

