package dev.cisnux.todolist.test.services;


import dev.cisnux.todolist.entity.Todo;
import dev.cisnux.todolist.repository.TodoRepositoryImpl;
import dev.cisnux.todolist.services.TodoServiceImpl;
import dev.cisnux.todolist.utils.ConnectionUtil;

public class TodoServiceTest {
    public static void main(String[] args) {
//        testShowTodos();
//        testAddTodo();
//        testRemoveTodo();
        testEditTodo();
    }

    private static void testEditTodo() {
        final var todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        final var todoService = new TodoServiceImpl(todoRepository);
        for (int i = 0; i < 10; i++) {
            todoService.addNewTodo(String.format("test %d", i + 1));
        }
        todoService.showTodos();
        todoService.editTodo(7, "Hi 💪");
        todoService.editTodo(12, "Hi 💪");
        todoService.showTodos();
    }

    private static void testRemoveTodo() {
        final var todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        final var todoService = new TodoServiceImpl(todoRepository);
        for (int i = 0; i < 10; i++) {
            todoService.addNewTodo(String.format("test %d", i + 1));
        }
        todoService.showTodos();
        todoService.removeTodo(2);
        todoService.removeTodo(12);
        todoService.removeTodo(7);
        todoService.showTodos();
    }

    private static void testAddTodo() {
        final var todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        final var todoService = new TodoServiceImpl(todoRepository);
        for (int i = 0; i < 10; i++) {
            todoService.addNewTodo(String.format("test %d", i + 1));
        }
        todoService.showTodos();
    }

    private static void testShowTodos() {
        final var todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        final var todoService = new TodoServiceImpl(todoRepository);
        for (int i = 0; i < 10; i++) {
            todoRepository.addNewTodo(new Todo(String.format("test %d", i + 1)));
        }
        todoService.showTodos();
    }
}
