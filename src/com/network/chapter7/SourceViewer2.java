package com.network.chapter7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "https://www.oreilly.com/";
		try {
			URL u = new URL(url);
			System.out.println("openConnection");
			HttpURLConnection uc = (HttpURLConnection) u.openConnection();
			uc.connect();
			
			//　获取响应码和响应Message
			int responseCode = uc.getResponseCode();
			String responseMessage = uc.getResponseMessage();
			
			System.out.println("响应码为：" + responseCode);
			System.out.println("响应Message为：" + responseMessage);

			System.out.println();
			
			// 通过下面这个方法返回响应报文的第一行，即整个第一行的完整信息
			System.out.println(uc.getHeaderField(0));
			
			// 遍历输出响应头部字段值
			for (int j = 1; ; j++) {
				String header = uc.getHeaderField(j);
				String key = uc.getHeaderFieldKey(j);
				if (header == null || key == null) break;
				System.out.println(uc.getHeaderFieldKey(j) + ": " + header);
			}
			System.out.println();
			
			// 注意这种try-with-resource的打开文件的方式
			try (InputStream raw = uc.getInputStream()) {
				InputStream buffer = new BufferedInputStream(raw);
				// 将InputStream串联到一个Buffer上
				Reader reader = new InputStreamReader(buffer);
				int c;
				while( (c = reader.read()) != -1) {
					System.out.print((char)c);
				}
			} 
		} catch(MalformedURLException ex) {
			System.out.println(url + " is not a parseable URL");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

}
