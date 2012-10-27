package com.aj.lookatoutlook;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;
//From the web...............
public class RootClass {
	int cntr=0;

        public RootClass(String filename) {
                try {
                        PSTFile pstFile = new PSTFile(filename);
                        System.out.println(pstFile.getMessageStore().getDisplayName());
                        processFolder(pstFile.getRootFolder());
                } catch (Exception err) {
                        err.printStackTrace();
                }
        } 

        int depth = -1;
        public void processFolder(PSTFolder folder)
                        throws PSTException, java.io.IOException
        {
                depth++;
                // the root folder doesn't have a display name
                if (depth > 0) {
                        printDepth();
                        System.out.println(folder.getDisplayName());
                }

                // go through the folders...
                if (folder.hasSubfolders()) {
                        Vector<PSTFolder> childFolders = folder.getSubFolders();
                        for (PSTFolder childFolder : childFolders) {
                                processFolder(childFolder);
                        }
                }

                // and now the emails for this folder
                if (folder.getContentCount() > 0 && (folder.getDisplayName().equalsIgnoreCase("inbox")||folder.getDisplayName().equalsIgnoreCase("sent items"))) {
                        depth++;                        
                        PSTMessage email = (PSTMessage)folder.getNextChild();
                        while (email != null) {
                                //printDepth();
                                //System.out.print("Email: "+email.getSubject()+"<<>>");
                        		String caseNum=new SubjectLine(email.getSubject()).getCaseNumber();
                                if(caseNum.equals("03864197")){
                                Date dateTime=email.getMessageDeliveryTime();
                                SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");                                
                                System.out.println(date_format.format(dateTime));
                                System.out.println("    "+email.getSubject());
                                }
                                cntr++;
                                //if(cntr > 100) System.exit(0);                                
                                email = (PSTMessage)folder.getNextChild();
                        }
                        depth--;
                }
                depth--;
        }

        public void printDepth() {
                for (int x = 0; x < depth-1; x++) {
                        System.out.print(" | ");
                }
                System.out.print(" |- ");
 
        }
        public static void main(String[] args)
        {
        		long startTime = System.currentTimeMillis();
                RootClass rc=new RootClass("/home/chinna/test20121024.pst");
                long endTime = System.currentTimeMillis();
                System.out.println("\n\n----Time taken :"+(endTime - startTime)/1000+" seconds.");
                
        }

}