package com.app.practise;

/**
 * @author sdubey6447 - we keep collections of constants in an interface in java
 *         application. FYI, in a large real time project, we had created tons
 *         of interfaces containing variables only as a collections of constants
 */

interface Math {
	public static final double PI = 3.14;
	public static final double ELUERNUMBER = 2.718;
	public static final double SQRT = 1.41421;
}


public class ExampleVariablesInInterface {
	public static void main(String[] args) {
		// Calculate area of circle
		int radious = 2;
		// call interface constant Math.PI
		double area = Math.PI * radious * radious;

		System.out.println("Area of Circle =" + area);

	}
}
