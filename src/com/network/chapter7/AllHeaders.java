package com.network.chapter7;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AllHeaders {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "https://www.oreilly.com/";
		
		try {
			URL u = new URL(url);
			
			System.out.println("发送请求之前：");
			
			URLConnection uc = u.openConnection();
			
			// 设置首部字段的方法
			uc.addRequestProperty("Cookie", "cookie-value1");
			uc.addRequestProperty("Cookie", "cookie-value2");
			uc.addRequestProperty("Cookie", "cookie-value3");
			
			uc.addRequestProperty("Accept", "text/html");
			uc.addRequestProperty("Accept", "application/xhtml+xml");
			uc.addRequestProperty("Accept", "application/xml");
			uc.addRequestProperty("Accept", "new");
			
			uc.addRequestProperty("Expect", "100-continue");
			
			Map map = uc.getRequestProperties();  
            Set set = map.entrySet();  
            Iterator iterator = set.iterator();  
            while (iterator.hasNext()) {  
                System.out.println(iterator.next());  
            }  
            
            System.out.println();
			System.out.println();
			
			for(int j = 1 ; ; j++) {
				String header = uc.getHeaderField(j);
				if(header == null) {
					break;
				}
				// 输出获取到的首部字段值
				System.out.println(uc.getHeaderFieldKey(j) + ":" + header);
			}
			
			System.out.println();
			System.out.println();
			
			// 通过传入指定的首部字段名称获取相应的值
			System.out.println(uc.getHeaderField("Date"));
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
