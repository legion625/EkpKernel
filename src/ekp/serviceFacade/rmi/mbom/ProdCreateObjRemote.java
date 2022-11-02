package ekp.serviceFacade.rmi.mbom;

import java.io.Serializable;

public class ProdCreateObjRemote implements Serializable {
	private String id; // 型號 biz key
	private String name; // 名稱

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
