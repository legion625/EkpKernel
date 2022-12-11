package ekp.data.service.mbom.query;

import legion.util.query.QueryParam;

public enum PpartSkewerQueryParam implements QueryParam {
	/* p */
	P_UID("pUid", "parent part uid"), //
	P_PIN("pPin", "parent part pin"), //
	P_NAME("pName", "parent part name"), //
	
	/* pa */
	PA_UID("paUid", "part acquisition uid"), //
	PA_ID("paId", "part acquisition id"), //
	PA_NAME("paId", "part acquisition name"), //
	
	/* pars */
	PARS_SEQ("parsSeq", "routing step seq"), //
	PARS_NAME("parsName", "routing step name"), //
	PARS_DESP("parsDesp", "routing step desription"), //
	
	/* ppart */
	PARS_UID("parsUid", "routing step uid"), //
	ASSIGN_PART("assignPart", "assign part"), //
	PART_UID("partUid", "part uid"), //
	PART_PIN("partPin", "part pin"), //
	
	/* ppart-p */
	PART_NAME("partName", "part name"), //

	/* PartCfg */
	PC_ROOT_PART_UID("pcRootPartUid", "part configuration root part uid"), //
	PC_ROOT_PART_PIN("pcRootPartPin", "part configuration root part pin"), //
	B_OF_PC$_PA_EXISTS("B_OF_PC$_PA_EXISTS", "B_OF_PC$_PA_EXISTS"), //
	B_OF_PC$_PARENT_PART_EXISTS("PC_IDs_IN_PARENT_PA","PC_IDs_IN_PARENT_PA"), //
	//
	B_OF_PC_ROOT_PART("B_OF_PC_ROOT_PART", "B_OF_PC_ROOT_PART"), //

	/* PartCfgConj */

	;

	private String id, desp;

	private PpartSkewerQueryParam(String id, String desp) {
		this.id = id;
		this.desp = desp;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDesp() {
		return desp;
	}
}
