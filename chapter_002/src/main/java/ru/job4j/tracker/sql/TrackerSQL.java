package ru.job4j.tracker.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;


public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connect;
    private static final Logger LOGGER = LogManager.getLogger(TrackerSQL.class.getName());

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connect = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            try (Statement st = connect.createStatement()) {
                st.execute("CREATE TABLE if not exists items (id serial, name varchar(100), description varchar(100), PRIMARY KEY (id))");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return this.connect != null;
    }

    public Connection getConnection() {
        if (Objects.isNull(connect)) {
            init();
        }
        return connect;
    }

    @Override
    public void close() throws Exception {
        connect.close();
    }

    @Override
    public Item add(Item item) {
        Connection connection = getConnection();
        try (PreparedStatement st = connection.prepareStatement("insert into items (name, description) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.executeUpdate();
            try (ResultSet result = st.getGeneratedKeys()) {
                if (result.next()) {
                    item.setId(String.valueOf(result.getInt(1)));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        Connection connection = getConnection();
        boolean result = false;
        try (PreparedStatement st = connection.prepareStatement("update items set name = ?, description = ? where id = ?;")) {
            st.setString(1, item.getName());
            st.setString(2, item.getDesc());
            st.setInt(3, Integer.valueOf(id));
            if (st.executeUpdate() > 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        Connection connection = getConnection();
        int result = 0;
        try (PreparedStatement st = connection.prepareStatement("delete from items where id = ?")) {
            st.setInt(1, Integer.valueOf(id));
            result = st.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result == 1;
    }

    @Override
    public List<Item> findAll() {
        Connection connection = getConnection();
        List<Item> items = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("select id, name, description from items")) {
           ResultSet result = st.executeQuery();
           while (result.next()) {
               items.add(new Item(
                       result.getString("id"),
                       result.getString("name"),
                       result.getString("description")
                       )
               );
           }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        Connection connection = getConnection();
        List<Item> items = new ArrayList<>();
        try (PreparedStatement st = connection.prepareStatement("select * from items where name = ?")) {
            st.setString(1, key);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                items.add(new Item(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("description"))
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public Item findById(String id) {
        Connection connection = getConnection();
        Item item = null;
        try (PreparedStatement st = connection.prepareStatement("select * from items where id = ?")) {
            st.setInt(1, Integer.valueOf(id));
            ResultSet result = st.executeQuery();
            while (result.next()) {
                item = new Item(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("description")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return item;
    }
}
