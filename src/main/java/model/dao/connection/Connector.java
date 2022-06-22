package model.dao.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static volatile Connector instance;

    private final HikariDataSource ds;

    private Connector() {
//        String userName = System.getProperty(USERNAME);
//        String password = System.getProperty(PASSWORD);

        //configuring db connection and pooling with recommended params
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("Hanakotop23");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void shutdown() {
        ds.close();
    }

    public static Connector getInstance() {
        Connector localInstance = instance;
        if (localInstance == null) {
            synchronized (Connector.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new Connector();
            }
        }
        return localInstance;
    }
//    private static Connector pool;
//    private final Connection connection;
//
//    public Connector() throws NamingException {
//       try {
//          this.connection = DriverManager.getConnection(
//                   "jdbc:postgresql://localhost:5432/postgres", "postgres", "Hanakotop23");
//       } catch (SQLException e) {
//           throw new RuntimeException("Database Connection Creation Failed : " + e.getMessage());
//       }
//
//    }
//
//    public static synchronized Connector getInstance() throws NamingException {
//        if (pool == null) {
//            pool = new Connector();
//        }
//        return pool;
//    }
//
//    public Connection getConnection() throws SQLException {
//        return connection;
//    }
}
