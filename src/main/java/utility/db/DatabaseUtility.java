package utility.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.sql.ResultSetMetaData;

public class DatabaseUtility {
	
	public static String getSecretKeyByPartnerId(String partnerId, DBConfigDev.DatabaseConfigDev config, String TableName) {
		String secretKey = null;
		Session session = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String SQL = "SELECT secretkey FROM " + TableName + " WHERE partnerid=? AND isactive='1' LIMIT 1";

		try {
			// Establish SSH tunnel
			session = SSHTunnel.createSSHTunnel(config.sshHost, config.sshUser, config.privateKeyPath, config.sshPassword, config.sshPort, config.remoteHost, config.remotePort);

			// Connect to MySQL database through the SSH tunnel
			connection = DriverManager.getConnection(config.jdbcUrl, config.dbUser, config.dbPassword);
			statement = connection.prepareStatement(SQL);
			statement.setString(1, partnerId);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				secretKey = resultSet.getString("secretkey");
			}
		} catch (SQLException | JSchException e) {
			// Handle exceptions or log errors here
			e.printStackTrace();
		} finally {
			// Close resources in a finally block to ensure they're closed even if an exception occurs
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (session != null && session.isConnected()) {
					session.disconnect();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return secretKey;
	}

	public static void printTableByTrackingRef(String trackingRef, DBConfigDev.DatabaseConfigDev config, String table) {
		Session session = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String SQL = "SELECT * FROM " + table + " WHERE tracking_ref = "+trackingRef+"";

		try {
			// Establish SSH tunnel
			session = SSHTunnel.createSSHTunnel(config.sshHost, config.sshUser, config.privateKeyPath, config.sshPassword, config.sshPort, config.remoteHost, config.remotePort);

			// Connect to MySQL database through the SSH tunnel
			connection = DriverManager.getConnection(config.jdbcUrl, config.dbUser, config.dbPassword);
			statement = connection.prepareStatement(SQL);

			// Set the parameter value using prepared statement
			statement.setString(1, trackingRef);

			resultSet = statement.executeQuery();

			// Get metadata to retrieve column names
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			// Loop through the ResultSet and print all columns for each row
			while (resultSet.next()) {
				StringBuilder row = new StringBuilder();
				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					String columnValue = resultSet.getString(columnName);
					row.append(columnName).append(": ").append(columnValue).append(", ");
				}
				System.out.println(row.toString());
			}
		} catch (SQLException | JSchException e) {
			// Handle exceptions or log errors here
			e.printStackTrace();
		} finally {
			// Close resources in a finally block to ensure they're closed even if an exception occurs
			// Close result set, statement, connection, and SSH session
		}
	}


	public static void deleteLogByTrackingRef(String trackingref, DBConfigDev.DatabaseConfigDev config) {
		Session session = null;
		Connection connection = null;
		PreparedStatement statement = null;

		String SQL = "DELETE FROM tb_r_logpaydata WHERE tracking_ref = ?";

		try {
			// Establish SSH tunnel
			session = SSHTunnel.createSSHTunnel(config.sshHost, config.sshUser, config.privateKeyPath, config.sshPassword, config.sshPort, config.remoteHost, config.remotePort);

			// Connect to MySQL database through the SSH tunnel
			connection = DriverManager.getConnection(config.jdbcUrl, config.dbUser, config.dbPassword);
			statement = connection.prepareStatement(SQL);
			statement.setString(1, trackingref);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Row with tracking_ref " + trackingref + " deleted successfully.");
			} else {
				System.out.println("No rows found with tracking_ref " + trackingref + ".");
			}

		} catch (SQLException | JSchException e) {
			// Handle exceptions or log errors here
			e.printStackTrace();
		} finally {
			// Close resources in a finally block to ensure they're closed even if an exception occurs
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
				if (session != null && session.isConnected()) {
					session.disconnect();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}


