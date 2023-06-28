package ekp.serviceFacade.rmi.invt;

import java.io.Serializable;

import ekp.mbom.type.PartUnit;

public class MaterialMasterCreateObjRemote implements Serializable{
	private String mano;
	private String name;
	private String specification;

	private PartUnit stdUnit;

	public String getMano() {
		return mano;
	}

	public void setMano(String mano) {
		this.mano = mano;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public PartUnit getStdUnit() {
		return stdUnit;
	}

	public void setStdUnit(PartUnit stdUnit) {
		this.stdUnit = stdUnit;
	}

}
