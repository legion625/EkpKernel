package ekp.serviceFacade.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.mbom.MbomService;
import ekp.mbom.PartAcquisition;
import ekp.serviceFacade.rmi.mbom.MbomFO;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionCreateObjRemote;
import ekp.serviceFacade.rmi.mbom.PartAcquisitionRemote;
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
	public PartAcquisitionRemote createPartAcquisition(PartAcquisitionCreateObjRemote _dto) throws RemoteException{
		return MbomFO.parsePartAcquisitionRemote(
				mbomService.createPartAcquisition(MbomFO.parsePartAcquisitionCreateObj(_dto)));
	}
	@Override
	public boolean deletePartAcquisition(String _uid) throws RemoteException{
		return mbomService.deletePartAcquisition(_uid);
	}
	@Override
	public PartAcquisitionRemote loadPartAcquisition(String _uid) throws RemoteException{
		return MbomFO.parsePartAcquisitionRemote(mbomService.loadPartAcquisition(_uid));
	}
	@Override
	public PartAcquisitionRemote loadPartAcquisitionById(String _id) throws RemoteException{
		return MbomFO.parsePartAcquisitionRemote(mbomService.loadPartAcquisitionById(_id));
	}
	
	@Override
	public List<PartAcquisitionRemote> loadPartAcquisitionList(String _partUid) throws RemoteException{
		List<PartAcquisition> list = mbomService.loadPartAcquisitionList(_partUid);
		List<PartAcquisitionRemote> remoteList = list.stream().map(MbomFO::parsePartAcquisitionRemote)
				.collect(Collectors.toList());
		return remoteList;
	}
}
