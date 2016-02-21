package ru.geekbrains.finmanager;

import java.sql.Connection;

public interface DbCrud {
	boolean create(Connection conn);

	boolean read(Connection conn);

	boolean update(Connection conn);

	boolean delete(Connection conn);

}
