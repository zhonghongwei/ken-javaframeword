package com.shine.sourceflow.web.show.trend;

import java.text.DecimalFormat;
import java.util.Map;

import com.shine.DBUtil.model.DBModel;
import com.shine.sourceflow.model.show.trend.ProtocolTrafficTrendDto;
import com.shine.sourceflow.service.show.trend.ProtocolTrafficTrendService;

public class ProtocolTrafficTrendAction extends TrendGenericAction {
	private static final long serialVersionUID = 9192398526313654968L;

	public ProtocolTrafficTrendAction() {
		this.dto = new ProtocolTrafficTrendDto();
		this.service = new ProtocolTrafficTrendService();
	}
	
	@Override
	protected void generateCharts() {
		StringBuffer cvsStr = new StringBuffer();
		DecimalFormat format = new DecimalFormat("0.00");
		for (Map.Entry<String, DBModel> entry : dbModels.entrySet()) {
			if (!entry.getValue().isEmpty()) {
				cvsStr.append(entry.getKey());
				cvsStr.append(";");
				String srcIpTotal = entry.getValue().get(0).
					getString("src_port_total_bytes").isEmpty() ? 
					"0" : entry.getValue().get(0).getString("src_port_total_bytes");
				cvsStr.append(format.format(Double.
						parseDouble(srcIpTotal) / 1048576));
				cvsStr.append(";");
				String dstIpTotal = entry.getValue().get(0).
					getString("dst_port_total_bytes").isEmpty() ? 
					"0" : entry.getValue().get(0).getString("dst_port_total_bytes");
				cvsStr.append(format.format(Double.
						parseDouble(dstIpTotal) / 1048576));
				cvsStr.append("\\n");
			}
		}
		this.charts.put(DATA_DEFAULT, cvsStr.toString());
	}
}
