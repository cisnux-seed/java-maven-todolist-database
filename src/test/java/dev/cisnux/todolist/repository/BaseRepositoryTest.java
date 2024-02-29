package dev.cisnux.todolist.repository;

import dev.cisnux.todolist.utils.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import java.sql.SQLException;

public class BaseRepositoryTest {
    @AfterAll
    static void afterAll() {
        cleanUp();
    }

    private static void cleanUp() {
        try (final var connection = ConnectionUtil.getDataSource().getConnection()) {
            final var sql = "DELETE FROM todo";
            try (final var statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
