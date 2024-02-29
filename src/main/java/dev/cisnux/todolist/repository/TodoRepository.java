package dev.cisnux.todolist.repository;


import dev.cisnux.todolist.entity.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> getTodos();
    Todo addNewTodo(Todo todo);
    boolean editTodo(int id, String activity);
    boolean removeTodo(int id);
}