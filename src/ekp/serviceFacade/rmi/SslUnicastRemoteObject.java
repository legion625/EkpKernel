package ekp.serviceFacade.rmi;

import java.rmi.server.UnicastRemoteObject;

import legion.datasource.source.RmiSslClientSocketFactory;
import legion.datasource.source.RmiSslServerSocketFactory;

public class SslUnicastRemoteObject extends UnicastRemoteObject {

	public SslUnicastRemoteObject(int port) throws Exception {
		super(port, new RmiSslClientSocketFactory(), new RmiSslServerSocketFactory());
	}

}
