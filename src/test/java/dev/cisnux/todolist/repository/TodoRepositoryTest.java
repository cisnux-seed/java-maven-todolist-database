package dev.cisnux.todolist.repository;

import dev.cisnux.todolist.entity.Todo;
import dev.cisnux.todolist.utils.ConnectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class TodoRepositoryTest extends BaseRepositoryTest {
    private TodoRepository repository;

    @BeforeAll
    void beforeAll() {
        repository = new TodoRepositoryImpl(ConnectionUtil.getDataSource());
        for (int i = 0; i < 100; i++) {
            final var dummyTodo = new Todo("watch football " + i);
            repository.addNewTodo(dummyTodo);
        }
    }

    @Test
    void testAddNewTodo() {
        // arrange
        final var dummyTodo = new Todo("watch football");
        // act
        final var actualTodo = repository.addNewTodo(dummyTodo);
        // assert
        assertTrue(actualTodo.id() != -1);
        assertNotNull(actualTodo);
        assertEquals(dummyTodo.activity(), actualTodo.activity());
    }

    @Test
    void testGetTodos() {
        final var todos = repository.getTodos();
        assertFalse(todos.isEmpty());
    }

    @Test
    void testUpdateTodoById_WhenFound() {
        // arrange
        final var dummyTodo = new Todo(3001, "updated");
        // act
        final var result = repository.editTodo(dummyTodo.id(), dummyTodo.activity());
        // assert
        assertTrue(result);
    }

    @Test
    void testUpdateTodoById_WhenNotFound() {
        // arrange
        final var dummyTodo = new Todo(1, "updated");
        // act
        final var result = repository.editTodo(dummyTodo.id(), dummyTodo.activity());
        // assert
        assertFalse(result);
    }

    @Test
    void testDeleteTodoById_WhenFound() {
        // arrange
        final var dummyId = 3000;
        // act
        final var result = repository.removeTodo(dummyId);
        // assert
        assertTrue(result);
    }

    @Test
    void testDeleteTodoById_WhenNotFound() {
        // arrange
        final var dummyId = 1;
        // act
        final var result = repository.removeTodo(dummyId);
        // assert
        assertFalse(result);
    }
}