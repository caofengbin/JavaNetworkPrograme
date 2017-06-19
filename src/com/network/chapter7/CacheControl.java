package com.network.chapter7;

import java.util.Date;
import java.util.Locale;

/**
 * 检查Cache-Control相关的首部字段的方法
 * @author Administrator
 *
 */
public class CacheControl {

	// 缓存指令中定义的相关字段设置1
	private Date maxAge = null;
	private Date sMaxAge = null;
	
	// 缓存指令中定义的相关字段设置2
	private boolean mustRevalidate = false;
	private boolean noCache = false;
	private boolean noStore = false;
	private boolean proxyRevalidate = false;
	private boolean publicCache = false;
	private boolean privateCache = false;
	
	public CacheControl(String s) {
		// default policy
		if(s == null || !s.contains(":")) {
			return;
		}
		
		String value = s.split(":")[1].trim();
	    String[] components = value.split(","); 
	    
	    Date now = new Date();
	    
	    for (String component : components) {
	    	try {
	    		// 遍历缓存控制相关的首部字段，并给予赋值操作
	    		component = component.trim().toLowerCase(Locale.US);
	    		if (component.startsWith("max-age=")) {
	    	          int secondsInTheFuture = Integer.parseInt(component.substring(8));
	    	          maxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
	    	        } else if (component.startsWith("s-maxage=")) {
	    	          int secondsInTheFuture = Integer.parseInt(component.substring(8));
	    	          sMaxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
	    	        } else if (component.equals("must-revalidate")) {
	    	          mustRevalidate = true;
	    	        } else if (component.equals("proxy-revalidate")) {
	    	          proxyRevalidate = true;
	    	        } else if (component.equals("no-cache")) {
	    	          noCache = true;
	    	        } else if (component.equals("public")) {
	    	          publicCache = true;
	    	        } else if (component.equals("private")) {
	    	          privateCache = true;
	    	        }
	    	} catch (RuntimeException ex) {
	            continue;
	        }
	    }
	}
	
}
