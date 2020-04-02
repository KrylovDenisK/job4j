package ru.job4j.parser.sql;

import ru.job4j.parser.Vacancy;
import ru.job4j.tracker.sql.TrackerSQL;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DataSource {
    private Connection connection;

    private boolean init() {
        try (InputStream in = Vacancy.class.getClassLoader().getResourceAsStream("vacancy.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement st = connection.createStatement()) {
                st.execute("create table if not exists vacancy (id serial, name varchar(2000), text varchar(5000), link varchar(2000), date TIMESTAMP, Primary Key(id));");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection != null;
    }

    public Connection getConnection() {
        if (connection == null) {
            init();
        }
        return connection;
    }
}
