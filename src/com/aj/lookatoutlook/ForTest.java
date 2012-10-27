package com.aj.lookatoutlook;

public class ForTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String mailSubjLine="Harley-Davidson Motor Co Inc:03824069::- Pre Match Proctab".replaceAll("[^A-Za-z]", "");
		String inputSubjLine="MoToR".replaceAll("[^A-Za-z]", "");
		if(mailSubjLine.toLowerCase().contains(inputSubjLine.toLowerCase()))
			System.out.println("Found");
		else System.out.println("Not Found");

	}

}
