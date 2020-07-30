package com.wickcentral;

/**
 * 
 *
 */
public class App {
	
	public static void main(String[] args) {
		
		System.out.println("Hello World!");
		
		try {
			
			
		} catch (Exception e)  {
			System.err.println("Exception: " + e.getLocalizedMessage());
			e.printStackTrace();
		} finally {
			System.out.println("... ParseFoldersFromFile DONE!");
	    } 
		
	}
	
}
