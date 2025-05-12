package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.presentation.TodoController;

public class Main {
    private static final TodoController todoController = new TodoController();

    public static void main(String[] args) {
        port(4567);

        get("/todos", todoController::getAllTodos);

        post("/todos", todoController::createTodo);
    }
}

