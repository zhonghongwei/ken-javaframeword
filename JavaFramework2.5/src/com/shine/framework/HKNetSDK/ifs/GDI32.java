package com.shine.framework.HKNetSDK.ifs;

import com.sun.jna.Native;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.examples.win32.W32API.HANDLE;
import com.sun.jna.examples.win32.W32API.HDC;

//windows gdi接口,gdi32.dll in system32 folder, 在设置遮挡区域,移动侦测区域等情况下使用
public interface GDI32 extends W32API {
	GDI32 INSTANCE = (GDI32) Native.loadLibrary("gdi32", GDI32.class,
			DEFAULT_OPTIONS);

	public static final int TRANSPARENT = 1;

	int SetBkMode(HDC hdc, int i);

	HANDLE CreateSolidBrush(int icolor);
}