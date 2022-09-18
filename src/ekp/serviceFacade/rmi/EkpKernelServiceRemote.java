package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ekp.serviceFacade.rmi.mbom.PartAcquisitionCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionRemote;
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

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	public PartAcquisitionRemote createPartAcquisition(PartAcquisitionCreateObjRemote _dto) throws RemoteException;

	public boolean deletePartAcquisition(String _uid) throws RemoteException;

	public PartAcquisitionRemote loadPartAcquisition(String _uid) throws RemoteException;

	public PartAcquisitionRemote loadPartAcquisitionById(String _id) throws RemoteException;

	public List<PartAcquisitionRemote> loadPartAcquisitionList(String _partUid) throws RemoteException;

}
