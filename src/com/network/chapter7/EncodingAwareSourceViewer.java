package com.network.chapter7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

	/**
	 * 使用正确的字符集下载一个Web页面的方式
	 */
	public static void main(String[] args) {
		String url = "https://www.oreilly.com/";
		try {
			String encoding = "ISO-8859-1";
			URL u = new URL(url);
			URLConnection uc = u.openConnection();
			
			// 获取contentType
			String contentType = uc.getContentType();
			// 获取编码方式的一种非常推荐的做法
			int encodingStart = contentType.indexOf("charset=");
			if(encodingStart != -1) {
				encoding = contentType.substring(encodingStart + 8); 
			}
			// encoding即为服务器返回的指定编码格式
			InputStream in = new BufferedInputStream(uc.getInputStream());
			Reader r = new InputStreamReader(in, encoding);
			
			int c;
			while( (c = r.read()) != -1) {
				System.out.print((char) c);
			}
			
			r.close();
		} catch (MalformedURLException ex) {
			System.err.println(args[0] + " is not a parseable URL");
		} catch (UnsupportedEncodingException ex) {
			System.err.println(
					"Server sent an encoding Java does not support: " + ex.getMessage());
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
