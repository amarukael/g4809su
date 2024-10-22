package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariDataSource;

import utility.db.DBConfigDev;
import utility.db.DBConfigStg;

public class LoadDataSource implements AutoCloseable {
    private HikariDataSource hikariDataSource = null;
    private Session session = null;
    private Connection con = null;
    private PreparedStatement stmt = null;
    private PreparedStatement stmt2 = null;
    private ResultSet rs = null;
    private int rowId = 1;

    public LoadDataSource(String environmentName, String environmentSvr) {
        String jdbcUrl = "";
        String dbUser = "";
        String dbPassword = "";

        if (environmentSvr.equals("DEV")) {
            DBConfigDev.DatabaseEnvironmentDEV environment = DBConfigDev.getEnvironmentByName(environmentName);
            DBConfigDev.DatabaseConfigDev config = new DBConfigDev.DatabaseConfigDev(environment);

            jdbcUrl = config.JDBC_URL;
            dbUser = config.DB_USERNAME;
            dbPassword = config.DB_PASSWORD;

            if (environmentName.equals("DIMS")) {
                if (hikariDataSource == null) {
                    hikariDataSource = new HikariDataSource();
                    hikariDataSource.setJdbcUrl(config.JDBC_URL);
                    hikariDataSource.setUsername(config.DB_USERNAME);
                    hikariDataSource.setPassword(config.DB_PASSWORD);
                    hikariDataSource.setPoolName(environmentName + "QA");
                    hikariDataSource.setIdleTimeout(30000);
                    hikariDataSource.setMaximumPoolSize(60);
                    hikariDataSource.setMaxLifetime(30000);
                    hikariDataSource.setMinimumIdle(1);
                    hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                }

                if (con == null) {
                    try {
                        con = hikariDataSource.getConnection();
                    } catch (SQLException e) {
                        System.out.println("ERROR: " + e);
                    }
                }
            }
        } else {
            DBConfigStg.DatabaseEnvironmentSTG environment = DBConfigStg.getEnvironmentByName(environmentName);
            DBConfigStg.DatabaseConfigStg config = new DBConfigStg.DatabaseConfigStg(environment);

            jdbcUrl = config.JDBC_URL;
            dbUser = config.DB_USERNAME;
            dbPassword = config.DB_PASSWORD;
        }

        if (con == null) {
            try {
                con = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            } catch (SQLException e) {
                System.out.println("ERROR: " + e);
            }
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void query(String sql, boolean insertupdatedId) throws SQLException {
        if (insertupdatedId)
            stmt = (PreparedStatement) con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        else
            stmt = (PreparedStatement) con.prepareStatement(sql);
    }

    public void statement(Object... s) throws SQLException {
        for (int i = 1; i <= s.length; i++) {
            stmt.setObject(i, s[i - 1]);
        }
    }

    public void appendStatement(Object s) throws SQLException {
        stmt.setObject(rowId, s);
        rowId++;
    }

    public void timeout(int timeout) throws SQLException {
        stmt.setQueryTimeout(timeout);
    }

    public ResultSet execute() throws SQLException {
        return rs = stmt.executeQuery();
    }

    public void query2(String sql, boolean insertupdatedId) throws SQLException {
        if (insertupdatedId)
            stmt2 = (PreparedStatement) con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        else
            stmt2 = (PreparedStatement) con.prepareStatement(sql);
    }

    public void statement2(Object... s) throws SQLException {
        for (int i = 1; i <= s.length; i++) {
            stmt2.setObject(i, s[i - 1]);
        }
    }

    public void appendStatement2(Object s) throws SQLException {
        stmt2.setObject(rowId, s);
        rowId++;
    }

    public void timeout2(int timeout) throws SQLException {
        stmt2.setQueryTimeout(timeout);
    }

    public ResultSet execute2() throws SQLException {
        return rs = stmt2.executeQuery();
    }

    public void autoCommit(boolean status) throws SQLException {
        con.setAutoCommit(status);
    }

    public void Commit() throws SQLException {
        con.commit();
    }

    public void Rollback() throws SQLException {
        con.rollback();
    }

    public int insertupdate() throws SQLException {
        return stmt.executeUpdate();
    }

    public int insertupdate2() throws SQLException {
        return stmt2.executeUpdate();
    }

    public void reset() {
        stmt = null;
        rowId = 1;
    }

    public void reset2() {
        stmt2 = null;
        rowId = 1;
    }

    public ResultSet executeGetId() throws SQLException {
        stmt.execute();
        return rs = stmt.getGeneratedKeys();
    }

    @Override
    public void close() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (stmt2 != null) {
            stmt2.close();
        }
        if (con != null) {
            con.close();
        }
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        if (hikariDataSource != null) {
            hikariDataSource.close();
        }
    }
}
