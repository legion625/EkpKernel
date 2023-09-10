package ekp.serviceFacade.rmi.sd;

import ekp.sd.SalesOrder;
import ekp.sd.SalesOrderItem;
import ekp.sd.dto.SalesOrderCreateObj;
import ekp.sd.dto.SalesOrderItemCreateObj;

public class SdFO {
	// -------------------------------------------------------------------------------
	// ----------------------------------SalesOrder-----------------------------------
	public static SalesOrderRemote parseSalesOrderRemote(SalesOrder _obj) {
		SalesOrderRemote remote = new SalesOrderRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setSosn(_obj.getSosn());
		remote.setTitle(_obj.getTitle());
		remote.setCustomerName(_obj.getCustomerName());
		remote.setCustomerBan(_obj.getCustomerBan());
		remote.setSalerId(_obj.getSalerId());
		remote.setSalerName(_obj.getSalerName());
		remote.setSaleDate(_obj.getSaleDate());
		return remote;
	}

	public static SalesOrderCreateObj parseSalesOrderCreateObj(SalesOrderCreateObjRemote _remote) {
		SalesOrderCreateObj dto = new SalesOrderCreateObj();
		dto.setTitle(_remote.getTitle());
		dto.setCustomerName(_remote.getCustomerName());
		dto.setCustomerBan(_remote.getCustomerBan());
		dto.setSalerId(_remote.getSalerId());
		dto.setSalerName(_remote.getSalerName());
		dto.setSaleDate(_remote.getSaleDate());
		return dto;
	}

	// -------------------------------------------------------------------------------
	// --------------------------------SalesOrderItem---------------------------------
	public static SalesOrderItemRemote parseSalesOrderItemRemote(SalesOrderItem _obj) {
		SalesOrderItemRemote remote = new SalesOrderItemRemote(_obj.getUid(), _obj.getObjectCreateTime(),
				_obj.getObjectUpdateTime());
		remote.setSoUid(_obj.getSoUid());
		remote.setMmUid(_obj.getMmUid());
		remote.setMmMano(_obj.getMmMano());
		remote.setMmName(_obj.getMmName());
		remote.setMmSpec(_obj.getMmSpec());
		remote.setQty(_obj.getQty());
		remote.setValue(_obj.getValue());
		remote.setAllDelivered(_obj.isAllDelivered());
		remote.setFinishDeliveredDate(_obj.getFinishDeliveredDate());
		return remote;
	}

	public static SalesOrderItemCreateObj parseSalesOrderItemCreateObj(SalesOrderItemCreateObjRemote _remote) {
		SalesOrderItemCreateObj dto = new SalesOrderItemCreateObj();
		dto.setMmUid(_remote.getMmUid());
		dto.setMmMano(_remote.getMmMano());
		dto.setMmName(_remote.getMmName());
		dto.setMmSpec(_remote.getMmSpec());
		dto.setQty(_remote.getQty());
		dto.setValue(_remote.getValue());
		return dto;
	}

}