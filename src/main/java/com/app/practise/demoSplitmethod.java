package com.app.practise;

public class demoSplitmethod {
	public static void main(String[] args) {
		String str = "27/05/2020";
		String[] strarry = str.split("/", -2);
		for (String string : strarry) {
			System.out.println(string);
		}
	}
}
