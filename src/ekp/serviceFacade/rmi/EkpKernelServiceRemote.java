package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ekp.serviceFacade.rmi.mbom.PartCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartRemote;

public interface EkpKernelServiceRemote extends Remote {
	public boolean testCallBack() throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	public PartRemote createPart(PartCreateObjRemote _dto) throws RemoteException;

	public boolean deletePart(String _uid) throws RemoteException;

	public PartRemote loadPart(String _uid) throws RemoteException;

	public PartRemote loadPartByPin(String _pin) throws RemoteException;

}
