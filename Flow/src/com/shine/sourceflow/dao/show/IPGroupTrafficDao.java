package com.shine.sourceflow.dao.show;

import java.text.DecimalFormat;
import java.util.Map;

import com.shine.DBUtil.model.DBModel;
import com.shine.sourceflow.dao.show.strategy.IPGroupTrafficStdQueryStrategy;
import com.shine.sourceflow.web.GenericAction;

public class IPGroupTrafficDao extends ShowGenericDao {
	public IPGroupTrafficDao() {
		this.queryStrategy = new IPGroupTrafficStdQueryStrategy();
	}
	
	@Override
	public void handleModel(Map<String, DBModel> dbModels, DBModel srcDBModel,
			DBModel dstDBModel, DecimalFormat perFormat,
			DecimalFormat bytesFormat, double bytesSum) {
		DBModel retModel = srcDBModel;
		if (srcDBModel.size() < dstDBModel.size()) {
			retModel = dstDBModel;
		}
		int size = srcDBModel.size() > dstDBModel.size() ? srcDBModel.size() : dstDBModel.size();
		for (int i = 0; i < size; i++) {
			// 计算源IP总流量
			String srcIpTotalFormat = "0";
			String srcIpPercentage = "0";
			if (dstDBModel.size() > i) {
				double srcIpTotal = Double.parseDouble(srcDBModel.get(i).get("total_bytes"));
				srcIpTotalFormat = bytesFormat.format(srcIpTotal / 1048576);
				double computeSrcIpPer = (srcIpTotal / bytesSum) * 100;
				srcIpPercentage = perFormat.format(computeSrcIpPer);
			}
			retModel.get(i).put("src_ip_total", srcIpTotalFormat);
			retModel.get(i).put("src_ip_percentage", srcIpPercentage);
			
			// 计算目标IP总流量
			String dstIpTotalFormat = "0";
			String dstIpPercentage = "0";
			if (dstDBModel.size() > i) {
				double dstIpTotal = Double.parseDouble(dstDBModel.get(i).get("total_bytes"));
				dstIpTotalFormat = bytesFormat.format(dstIpTotal / 1048576);
				double computeDstIpPer = (dstIpTotal / bytesSum) * 100;
				dstIpPercentage = perFormat.format(computeDstIpPer);
			}
			retModel.get(i).put("dst_ip_total", dstIpTotalFormat);
			retModel.get(i).put("dst_ip_percentage", dstIpPercentage);
		}
		dbModels.put(GenericAction.DATA_DEFAULT, retModel);
	}
}
