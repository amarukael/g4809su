package utility.db;

import java.util.HashMap;
import java.util.Map;

public class DBConfigDev {
	public enum DatabaseEnvironmentDEV {
		PPOBPROD("117.54.12.146", "adhy", "4dhy@1Ds", 2260, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "ppobprod", "adhy", "4dhy@1Ds"),
		IDSPROD("117.54.12.146", "adhy", "4dhy@1Ds", 2260, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "idsprod", "adhy", "4dhy@1Ds"),
		SETORKU("117.54.12.146", "adhy", "4dhy@1Ds", 2261, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "setorku", "adhy", "4dhy@1Ds"),
		IDS_SETORKU("117.54.12.146", "adhy", "4dhy@1Ds", 2261, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "ids_setorku", "adhy", "4dhy@1Ds"),
		DANA("117.54.12.146", "adhy", "4dhy@1Ds", 2262, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "dana", "adhy", "4dhy@1Ds"),
		MnM("117.54.12.146", "adhy", "4dhy@1Ds", 2263, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "ids_messaging", "adhy", "4dhy@1Ds"),
		DIMS("117.54.12.146", "adhy", "4dhy@1Ds", 3358, "localhost", 3306,
				"src/test/resources/sshprivatekey/newopenssh", "dims", "adhy", "4dhy@1Ds");

		private final String sshHost;
		private final String sshUser;
		private final String sshPassword;
		private final int sshPort;
		private final String remoteHost;
		private final int remotePort;
		private final String privateKeyPath;
		private final String dbName;
		private final String dbUser;
		private final String dbPassword;

		DatabaseEnvironmentDEV(String sshHost, String sshUser, String sshPassword, int sshPort,
				String remoteHost, int remotePort, String privateKeyPath, String dbName, String dbUser,
				String dbPassword) {
			this.sshHost = sshHost;
			this.sshUser = sshUser;
			this.sshPassword = sshPassword;
			this.sshPort = sshPort;
			this.remoteHost = remoteHost;
			this.remotePort = remotePort;
			this.privateKeyPath = privateKeyPath;
			this.dbName = dbName;
			this.dbUser = dbUser;
			this.dbPassword = dbPassword;
		}

		public String getSshHost() {
			return sshHost;
		}

		public String getSshUser() {
			return sshUser;
		}

		public String getSshPassword() {
			return sshPassword;
		}

		public int getSshPort() {
			return sshPort;
		}

		public String getRemoteHost() {
			return remoteHost;
		}

		public int getRemotePort() {
			return remotePort;
		}

		public String getPrivateKeyPath() {
			return privateKeyPath;
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
		public final String sshHost;
		public final String sshUser;
		public final String sshPassword;
		public final int sshPort;
		public final String remoteHost;
		public final int remotePort;
		public final String privateKeyPath;
		public final String jdbcUrl;
		public final String jdbcUrlHikari;
		public final String dbUser;
		public final String dbPassword;

		public DatabaseConfigDev(DatabaseEnvironmentDEV environment) {
			sshHost = environment.getSshHost();
			sshUser = environment.getSshUser();
			sshPassword = environment.getSshPassword();
			sshPort = environment.getSshPort();
			remoteHost = environment.getRemoteHost();
			remotePort = environment.getRemotePort();
			privateKeyPath = environment.getPrivateKeyPath();
			jdbcUrl = "jdbc:mysql://" + environment.getRemoteHost() + ":" + environment.getRemotePort() + "/" + environment.getDbName();
			jdbcUrlHikari = "jdbc:mysql://" + environment.getSshHost() + ":" + environment.getSshPort() + "/" + environment.getDbName() + "?zeroDateTimeBehavior=convertToNull";
			dbUser = environment.getDbUser();
			dbPassword = environment.getDbPassword();
		}
	}
}

