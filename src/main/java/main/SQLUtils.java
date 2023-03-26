package main;

import java.sql.*;

public class SQLUtils {

    private Statement stmt;
    private Connection connection;
    private int rs;

    public Connection connectToSQL() throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

        stmt = connection.createStatement();

        return connection;
    }

    public int sendToSQL(String query) throws SQLException {
        rs = stmt.executeUpdate(query);
        return rs;
    }

    public void closeSQL() throws SQLException {
        stmt.close();
        connection.close();
    }

}
