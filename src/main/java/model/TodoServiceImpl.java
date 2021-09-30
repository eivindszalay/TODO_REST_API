package model;

import java.util.Collection;
import java.util.HashMap;

public class TodoServiceImpl implements TodoService {

    HashMap<String, Todo> todos = new HashMap<>();

    public void addTodo(Todo todo) {
        todos.put(todo.getUrlId(), todo);
        System.out.println(todo.toString());
    }

    public Collection<Todo> getTodos() {
        return todos.values();
    }

    public Todo getTodo(String id) {
        return todos.get(id);
    }

    public Todo editTodo(Todo todo) throws Exception {
        if (todoExist(todo.getUrlId())) {
            todos.put(todo.getUrlId(), todo);
            return todo;
        } else {
            Exception e = new Exception("Todo " + todo.toString() + "does not exists.");
            throw e;
        }
    }

    public void deleteTodo(String id) {
        todos.remove(id);
    }

    public boolean todoExist(String id) {
        return todos.containsKey(id);
    }

    public boolean isEmpty() {
        return todos.isEmpty();
    }
}
