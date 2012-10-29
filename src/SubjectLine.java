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
		theNum="";		//
		if(startPos == -1)
			return null;
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
	public boolean hasCaseNumber(String caseNum){
		if(this.getCaseNumber().equals(caseNum))
			return true;
		else return false;
	}
	public boolean hasProjectName(String projName){
		String mailSubjLine=this.theSubjLine.replaceAll("[^A-Za-z]", "");
		String inputSubjLine=projName.replaceAll("[^A-Za-z]", "");
		if(mailSubjLine.toLowerCase().contains(inputSubjLine.toLowerCase()))
			return true;
		else return false;
	}
public static void main(String args[]){
	SubjectLine sl=new SubjectLine("Sundt Construction, Inc::03824616::- Delivery");
	System.out.println("The case number is :"+sl.getCaseNumber());
}

}
