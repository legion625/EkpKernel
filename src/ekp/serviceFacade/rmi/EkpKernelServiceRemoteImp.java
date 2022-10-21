package ekp.serviceFacade.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.mbom.MbomService;
import ekp.mbom.ParsPart;
import ekp.mbom.ParsProc;
import ekp.mbom.PartAcqRoutingStep;
import ekp.mbom.PartAcquisition;
import ekp.mbom.PartCfg;
import ekp.mbom.PartCfgConj;
import ekp.mbom.dto.PartCfgCreateObj;
import ekp.serviceFacade.rmi.mbom.MbomFO;
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
import legion.BusinessServiceFactory;

public class EkpKernelServiceRemoteImp extends UnicastRemoteObject implements EkpKernelServiceRemote {

	private static Logger log = LoggerFactory.getLogger(EkpKernelServiceRemoteImp.class);

	// -------------------------------------------------------------------------------
	private MbomService mbomService = BusinessServiceFactory.getInstance().getService(MbomService.class);

	// -------------------------------------------------------------------------------
	public EkpKernelServiceRemoteImp(int port) throws Exception {
		super(port);
		// TODO Auto-generated constructor stub
	}

	// -------------------------------------------------------------------------------
	@Override
	public boolean testCallBack() throws RemoteException {
		return true;
	}

	// -------------------------------------------------------------------------------
	// -------------------------------------Part--------------------------------------
	@Override
	public PartRemote createPart(PartCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartRemote(mbomService.createPart(MbomFO.parsePartCreateObj(_dto)));
	}

	@Override
	public boolean deletePart(String _uid) throws RemoteException {
		return mbomService.deletePart(_uid);
	}

	@Override
	public PartRemote loadPart(String _uid) throws RemoteException {
		return MbomFO.parsePartRemote(mbomService.loadPart(_uid));
	}

	@Override
	public PartRemote loadPartByPin(String _pin) throws RemoteException {
		return MbomFO.parsePartRemote(mbomService.loadPartByPin(_pin));
	}

	// -------------------------------------------------------------------------------
	// --------------------------------PartAcquisition--------------------------------
	@Override
	public PartAcquisitionRemote createPartAcquisition(PartAcquisitionCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartAcquisitionRemote(
				mbomService.createPartAcquisition(MbomFO.parsePartAcquisitionCreateObj(_dto)));
	}

	@Override
	public boolean deletePartAcquisition(String _uid) throws RemoteException {
		return mbomService.deletePartAcquisition(_uid);
	}

	@Override
	public PartAcquisitionRemote loadPartAcquisition(String _uid) throws RemoteException {
		return MbomFO.parsePartAcquisitionRemote(mbomService.loadPartAcquisition(_uid));
	}

	@Override
	public PartAcquisitionRemote loadPartAcquisitionById(String _id) throws RemoteException {
		return MbomFO.parsePartAcquisitionRemote(mbomService.loadPartAcquisitionById(_id));
	}

	@Override
	public List<PartAcquisitionRemote> loadPartAcquisitionList(String _partUid) throws RemoteException {
		List<PartAcquisition> list = mbomService.loadPartAcquisitionList(_partUid);
		List<PartAcquisitionRemote> remoteList = list.stream().map(MbomFO::parsePartAcquisitionRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// ------------------------------PartAcqRoutingStep-------------------------------
	@Override
	public PartAcqRoutingStepRemote createPartAcqRoutingStep(PartAcqRoutingStepCreateObjRemote _dto)
			throws RemoteException {
		return MbomFO.parsePartAcqRoutingStepRemote(
				mbomService.createPartAcqRoutingStep(MbomFO.parsePartAcqRoutingStepCreateObj(_dto)));
	}

	@Override
	public boolean deletePartAcqRoutingStep(String _uid) throws RemoteException {
		return mbomService.deletePartAcqRoutingStep(_uid);
	}

	@Override
	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _uid) throws RemoteException {
		return MbomFO.parsePartAcqRoutingStepRemote(mbomService.loadPartAcqRoutingStep(_uid));
	}

	@Override
	public PartAcqRoutingStepRemote loadPartAcqRoutingStep(String _partAcqUid, String _id) throws RemoteException {
		return MbomFO.parsePartAcqRoutingStepRemote(mbomService.loadPartAcqRoutingStep(_partAcqUid, _id));
	}

	@Override
	public List<PartAcqRoutingStepRemote> loadPartAcqRoutingStepList(String _partAcqUid) throws RemoteException {
		List<PartAcqRoutingStep> list = mbomService.loadPartAcqRoutingStepList(_partAcqUid);
		List<PartAcqRoutingStepRemote> remoteList = list.stream().map(MbomFO::parsePartAcqRoutingStepRemote)
				.collect(Collectors.toList());
		return remoteList;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsProc------------------------------------
	@Override
	public ParsProcRemote createParsProc(ParsProcCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parseParsProcRemote(mbomService.createParsProc(MbomFO.parseParsProcCreateObj(_dto)));
	}

	@Override
	public boolean deleteParsProc(String _uid) throws RemoteException {
		return mbomService.deleteParsProc(_uid);
	}

	@Override
	public ParsProcRemote loadParsProc(String _uid) throws RemoteException {
		return MbomFO.parseParsProcRemote(mbomService.loadParsProc(_uid));
	}

	@Override
	public List<ParsProcRemote> loadParsProcList(String _parsUid) throws RemoteException {
		List<ParsProc> list = mbomService.loadParsProcList(_parsUid);
		List<ParsProcRemote> remoteList = list.stream().map(MbomFO::parseParsProcRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ParsProcRemote> loadParsProcListByProc(String _procUid) throws RemoteException {
		List<ParsProc> list = mbomService.loadParsProcListByProc(_procUid);
		List<ParsProcRemote> remoteList = list.stream().map(MbomFO::parseParsProcRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean parsProcAssignProc(String _uid, String _procUid, String _procId) throws RemoteException {
		return mbomService.parsProcAssignProc(_uid, _procUid, _procId);
	}

	@Override
	public boolean parsProcRevertAssignProc(String _uid) throws RemoteException {
		return mbomService.parsProcRevertAssignProc(_uid);
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------ParsPart------------------------------------
	@Override
	public ParsPartRemote createParsPart(String _parsUid) throws RemoteException {
		return MbomFO.parseParsPartRemote(mbomService.createParsPart(_parsUid));
	}

	@Override
	public boolean deleteParsPart(String _uid) throws RemoteException {
		return mbomService.deleteParsPart(_uid);
	}

	@Override
	public ParsPartRemote loadParsPart(String _uid) throws RemoteException {
		return MbomFO.parseParsPartRemote(mbomService.loadParsPart(_uid));
	}

	@Override
	public List<ParsPartRemote> loadParsPartList(String _parsUid) throws RemoteException {
		List<ParsPart> list = mbomService.loadParsPartList(_parsUid);
		List<ParsPartRemote> remoteList = list.stream().map(MbomFO::parseParsPartRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public List<ParsPartRemote> loadParsPartListByPart(String _partUid) throws RemoteException {
		List<ParsPart> list = mbomService.loadParsPartListByPart(_partUid);
		List<ParsPartRemote> remoteList = list.stream().map(MbomFO::parseParsPartRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean parsPartAssignPart(String _uid, String _partUid, String _partPin, double _partReqQty)
			throws RemoteException {
		return mbomService.parsPartAssignPart(_uid, _partUid, _partPin, _partReqQty);
	}

	@Override
	public boolean parsePartRevertAssignPart(String _uid) throws RemoteException {
		return mbomService.parsePartRevertAssignPart(_uid);
	}

	// -------------------------------------------------------------------------------
	// ------------------------------------PartCfg------------------------------------
	@Override
	public PartCfgRemote createPartCfg(PartCfgCreateObjRemote _dto) throws RemoteException {
		return MbomFO.parsePartCfgRemote(mbomService.createPartCfg(MbomFO.parsePartCfgCreateObj(_dto)));
	}

	@Override
	public boolean deletePartCfg(String _uid) throws RemoteException {
		return mbomService.deletePartCfg(_uid);
	}

	@Override
	public PartCfgRemote loadPartCfg(String _uid) throws RemoteException {
		return MbomFO.parsePartCfgRemote(mbomService.loadPartCfg(_uid));
	}

	@Override
	public List<PartCfgRemote> loadPartCfgList(String _rootPartUid) throws RemoteException {
		List<PartCfg> list = mbomService.loadPartCfgList(_rootPartUid);
		List<PartCfgRemote> remoteList = list.stream().map(MbomFO::parsePartCfgRemote).collect(Collectors.toList());
		return remoteList;
	}

	@Override
	public boolean partCfgStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgStartEditing(_uid);
	}

	@Override
	public boolean partCfgRevertStartEditing(String _uid) throws RemoteException {
		return mbomService.partCfgRevertStartEditing(_uid);
	}

	@Override
	public boolean partCfgPublish(String _uid) throws RemoteException {
		return mbomService.partCfgPublish(_uid);
	}

	@Override
	public boolean partCfgRevertPublish(String _uid) throws RemoteException {
		return mbomService.partCfgRevertPublish(_uid);
	}

	// -------------------------------------------------------------------------------
	// ----------------------------------PartCfgConj----------------------------------
	@Override
	public PartCfgConjRemote createPartCfgConj(String _partCfgUid, String _partAcqUid) throws RemoteException {
		return MbomFO.parsePartCfgConjRemote(mbomService.createPartCfgConj(_partCfgUid, _partAcqUid));
	}

	@Override
	public boolean deletePartCfgConj(String _uid) throws RemoteException {
		return mbomService.deletePartCfgConj(_uid);
	}

	@Override
	public PartCfgConjRemote loadPartCfgConj(String _uid) throws RemoteException {
		return MbomFO.parsePartCfgConjRemote(mbomService.loadPartCfgConj(_uid));
	}

	@Override
	public List<PartCfgConjRemote> loadPartCfgConjList(String _partCfgUid) throws RemoteException {
		List<PartCfgConj> list = mbomService.loadPartCfgConjList(_partCfgUid);
		List<PartCfgConjRemote> remoteList = list.stream().map(MbomFO::parsePartCfgConjRemote)
				.collect(Collectors.toList());
		return remoteList;
	}
}
