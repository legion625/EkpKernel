package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ekp.mbom.Part;
import ekp.mbom.dto.PartCreateObj;

public interface EkpKernelServiceRemote extends Remote {
	public boolean testCallBack() throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------

	public Part createPart(PartCreateObj _dto);

	public boolean deletePart(String _uid);

	public Part loadPart(String _uid);

	public Part loadPartByPin(String _pin);

}
