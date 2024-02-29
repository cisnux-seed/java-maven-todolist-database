package dev.cisnux.todolist.repository;


import dev.cisnux.todolist.entity.Todo;
import dev.cisnux.todolist.utils.ConnectionUtil;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TodoRepositoryImpl implements TodoRepository {
    private final DataSource dataSource;

    public TodoRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Todo> getTodos() {
        final List<Todo> todos = new ArrayList<>();
        try (final var connection = dataSource.getConnection()) {
            final var sql = "SELECT * FROM todo";
            try (final var statement = connection.prepareStatement(sql)) {
                statement.executeQuery();
                try (final var resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        todos.add(new Todo(resultSet.getInt(1), resultSet.getString(2)));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    @Override
    public Todo addNewTodo(Todo todo) {
        try (final var connection = dataSource.getConnection()) {
            final var sql = "INSERT INTO todo(activity) VALUES(?)";
            try (final var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, todo.activity());
                statement.executeUpdate();
                try (final var resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next())
                        return todo.copy().setId(resultSet.getInt(1)).build();
                    return todo;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean editTodo(int id, String activity) {
        try (final var connection = dataSource.getConnection()) {
            final var sql = "UPDATE todo SET activity = ? WHERE id = ?";
            try (final var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, activity);
                statement.setInt(2, id);
                statement.executeUpdate();
                try (final var resultSet = statement.getGeneratedKeys()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeTodo(int id) {
        try (final var connection = dataSource.getConnection()) {
            final var sql = "DELETE FROM todo WHERE id = ?";
            try (final var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                try (final var resultSet = statement.getGeneratedKeys()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
