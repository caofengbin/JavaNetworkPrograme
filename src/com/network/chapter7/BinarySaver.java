package com.network.chapter7;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BinarySaver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "https://www.oreilly.com/";
		try {
			URL root = new URL(url);
			saveBinaryFile(root);
		} catch (MalformedURLException ex) {
			System.err.println(url + " is not URL I understand.");
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	private static void saveBinaryFile(URL url) throws IOException {
		URLConnection uc = url.openConnection();
		
		// 获取内容的类型以及长度字段
		String contentType = uc.getContentType();
		int contentLength = uc.getContentLength();
		
		if(contentType.startsWith("text/") || contentLength == -1) {
			throw new IOException(url + " is not a binary file.");
		}
		
		try (InputStream raw = uc.getInputStream()) {
			InputStream in = new BufferedInputStream(raw);
			byte[] data = new byte[contentLength];
			
			int offSet = 0;
			while(offSet < contentLength) {
				int byteRead = in.read(data, offSet, data.length - offSet);
				if(byteRead == -1) {
					break;
				}
				offSet += byteRead;
			}
			
			// 读取操作完成，检查合法性问题
			if(offSet != contentLength) {
				// 读取到的字节数不合法
				throw new IOException("Only read " + offSet
						+ " bytes; Expected " + contentLength + " bytes");
			}
			
			String filename = url.getFile();
			filename = filename.substring(filename.lastIndexOf('/') + 1);
			try (FileOutputStream fout = new FileOutputStream(filename)) {
				fout.write(data);
				fout.flush();
			}
		}
	}
}
