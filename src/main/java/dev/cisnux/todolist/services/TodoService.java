package dev.cisnux.todolist.services;

public interface TodoService {
    void showTodos();
    void addNewTodo(String activity);
    boolean editTodo(int id, String activity);
    boolean removeTodo(int id);
}
