package com.network.chapter2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 演示一个比较推荐的打开与关闭流的方法
 * @author Administrator
 *
 */
public class TestOutStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStream out = null;
		try {
			out = new FileOutputStream("/tmp/data.txt");
		} catch(IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(IOException ex) {
					System.err.println(ex.getMessage());
				} 
			}
		}
	}

}
