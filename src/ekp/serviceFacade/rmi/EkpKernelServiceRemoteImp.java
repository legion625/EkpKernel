package ekp.serviceFacade.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ekp.mbom.MbomService;
import ekp.serviceFacade.rmi.mbom.MbomFO;
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
}
