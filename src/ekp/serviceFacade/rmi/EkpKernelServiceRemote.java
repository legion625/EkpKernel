package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ekp.serviceFacade.rmi.mbom.ParsPartRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepRemote;
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

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	public PartAcqRoutingStepRemote createPartAcqRoutingStep(PartAcqRoutingStepCreateObjRemote _dto)
			throws RemoteException;

	public boolean deletePartAcqRoutingStep(String _uid) throws RemoteException;

	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _uid) throws RemoteException;

	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _partAcqUid, String _id) throws RemoteException;

	public List<PartAcqRoutingStepRemote> loadPartAcqRoutingStepList(String _partAcqUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	public ParsProcRemote createParsProc(ParsProcCreateObjRemote _dto) throws RemoteException;

	public boolean deleteParsProc(String _uid) throws RemoteException;

	public ParsProcRemote loadParsProc(String _uid) throws RemoteException;

	public List<ParsProcRemote> loadParsProcList(String _parsUid) throws RemoteException;

	public List<ParsProcRemote> loadParsProcListByProc(String _procUid) throws RemoteException;

	public boolean parsProcAssignProc(String _uid, String _procUid, String _procId) throws RemoteException;

	public boolean parsProcRevertAssignProc(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	public ParsPartRemote createParsPart(String _parsUid) throws RemoteException;
	public boolean deleteParsPart(String _uid) throws RemoteException;
	public ParsPartRemote loadParsPart(String _uid) throws RemoteException;
	public List<ParsPartRemote> loadParsPartList(String _parsUid) throws RemoteException;
	public List<ParsPartRemote> loadParsPartListByPart(String _partUid) throws RemoteException;
	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty) throws RemoteException;
	public boolean parsePartRevertAssignPart(String _uid) throws RemoteException;

}
