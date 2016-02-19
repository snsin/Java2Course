package ru.geekbrains.finmanager;

import java.sql.Connection;

public interface JdbcCrud<T> {
    T create(Connection conn, int id);
    T read(Connection conn, int id);
    T update(Connection conn, int id);
    T delete(Connection conn, int id);

}
