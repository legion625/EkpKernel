package ekp.serviceFacade.rmi.invt;

import java.io.Serializable;

public class MaterialBinStockCreateObjRemote implements Serializable {
	private String mmUid; // material master的uid
	private String mano; // (redundant attribute)

	private String wrhsBinUid; // biz key

	public String getMmUid() {
		return mmUid;
	}

	public void setMmUid(String mmUid) {
		this.mmUid = mmUid;
	}

	public String getMano() {
		return mano;
	}

	public void setMano(String mano) {
		this.mano = mano;
	}

	public String getWrhsBinUid() {
		return wrhsBinUid;
	}

	public void setWrhsBinUid(String wrhsBinUid) {
		this.wrhsBinUid = wrhsBinUid;
	}
}
