package com.gm.gmview.project.test;

import java.util.List;

import org.dom4j.Element;

import com.gm.gmview.platform.plugin.BaseProjectPlugin;
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

	public void start() {
		System.out.println("启动插件:" + this.pluginName);
		System.out.println("启动项目:" + this.projectName);

	}

}