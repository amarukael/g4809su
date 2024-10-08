package utility.db;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHTunnel {

	public static Session createSSHTunnel(String sshHost, String sshUser, String privateKeyPath, String privateKeyPassphrase, int sshPort, String remoteHost, int remotePort) throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(privateKeyPath, privateKeyPassphrase); // Add private key with passphrase

		Session session = jsch.getSession(sshUser, sshHost, sshPort);
		session.setConfig("StrictHostKeyChecking", "no"); // Disable host key checking (NOTE: This reduces security)
		session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
		session.connect();

		// Set port forwarding for the SSH tunnel
		session.setPortForwardingL(3306, remoteHost, remotePort);

		return session;
	}
}
