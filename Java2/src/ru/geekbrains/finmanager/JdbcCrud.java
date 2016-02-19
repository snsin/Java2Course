package ru.geekbrains.finmanager;

import java.sql.Connection;

public interface JdbcCrud {
    boolean create(Connection conn, int id);
    boolean read(Connection conn, int id);
    boolean update(Connection conn, int id);
    boolean delete(Connection conn, int id);

}
