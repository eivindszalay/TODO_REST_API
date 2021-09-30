package model;

import com.google.gson.Gson;

import static spark.Spark.*;

public class App {

    public static final String BASE_URL = "/todos";
    public static TodoService todoService = new TodoServiceImpl();

    public static void main(String[] args) {

        port(8080);


        // post a new todo
        post(BASE_URL, (req, res) -> {
            res.type("application/json");
            Todo todo = new Gson().fromJson(req.body(), Todo.class);
            todoService.addTodo(todo);

            return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        // get all todos
        get(BASE_URL, (req, res) -> {
            res.type("application/json");
            if (todoService.isEmpty()) {
                return "There is nothing Todo! Post a new Todo if you have anything!";
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson().
                                toJsonTree(todoService.getTodos())));
            }
        });


        // get todo by id
        get(BASE_URL + "/:id", (req, res) -> {
            res.type("application/json");
            if (todoService.todoExist(req.params(":id"))) {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                .toJsonTree(todoService.getTodo(req.params(":id")))));
            } else return new Gson().toJson(
                    new StandardResponse(StatusResponse.ERROR,
                            "Todo with id=" + req.params(":id") + " does not exists."));
        });


        // update a todo
        put(BASE_URL + "/:id", (req, res) -> {
            if (todoService.todoExist(req.params(":id"))) {
                res.type("application/json");
                Todo toEdit = new Gson().fromJson(req.body(), Todo.class);
                Todo editedTodo = todoService.editTodo(toEdit);
                if (editedTodo != null) {
                    return new Gson().toJson(
                            new StandardResponse(StatusResponse.SUCCESS, new Gson()
                                    .toJsonTree(editedTodo)));
                } else {
                    return new Gson().toJson(
                            new StandardResponse(StatusResponse.ERROR, new Gson()
                                    .toJson("Todo not found or error in edit")));
                }
            } else {
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.ERROR,
                                "Todo with id=" + req.params(":id") + " does not exists."));
            }
        });

        // delete a todo
        delete(BASE_URL + "/:id", (req, res) -> {
            if (todoService.todoExist(req.params(":id"))) {
                res.type("application/json");
                todoService.deleteTodo(req.params(":id"));
                return new Gson().toJson(
                        new StandardResponse(StatusResponse.SUCCESS, "Todo deleted"));
            } else return new Gson().toJson(
                    new StandardResponse(StatusResponse.ERROR,
                            "Todo with id=" + req.params(":id") + " does not exists."));

        });
    }
}
