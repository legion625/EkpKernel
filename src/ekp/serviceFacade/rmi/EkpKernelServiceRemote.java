package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EkpKernelServiceRemote extends Remote{
	public boolean testCallBack() throws RemoteException;
}
