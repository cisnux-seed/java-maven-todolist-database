package dev.cisnux.todolist.services;


import dev.cisnux.todolist.entity.Todo;
import dev.cisnux.todolist.repository.TodoRepository;

public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void showTodos() {
        final var todos = todoRepository.getTodos();
        if (!todos.isEmpty()) System.out.println("Todo List:");
        for (final var todo : todos) {
            System.out.println(todo.id() + ". " + todo.activity());
        }
    }

    @Override
    public void addNewTodo(String activity) {
        final var newTodo = new Todo(activity);
        todoRepository.addNewTodo(newTodo);
    }

    @Override
    public boolean editTodo(int id, String activity) {
        return todoRepository.editTodo(id, activity);
    }

    @Override
    public boolean removeTodo(int id) {
        return todoRepository.removeTodo(id);
    }
}
