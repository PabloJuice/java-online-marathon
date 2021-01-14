//package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MyUtils {
    private Connection connection;
    private Statement statement;
    private String schemaName;

    public Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "pass");
        return connection;
    }
    public void closeConnection() throws SQLException {
        connection.close();
    }
    public Statement createStatement() throws SQLException {
        connection.setAutoCommit(true);
        this.statement = connection.createStatement();
        return statement;
    }
    public void closeStatement() throws SQLException {
        statement.close();
    }
    public void createSchema(String schemaName) throws SQLException {
        this.schemaName = schemaName;
        statement.execute("CREATE SCHEMA " + this.schemaName+ ";");
    }
    public void dropSchema() throws SQLException {
        statement.execute("DROP SCHEMA " + this.schemaName+ ";");
    }
    public void useSchema() throws SQLException {
        statement.execute("USE " + this.schemaName + ";");
    }
    public void createTableRoles() throws SQLException {
        statement.execute("CREATE TABLE Roles ("
                +" id INT NOT NULL AUTO_INCREMENT,"
                + " roleName VARCHAR(20) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE INDEX id_ROLES (`id` ASC));");
    }
    public void createTableDirections() throws SQLException {
        statement.execute("CREATE TABLE Directions ("
                +" id INT NOT NULL AUTO_INCREMENT,"
                + " directionName VARCHAR(20) NOT NULL,"
                + " PRIMARY KEY (`id`),"
                + " UNIQUE INDEX id_ROLES (`id` ASC));");
    }
    public void createTableProjects() throws SQLException {
        // code
    }
    public void createTableEmployee() throws SQLException {
        // code
    }
    public void dropTable(String tableName) throws SQLException {
        // code
    }
    public void insertTableRoles(String roleName) throws SQLException {
        // code
    }
    public void insertTableDirections(String directionName) throws SQLException {
        // code
    }
    public void insertTableProjects(String projectName, String directionName) throws SQLException {
        int directionId = getDirectionId(directionName);
        statement.execute("INSERT INTO Projects (projectName, directionId) VALUES ('"
                + projectName + "', " + directionId + ");");
    }
    public void insertTableEmployee(String firstName, String roleName, String projectName) throws SQLException {
        // code
    }
    public int getRoleId(String roleName) throws SQLException {
        ResultSet resultSet = statement
                .executeQuery("SELECT id FROM Roles WHERE roleName='" + roleName + "';");
        int result =-1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        resultSet.close();
        return result;
    }
    public int getDirectionId(String directionName) throws SQLException {
        ResultSet resultSet = statement
                .executeQuery("SELECT id FROM Directions WHERE directionName='" + directionName + "';");
        int result =-1;
        if (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        resultSet.close();
        return result;
    }
    public int getProjectId(String projectName) throws SQLException {
        return 0;
    }
    public int getEmployeeId(String firstName) throws SQLException {
        return 0;
    }
    public List<String> getAllRoles() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllDirestion() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllProjects() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllEmployee() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllDevelopers() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllJavaProjects() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }
    public List<String> getAllJavaDevelopers() throws SQLException {
        List<String> result = new ArrayList<>();
        return result;
    }

}
