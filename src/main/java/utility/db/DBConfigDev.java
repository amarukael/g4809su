package utility.db;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConfigDev {

    public enum DatabaseEnvironmentDEV {
        PPOBPROD("117.54.12.146", 3360, "ppobprod", "adhy", "4dhy@1Ds"),
        MULTIBILLER("10.254.76.51", 3306, "multibiller", "read_only", "qwHhCeDhNlgedvRKv2unh8HrR1vX7bY5"),
        SETORKU("10.254.76.22", 3306, "setorku", "read_only", "F1GBYrhn7pSFaeHCHdIbDXrfHVYCZmI6"),
        SOLUSIPAY("10.254.76.16", 3306, "solusipay", "read_only", "PImq3JO4bOj8LKiec4wkKRu2Ps6Pyyn3"),
        DANA("10.254.76.42", 3306, "dana", "read_only", "OE92iIyN3vd7eiA8AWByEbTKHfH1o68R"),
        OYPAYMENT("10.254.76.234", 3306, "oypayment", "read_only", "hYTq2aTTUsKSOGSjZQnYSRdlLmoQsYvO"),
        PAYMENTGATEWAY("10.254.76.45", 3306, "paymentgateway", "read_only", "7OmYQjdCwBIaBAxV2oXFcXMZx8JarQzy"),
        DISI("10.254.76.70", 3306, "disi", "read_only", "IXk5T4HBHkgRaRqAkbs6tH5x29zfysD1"),
        MnM("10.254.76.16", 3306, "ids_messaging", "read_only", "PImq3JO4bOj8LKiec4wkKRu2Ps6Pyyn3"),
        DIMS("10.254.76.19", 3306, "dims", "read_only", "p3c7RU0o4sifzqp28z1cn2qyMs4ExRsS");

        private final String remoteHost;
        private final int remotePort;
        private final String dbName;
        private final String dbUser;
        private final String dbPassword;

        DatabaseEnvironmentDEV(String remoteHost, int remotePort, String dbName, String dbUser, String dbPassword) {
            this.remoteHost = remoteHost;
            this.remotePort = remotePort;
            this.dbName = dbName;
            this.dbUser = dbUser;
            this.dbPassword = dbPassword;
        }

        public String getRemoteHost() {
            return remoteHost;
        }

        public int getRemotePort() {
            return remotePort;
        }

        public String getDbName() {
            return dbName;
        }

        public String getDbUser() {
            return dbUser;
        }

        public String getDbPassword() {
            return dbPassword;
        }
    }

    private static final Map<String, DatabaseEnvironmentDEV> ENVIRONMENT_MAP = new HashMap<>();

    static {
        for (DatabaseEnvironmentDEV environment : DatabaseEnvironmentDEV.values()) {
            ENVIRONMENT_MAP.put(environment.name(), environment);
        }
    }

    public static DatabaseEnvironmentDEV getEnvironmentByName(String environmentName) {
        return ENVIRONMENT_MAP.get(environmentName);
    }

    public static class DatabaseConfigDev {
        public final String JDBC_URL;
        public final String DB_USERNAME;
        public final String DB_PASSWORD;

        public DatabaseConfigDev(DatabaseEnvironmentDEV environment) {
            JDBC_URL = "jdbc:mysql://" + environment.getRemoteHost() + ":" + environment.getRemotePort() + "/"
                    + environment.getDbName();
            DB_USERNAME = environment.getDbUser();
            DB_PASSWORD = environment.getDbPassword();
        }
    }

    public static final List<DatabaseConfigDev> DATABASE_CONFIGS_STG = Arrays.asList(
            new DatabaseConfigDev(DatabaseEnvironmentDEV.PPOBPROD),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.MULTIBILLER),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.SETORKU),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.SOLUSIPAY),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.DANA),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.OYPAYMENT),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.PAYMENTGATEWAY),
            new DatabaseConfigDev(DatabaseEnvironmentDEV.DISI));
}
