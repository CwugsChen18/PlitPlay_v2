package com.plit.googleplay.utils;

import java.io.Closeable;
import java.io.IOException;

public class IOUtils {
	/** 关闭流 */
	public static boolean close(Closeable io) {
		if (io != null) {
			try {
				io.close();
			} catch (IOException e) {
				//LogUtils.logE(e.getMessage());
				e.printStackTrace();
			}
		}
		return true;
	}
}
