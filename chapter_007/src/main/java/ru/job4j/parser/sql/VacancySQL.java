package ru.job4j.parser.sql;

import ru.job4j.parser.Vacancy;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VacancySQL implements AutoCloseable {
    private Connection connection;

    public VacancySQL(Connection connection) {
        this.connection = connection;
    }

    public void add(List<Vacancy> list) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("insert into vacancy(name, text, link, date) values (?, ?, ?, ?) on conflict do nothing")) {
            for (Vacancy vacancy : list) {
                preparedStatement.setString(1, vacancy.getName());
                preparedStatement.setString(2, vacancy.getDescription());
                preparedStatement.setString(3, vacancy.getLink());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(vacancy.getDateTime()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        }
    }

    public List<Vacancy> findByName(String name) {
        List<Vacancy> vacancies = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection
                .prepareStatement("select * from vacancy where name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                vacancies.add(
                        new Vacancy(
                                result.getString("name"),
                                result.getString("text"),
                                result.getString("link"),
                                result.getTimestamp("date").toLocalDateTime()
                        ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    public LocalDateTime searchLastDateOnDB() {
        LocalDateTime result = null;
        try (PreparedStatement statement = connection.prepareStatement("select max(date) from vacancy;")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Timestamp value = resultSet.getTimestamp(1);
                result = value != null ? value.toLocalDateTime() : null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
