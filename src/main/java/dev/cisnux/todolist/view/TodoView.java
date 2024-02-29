package dev.cisnux.todolist.view;


import dev.cisnux.todolist.services.TodoService;
import dev.cisnux.todolist.utils.Utils;

public class TodoView {
    private boolean isRunning = true;
    private final TodoService todoService;

    public TodoView(TodoService todoService) {
        this.todoService = todoService;
    }

    public void MainMenu() {
        while (isRunning) {
            todoService.showTodos();
            System.out.println("Menu:");
            System.out.println("1.Add");
            System.out.println("2.Edit");
            System.out.println("3.Remove");
            System.out.println("4.Logout");
            final var selectedMenu = Utils.inputString("What's your choice? ");
            switch (selectedMenu) {
                case "1" -> addViewTodoList();
                case "2" -> editViewTodoList();
                case "3" -> removeViewTodoList();
                case "4" -> {
                    isRunning = !isRunning;
                }
                default -> {
                    System.out.println("Please select a different choice");
                }
            }
        }
        System.out.println("Good Bye! Have a nice day");
    }

    public void addViewTodoList() {
        final var newTodo = Utils.inputString("Enter your todo! (enter x to cancel): ");
        if (!newTodo.equals("x"))
            todoService.addNewTodo(newTodo);
    }

    public void editViewTodoList() {
        final var todoNumber = Utils.inputNumber("Enter a todo number: ");
        final var newTodo = Utils.inputString("Enter new todo to update! (enter x to cancel): ");
        if (newTodo.equals("x"))
            return;

        final boolean isUpdated = todoService.editTodo(todoNumber, newTodo);
        if (!isUpdated) {
            System.out.println("the todo number was not found, please enter the correct number");
            editViewTodoList();
        } else
            System.out.println("successfully updated the todo");
    }

    public void removeViewTodoList() {
        final var todoNumber = Utils.inputNumber("Enter a todo number: ");
        final var isRemoved = todoService.removeTodo(todoNumber);

        if (!isRemoved) {
            System.out.println("the todo number was not found, please enter the correct number");
            removeViewTodoList();
        } else
            System.out.println("successfully removed the todo");
    }
}
