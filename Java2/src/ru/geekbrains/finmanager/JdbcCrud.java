package ru.geekbrains.finmanager;

import java.sql.Connection;
import java.util.Set;

public interface JdbcCrud<T> {
    boolean create(Connection conn, int id);
    Set<? super T> read(Connection conn, int id);
    boolean update(Connection conn);
    boolean delete(Connection conn);

}
