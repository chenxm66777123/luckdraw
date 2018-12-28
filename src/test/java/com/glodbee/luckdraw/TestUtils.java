package com.glodbee.luckdraw;

import java.net.URLEncoder;

public class TestUtils {

	 
	public static void main(String[] args) {
		String s = URLEncoder.encode("http://luck.beesrv.com:3000/index.html");
		System.out.println(s);
	}
}
