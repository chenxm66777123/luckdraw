package com.glodbee.luckdraw;

import java.net.URLEncoder;

public class TestUtils {

	 
	public static void main(String[] args) {
		String s = URLEncoder.encode("http://6ff9b899.ngrok.io/luckdraw/getOpenIdByCode");
		System.out.println(s);
	}
}
