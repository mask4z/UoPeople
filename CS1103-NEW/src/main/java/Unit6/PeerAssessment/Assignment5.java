package Unit6.PeerAssessment;

import java.util.Scanner;
import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.lang.Object;
import javax.swing.JFileChooser;

//Set Methods
//For this lab, you will need to use some of the methods 
//that are defined in the Set interface. 
//Recall that if set is a Set, then the following methods are defined:
//set.size() -- Returns the number of items in the set.
//set.add(item) -- Adds the item to the set, if it is not already there.
//set.contains(item) -- Check whether the set contains the item.
//set.isEmpty() -- Check whether the set is empty.


public class Assignment5 {
	
    

	
	public static void main(String[] args) throws FileNotFoundException {
	
		
		HashSet<String> MySet = new HashSet<String>();
		
		
		
		
//Reading a Dictionary:
		
	    try {
	    	Scanner filein = new Scanner(getInputFileNameFromUser());
		    
	    while (filein.hasNext()) {
	    	 String tk = filein.next() ;
	    	 MySet.add(tk.toLowerCase()); 	 
	    }
	    
	    }   
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
	
	    System.out.print(MySet.size()+ "\n");// got the size of set = 72875.
	    
	    
	    
//	    checking the Words in a File:
	    
	    File FileInToCheck = getInputFileNameFromUser();
	  
	    if (FileInToCheck != null) {
	    	Scanner Checkfilein = new Scanner(FileInToCheck);
	    	String testWord;
	    		Checkfilein.useDelimiter("[^a-zA-Z]+");
	    		
	    		while (Checkfilein.hasNext()) {	
	    			testWord = Checkfilein.next().toLowerCase();
	    			if (MySet.contains(testWord) == false  ) {
//	    				System.out.print( testWord + "\n" );
	    				System.out.print(testWord+": " + corrections(testWord, MySet)+ "\n");
	    				
	    			}
	    			
	    		}
	    } 
	    else {
	    	//if FileInToChech (input file) = null, do nothing
	    }
	    
	    
	    
	}
	    
	
	
	
	   /**
     * Lets the user select an input file using a standard file
     * selection dialog box.  If the user cancels the dialog
     * without selecting a file, the return value is null.
     */
	
    static File getInputFileNameFromUser() {
       JFileChooser fileDialog = new JFileChooser();
       fileDialog.setDialogTitle("Select File for Input");
       int option = fileDialog.showOpenDialog(null);
       if (option != JFileChooser.APPROVE_OPTION)
          return null;
       else
          return fileDialog.getSelectedFile();
    }
    
    static TreeSet corrections(String badWord, HashSet dictionary){
    	TreeSet<String> NewTreeSet = new TreeSet<String>() ;
    	char charTmp;
    	StringBuilder sb = new StringBuilder(badWord);




    	//case1 : Delete any one of the letters from the misspelled word. 
    	for (int i = 0; i < badWord.length(); i++) {   		
			sb.deleteCharAt(i);
    		for (char c ='a' ; c <= 'z'; c++) {
    			String newStr = sb.toString();
    			if (dictionary.contains(newStr) ) {
       			 NewTreeSet.add(newStr);
       			}
    		}	
    		
    		//initialize the badWord after loop.	
    		sb.insert(i, badWord.charAt(i) );
    	}

    	
    	//case2 : Change any letter in the misspelled word to any other letter. 
    	for (int i = 0; i < badWord.length(); i++) {

    		for (char c ='a' ; c <= 'z'; c++) {
    			sb.setCharAt(i, c);
    			String newStr = sb.toString();
    			if (dictionary.contains(newStr) ) {
    			 NewTreeSet.add(newStr);
    			}
    			
    		//initialize the badWord after loop.	
    		sb.setCharAt(i, badWord.charAt(i) );
    		}        	  		
    	}    	
    	
    	//case3 : Insert any letter at any point in the misspelled word. 
    	for (int i = 0; i < badWord.length()+1; i++) {
			sb.insert(i, "a");    		
    		for (char c ='a' ; c <= 'z'; c++) {
    			sb.setCharAt(i, c);
    			String newStr = sb.toString();
    			if (dictionary.contains(newStr) ) {
       			 NewTreeSet.add(newStr);
       			}
    		}    			
    		//initialize the badWord after loop.	
    		sb.deleteCharAt(i);
    	}   	
    	
    	
    	//case4 :Swap any two neighboring characters in the misspelled word. 
    	
    	for (int i = 0; i < badWord.length()-1; i++) {   		
			charTmp = sb.charAt(i+1);
			sb.deleteCharAt(i+1);

			sb.insert(i, charTmp );
    		
			String newStr = sb.toString();
    		if (dictionary.contains(newStr) ) {
    			NewTreeSet.add(newStr);
       		}	
    		
    		//initialize the badWord after loop.	
    		sb.deleteCharAt(i);
    		sb.insert(i+1, charTmp);
    		
    	}
    	
    	//case5 : Insert a space at any point in the misspelled word 
    	
    	for (int i = 1; i < badWord.length(); i++) {
			sb.insert(i, " ");    	
			
			
				if (dictionary.contains(sb.substring(0, i ) ) && 
					dictionary.contains(sb.substring(i+1, badWord.length()+1 ) )){
       			 	NewTreeSet.add(sb.substring(0, i));
       			 	NewTreeSet.add( sb.substring(i+1, badWord.length()+1 )) ;
       			}
    		    			
    		//initialize the badWord after loop.	
    		sb.deleteCharAt(i);
    	}
    	
    	//case6 : no suggestions
    	if (NewTreeSet.size() == 0) {
    		NewTreeSet.add("(no suggestions)");
    	}
    	
    	return NewTreeSet;
    	
    }	


    
    
    
    
    
}