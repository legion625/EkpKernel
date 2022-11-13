package ekp.serviceFacade.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.Prod;
import ekp.mbom.ProdCtl;
import ekp.mbom.ProdCtlPartCfgConj;
import ekp.mbom.ProdMod;
import ekp.mbom.ProdModItem;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.mbom.dto.ProdCreateObj;
import ekp.mbom.dto.ProdCtlCreateObj;
import ekp.mbom.dto.ProdModCreateObj;
import ekp.serviceFacade.rmi.mbom.ParsPartRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ParsProcRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcqRoutingStepRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgConjRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartCfgRemote;
import ekp.serviceFacade.rmi.mbom.PartCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartRemote;
import ekp.serviceFacade.rmi.mbom.ProdCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlPartCfgConjRemote;
import ekp.serviceFacade.rmi.mbom.ProdCtlRemote;
import ekp.serviceFacade.rmi.mbom.ProdModCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.ProdModItemRemote;
import ekp.serviceFacade.rmi.mbom.ProdModRemote;
import ekp.serviceFacade.rmi.mbom.ProdRemote;

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

	public PartAcquisitionRemote loadPartAcquisition(String _partPin, String _id) throws RemoteException;

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
	
	public ParsPartRemote loadParsPart(String _parsUid, String _partuid) throws RemoteException;

	public List<ParsPartRemote> loadParsPartList(String _parsUid) throws RemoteException;

	public List<ParsPartRemote> loadParsPartListByPart(String _partUid) throws RemoteException;

	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty)
			throws RemoteException;

	public boolean parsPartRevertAssignPart(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	public PartCfgRemote createPartCfg(PartCfgCreateObjRemote _dto) throws RemoteException;

	public boolean deletePartCfg(String _uid) throws RemoteException;

	public PartCfgRemote loadPartCfg(String _uid) throws RemoteException;
	
	public PartCfgRemote loadPartCfgById(String _id) throws RemoteException;

	public List<PartCfgRemote> loadPartCfgList(String _rootPartUid) throws RemoteException;

	public boolean partCfgStartEditing(String _uid) throws RemoteException;

	public boolean partCfgRevertStartEditing(String _uid) throws RemoteException;

	public boolean partCfgPublish(String _uid) throws RemoteException;

	public boolean partCfgRevertPublish(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	public PartCfgConjRemote createPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException;

	public boolean deletePartCfgConj(String _uid) throws RemoteException;

	public PartCfgConjRemote loadPartCfgConj(String _uid) throws RemoteException;
	
	public PartCfgConjRemote loadPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException;
	
	public List<PartCfgConjRemote> loadPartCfgConjList(String _partCfgUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// -------------------------------------Prod--------------------------------------
	public ProdRemote createProd(ProdCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProd(String _uid) throws RemoteException;

	public ProdRemote loadProd(String _uid) throws RemoteException;
	
	public ProdRemote loadProdById(String _id) throws RemoteException;

	public List<ProdRemote> loadProdList() throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdCtl------------------------------------
	public ProdCtlRemote createProdCtl(ProdCtlCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProdCtl(String _uid) throws RemoteException;

	public ProdCtlRemote loadProdCtl(String _uid) throws RemoteException;
	
	public ProdCtlRemote loadProdCtlById(String _id) throws RemoteException;

	public List<ProdCtlRemote> loadProdCtlList(String _parentUid) throws RemoteException;

	public List<ProdCtlRemote> loadProdCtlListLv1(String _prodUid) throws RemoteException;

	public boolean prodCtlAssignParent(String _uid, String _parentUid, String _parentId) throws RemoteException;

	public boolean prodCtlUnassignParent(String _uid) throws RemoteException;

	public boolean prodCtlAssignProd(String _uid, String _prodUid) throws RemoteException;

	public boolean prodCtlUnassignProd(String _uid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------ProdCtlPartCfgConj-------------------------------
	public ProdCtlPartCfgConjRemote createProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid)
			throws RemoteException;

	public boolean deleteProdCtlPartCfgConj(String _uid) throws RemoteException;

	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _uid) throws RemoteException;
	
	public ProdCtlPartCfgConjRemote loadProdCtlPartCfgConj(String _prodCtlUid, String _partCfgUid) throws RemoteException;

	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList1(String _prodCtlUid) throws RemoteException;

	public List<ProdCtlPartCfgConjRemote> loadProdCtlPartCfgConjList2(String _partCfgUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ------------------------------------ProdMod------------------------------------
	public ProdModRemote createProdMod(ProdModCreateObjRemote _dto) throws RemoteException;

	public boolean deleteProdMod(String _uid) throws RemoteException;

	public ProdModRemote loadProdMod(String _uid) throws RemoteException;

	public List<ProdModRemote> loadProdModList(String _prodUid) throws RemoteException;

	// -------------------------------------------------------------------------------
	// ----------------------------------ProdModItem----------------------------------
	public ProdModItemRemote createProdModItem(String prodModUid, String prodCtlUid) throws RemoteException;

	public boolean deleteProdModItem(String _uid) throws RemoteException;

	public ProdModItemRemote loadProdModItem(String _uid) throws RemoteException;

	public List<ProdModItemRemote> loadProdModItemList(String _prodModUid) throws RemoteException;

	public boolean prodModItemAssignPartCfg(String _uid, String _partCfgUid) throws RemoteException;

	public boolean prodModItemUnassignPartCfg(String _uid) throws RemoteException;

}
