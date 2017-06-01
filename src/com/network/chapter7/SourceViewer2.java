package com.network.chapter7;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "https://www.oreilly.com/";
		try {
			URL u = new URL(url);
			System.out.println("openConnection");
			URLConnection uc = u.openConnection();
			uc.connect();
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
