package com.network.chapter2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 测试使用书写器的方法
 * @author Administrator
 *
 */
public class TestWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OutputStreamWriter wr;
		try {
			wr = new OutputStreamWriter(new FileOutputStream("/data.txt"));
			System.out.println(wr.getEncoding());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
