package main;

import java.sql.*;

public class SQLUtils {

    private Statement stmt;
    private Connection connection;
    private ResultSet rs;

    public Connection connectToSQL() throws SQLException {

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");

        stmt = connection.createStatement();

        return connection;
    }

    public ResultSet sendToSQL(String query) throws SQLException {
        rs = stmt.executeQuery(query);
        return rs;
    }

    public void closeSQL() throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
    }

}
