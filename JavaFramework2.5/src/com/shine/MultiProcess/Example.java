package com.shine.MultiProcess;

public class Example {

	/**
	 * 基础例子
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MultiProcessManager.getManager().setJvmPath(
				"C:\\Program Files\\Java\\jre6\\bin\\java");
		MultiProcessManager
				.getManager()
				.addProcessByJar("test",
						"C:\\Program Files\\Java\\jakarta-jmeter-2.3.4\\bin\\ApacheJMeter.jar");
		MultiProcessManager.getManager().startProcess("test");
		
		
	}
}
