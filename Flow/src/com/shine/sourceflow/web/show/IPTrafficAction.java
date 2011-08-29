package com.shine.sourceflow.web.show;

import com.shine.sourceflow.model.show.IPTrafficDTO;
import com.shine.sourceflow.service.show.IPTrafficService;

/**
 * IP流量
 */
public class IPTrafficAction extends GenericAction {
	private static final long serialVersionUID = 1730268592434335250L;
	public static final String IP_SRC = "ipSrc";
	public static final String IP_DST = "ipDst";
	
	public IPTrafficAction() {
		this.dto = new IPTrafficDTO();
		this.service = new IPTrafficService();
	}
	
	public String list() {
		this.dto.init(this.request);
		this.dbModels = this.service.query(this.dto);
		return "list";
	}
}