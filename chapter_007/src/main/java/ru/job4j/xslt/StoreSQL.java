package ru.job4j.xslt;

import ru.job4j.xslt.entries.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * connect to database
     */
    public void init() {
        try {
            connect = DriverManager.getConnection(config.get("url"));
            Statement statement = connect.createStatement();
            statement.execute("create table if not exists entry (field integer)");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        if (Objects.isNull(connect)) {
            init();
        }
        return this.connect;
    }
    /**
     Generation of values​and their recording in the database
     */
    public void generate(int size) {
        String values = "";
        for (int i = 1; i <= size; i++) {
           values = i != size ? values + "(" + i + ")," : values + "(" + i + ")";
        }
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("delete from entry");
            statement.executeUpdate("insert into entry values " + values);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * saving the contents of the database in list
     * @return
     */
    public List<Entry> load() {
        List<Entry> result = new ArrayList<>();
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select field from entry");
            while (resultSet.next()) {
                result.add(new Entry().setField(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public void close() throws Exception {
        if (Objects.nonNull(connect)) {
            connect.close();
        }
    }
}