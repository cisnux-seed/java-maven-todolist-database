package dev.cisnux.todolist.test.view;

import dev.cisnux.todolist.repository.TodoRepository;
import dev.cisnux.todolist.repository.TodoRepositoryImpl;
import dev.cisnux.todolist.services.TodoService;
import dev.cisnux.todolist.services.TodoServiceImpl;
import dev.cisnux.todolist.utils.ConnectionUtil;
import dev.cisnux.todolist.view.TodoView;

public class TodoViewTest {
    private static final TodoRepository todoRepository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
    private static final TodoService todoService = new TodoServiceImpl(todoRepository);
    private static final TodoView todoView = new TodoView(todoService);

    public static void main(String[] args) {
//        testMainMenu();
//        testAddView();
//        testEditView();
        testRemoveView();
    }

    private static void testMainMenu() {
        todoView.MainMenu();
    }

    private static void testAddView() {
        todoView.addViewTodoList();
        todoView.MainMenu();
    }

    private static void testEditView() {
        for (int i = 0; i < 10; i++) {
            todoService.addNewTodo(String.format("test %d", i + 1));
        }
        todoView.editViewTodoList();
        todoView.MainMenu();
    }

    private static void testRemoveView() {
        for (int i = 0; i < 10; i++) {
            todoService.addNewTodo(String.format("test %d", i + 1));
        }
        todoView.removeViewTodoList();
        todoView.MainMenu();
    }
}
