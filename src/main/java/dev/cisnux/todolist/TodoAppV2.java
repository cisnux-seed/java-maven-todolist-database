package dev.cisnux.todolist;


import dev.cisnux.todolist.repository.TodoRepositoryImpl;
import dev.cisnux.todolist.services.TodoServiceImpl;
import dev.cisnux.todolist.utils.ConnectionUtil;
import dev.cisnux.todolist.view.TodoView;

public class TodoAppV2 {
    public static void main(String[] args) {
        final var todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        final var todoService = new TodoServiceImpl(todoRepository);
        final var todoView = new TodoView(todoService);
        todoView.MainMenu();
    }
}
