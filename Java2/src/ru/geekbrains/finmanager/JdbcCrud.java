package ru.geekbrains.finmanager;

public interface JdbcCrud {
    boolean create();
    boolean read();
    boolean update();
    boolean delete();

}
