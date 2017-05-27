package com.network.chapter2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestInputStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new FileInputStream("/tmp/data.txt");
			int byteRead = 0;
			int byteToRead = 1024;
			byte[] input = new byte[byteToRead];
			while(byteRead < byteToRead) {
				int result = in.read(input, byteRead, byteToRead - byteRead);
				if(result == -1) {
					break;
				}
				byteRead += result;
			}
		} catch(IOException ex) {
			System.err.println(ex.getMessage());
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(IOException ex) {
					System.err.println(ex.getMessage());
				} 
			}
		}
	}

}
