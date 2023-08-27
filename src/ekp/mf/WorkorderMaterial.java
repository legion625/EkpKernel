package ekp.mf;

import legion.ObjectModel;

public class WorkorderMaterial extends ObjectModel {
	private String woUid;
	private String woNo;

	// 料件基本檔
	private String mmUid;
	private String mmMano;
	private String mmName;
	private double qty0; // 待領用量
	private double qty1; // 已領用量

//TODO

	@Override
	protected boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
