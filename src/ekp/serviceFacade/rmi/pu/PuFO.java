package ekp.serviceFacade.rmi.pu;

import ekp.pu.Purch;
import ekp.pu.PurchItem;
import ekp.pu.dto.PurchCreateObj;
import ekp.pu.dto.PurchItemCreateObj;

public class PuFO {
	// -------------------------------------------------------------------------------
	// -------------------------------------Purch-------------------------------------
	public static PurchRemote parsePurchRemote(Purch _obj) {
		PurchRemote remote = new PurchRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setPuNo(_obj.getPuNo());
		remote.setTitle(_obj.getTitle());
		remote.setSupplierUid(_obj.getSupplierUid());
		remote.setSupplierName(_obj.getSupplierName());
		remote.setSupplierBan(_obj.getSupplierBan());
		remote.setPerfStatus(_obj.getPerfStatus());
		remote.setPerfTime(_obj.getPerfTime());
		return remote;
	}
	
	public static PurchCreateObj parsePurchCreateObj(PurchCreateObjRemote _remote) {
		PurchCreateObj dto = new PurchCreateObj();
		dto.setPuNo(_remote.getPuNo());
		dto.setTitle(_remote.getTitle());
		dto.setSupplierUid(_remote.getSupplierUid());
		dto.setSupplierName(_remote.getSupplierName());
		dto.setSupplierBan(_remote.getSupplierBan());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// -----------------------------------PurchItem-----------------------------------
	public static PurchItemRemote parsePurchItemRemote(PurchItem _obj) {
		PurchItemRemote remote = new PurchItemRemote(_obj.getUid(), _obj.getObjectCreateTime(), _obj.getObjectUpdateTime());
		remote.setPurchUid(_obj.getPurchUid());
		remote.setMmUid(_obj.getMmUid());
		remote.setMmMano(_obj.getMmMano());
		remote.setMmName(_obj.getMmName());
		remote.setMmSpecification(_obj.getMmSpecification());
		remote.setMmStdUnit(_obj.getMmStdUnit());
		remote.setRefPa(_obj.isRefPa());
		remote.setRefPaUid(_obj.getRefPaUid());
		remote.setRefPaType(_obj.getRefPaType());
		remote.setQty(_obj.getQty());
		remote.setValue(_obj.getValue());
		remote.setRemark(_obj.getRemark());
		return remote;
	}
	
	public static PurchItemCreateObj parsePurchItemCreateObj(PurchItemCreateObjRemote _remote) {
		PurchItemCreateObj dto = new PurchItemCreateObj();
		dto.setPurchUid(_remote.getPurchUid());
		dto.setMmUid(_remote.getMmUid());
		dto.setMmMano(_remote.getMmMano());
		dto.setMmName(_remote.getMmName());
		dto.setMmSpecification(_remote.getMmSpecification());
		dto.setMmStdUnit(_remote.getMmStdUnit());
		dto.setRefPa(_remote.isRefPa());
		dto.setRefPaUid(_remote.getRefPaUid());
		dto.setRefPaType(_remote.getRefPaType());
		dto.setQty(_remote.getQty());
		dto.setValue(_remote.getValue());
		dto.setRemark(_remote.getRemark());
		return dto;
		
	}
}
