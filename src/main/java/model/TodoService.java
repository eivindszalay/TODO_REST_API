package model;

import java.util.Collection;

public interface TodoService {

    public void addTodo(Todo todo);

    public Collection<Todo> getTodos();

    public Todo getTodo(String id);

    public Todo editTodo(Todo todo)
            throws Exception;

    public void deleteTodo(String id);

    public boolean todoExist(String id);

    public boolean isEmpty();
}
