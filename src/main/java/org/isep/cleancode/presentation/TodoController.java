package org.isep.cleancode.presentation;

import com.google.gson.Gson;
import org.isep.cleancode.Todo;
import org.isep.cleancode.application.ITodoRepository;
import org.isep.cleancode.application.TodoManager;
import spark.Request;
import spark.Response;

public class TodoController {

    // this Todo class should be completed to achieve Step 1

    private static final Gson gson = new Gson();

    private final TodoManager todoManager;

    public TodoController(ITodoRepository repo) {
        this.todoManager = new TodoManager(repo);
    }

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(todoManager.getAllTodos());
    }

    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);

        String error = todoManager.addTodo(newTodo);
        if (error != null) {
            res.status(400);
            return error;
        }

        res.status(201);
        res.type("application/json");
        return gson.toJson(newTodo);
    }
}
