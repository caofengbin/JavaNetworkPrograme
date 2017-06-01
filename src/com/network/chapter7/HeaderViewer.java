package com.network.chapter7;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class HeaderViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 对之前的六个特定的方法进行验证操作
		String url = "https://www.oreilly.com/";
		
		try {
			URL u= new URL(url);
			URLConnection uc = u.openConnection();
			
			System.out.println("Content-Type字段值为：" + uc.getContentType());
			
			if(uc.getContentEncoding() != null) {
				System.out.println("Content-Encoding字段值为：" + uc.getContentEncoding());
			}
			
			if (uc.getDate() != 0) {
				System.out.println("Date字段值为： " + new Date(uc.getDate()));
			}
			
			if (uc.getLastModified() != 0) {
				System.out.println("Last-Modified字段值为： " + uc.getLastModified());
				System.out.println("Last-Modified字段值为： " + new Date(uc.getLastModified()));
			}
			
			if (uc.getExpiration() != 0) {
				System.out.println("Expiration字段值为： " + uc.getExpiration());
				System.out.println("Expiration字段值为： " + new Date(uc.getExpiration()));
			}
			
			System.out.println("Content-Length字段值为： " + uc.getContentLength());
			
			System.out.print(uc.getURL());
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
	}

}
