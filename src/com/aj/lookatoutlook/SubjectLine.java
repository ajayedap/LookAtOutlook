package com.aj.lookatoutlook;

public class SubjectLine {
	String theSubjLine;
	public SubjectLine(String val){
		this.theSubjLine=val;
	}
	
	public String getCaseNumber(){
		String theNum="";
		int startPos=-1;
		for(int i=0;i<theSubjLine.length()-6;i++){
			theNum=theSubjLine.substring(i,i+6);
			if(isNumber(theNum)){
				startPos=i;
				break;
			}			
		}
		theNum="";		
		if(startPos == -1)
			return "caseNumNotFound";
		for(int i=startPos;i<theSubjLine.length()-9;i++){
			char c=theSubjLine.charAt(i);			
			if(Character.isDigit(c)){
				theNum+=c;				
			}
			else break;
		}
		return theNum;
	}
	
	public boolean isNumber(String input){
		    try  
		    {  
		       Integer.parseInt( input );  
		       return true;  
		    }  
		    catch( Exception e)  
		    {  
		       return false;  
		    } 
	}
public static void main(String args[]){
	SubjectLine sl=new SubjectLine("Sundt Construction, Inc::03824616::- Delivery");
	System.out.println("The case number is :"+sl.getCaseNumber());
}

}
