package org.isep.cleancode;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class TodoController {

    // this Todo class should be completed to achieve Step 1

    private static final Gson gson = new Gson();
    private final List<Todo> todos = new ArrayList<>();

    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");

        return gson.toJson(todos);
    }

    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);

        // check if name is valid
        if (!newTodo.isValid()) {
            res.status(400);
            return "Invalid name: must be non-null and < 64 characters.";
        }

        // check if name is unique
        boolean exists = todos.stream().anyMatch(t -> t.getName().equals(newTodo.getName()));
        if (exists) {
            res.status(400);
            return "Name already exists.";
        }

        // check if dueDate is valid
        if (!newTodo.isDueDateValid()) {
            res.status(400);
            return "Invalid due date format. Expected format: YYYY-MM-DD.";
        }

        todos.add(newTodo);
        res.status(201);
        res.type("application/json");

        return gson.toJson(newTodo);
    }
}
