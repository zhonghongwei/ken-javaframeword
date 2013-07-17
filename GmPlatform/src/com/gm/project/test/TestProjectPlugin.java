package com.gm.project.test;

import java.util.List;

import org.dom4j.Element;

import com.gm.framework.config.StrutsFilterDispatcher;
import com.gm.gmPlatform.GmPlatformManager;
import com.gm.gmPlatform.plugin.BaseProjectPlugin;
import com.shine.DBUtil.DBUtil;
import com.shine.framework.core.util.FileUtil;
import com.shine.framework.core.util.XmlUitl;

public class TestProjectPlugin extends BaseProjectPlugin {

	public void regist() {
		try {
			// 加载插件名称
			List<Element> list = XmlUitl.getAllTag(XmlUitl
					.getFileDocument(getClass()
							.getResource("config/config.xml").getPath()
							.replace("%20", " ")), "pluginName");
			if (list.size() == 1)
				this.pluginName = list.get(0).getText();
			list.clear();
			list = null;

			// 加载项目名称
			list = XmlUitl.getAllTag(XmlUitl.getFileDocument(getClass()
					.getResource("config/config.xml").getPath().replace("%20",
							" ")), "projectName");
			if (list.size() == 1)
				this.projectName = list.get(0).getText();
			list.clear();
			list = null;

			this.pluginType = "project";

			// 注册mvc
			StrutsFilterDispatcher.registerXML(getClass().getResource(
					"config/testProjectStruts.xml").getPath());

			// 加载数据医院
			loadDB();

			// 加载功能插件
			loadFunctionPlugins();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		// TODO Auto-generated method stub

	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public void loadFunctionPlugins() {
		// GmPlatformManager.getManager().loadFunctionPlugin(
		// "com.gm.gmview.functions.ZfjlyManager.ZfjlyPluginPlugin");
	}

	public void start() {
		System.out.println("启动项目:" + this.projectName);
	}

	/**
	 * 加载项目数据源
	 */
	public void loadDB() {
		if (getClass().getResource("config/dbXml.xml") != null) {
			DBUtil.getInstance().init(
					getClass().getResource("config/dbXml.xml").getPath()
							.replace("%20", " "));
			System.out.println("加载"+this.projectName+"项目数据源完成.........");
		}
	}
}
