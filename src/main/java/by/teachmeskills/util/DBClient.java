package by.teachmeskills.util;

import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.function.Function;

@Log4j2
public class DBClient {

    public static final String DB_URL = "jdbc:mysql://localhost:3308/teachmeskills";
    public static final String USER = "root";
    public static final String PASSWORD = "12345";

    public <T> T executeQuery(String query, Function<ResultSet, T> mapping) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return mapping.apply(resultSet);
        } catch (SQLException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
}

