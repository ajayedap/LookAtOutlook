package com.aj.lookatoutlook;
import com.pff.*;
import java.util.*;
//From the web...............
public class RootClass {
	int cntr=0;

        public RootClass(String filename) {
                try {
                        PSTFile pstFile = new PSTFile(filename);
                        System.out.println(pstFile.getMessageStore().getDisplayName());
                        //processFolder(pstFile.getRootFolder());
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
                if (folder.getContentCount() > 0) {
                        depth++;                        
                        PSTMessage email = (PSTMessage)folder.getNextChild();
                        while (email != null) {
                                //printDepth();
                                System.out.print("Email: "+email.getSubject()+"<<>>");
                                System.out.println("Case# "+new SubjectLine(email.getSubject()).getCaseNumber());
                                cntr++;
                                if(cntr > 100) System.exit(0);                                
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
                RootClass rc=new RootClass("/home/chinna/test20121024.pst");
                
        }

}