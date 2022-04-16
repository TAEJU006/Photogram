package com.cos.photogramstart.util;

public class Script {

	
	public static String back(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');"); // alert 나오게 하고
		sb.append("history.back();"); // page 되돌리게 하기
		sb.append("</script>");
		return sb.toString();
	}
}
