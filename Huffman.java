package labs;
	import java.util.*;

	public class Huffman{

	    public static void main(String[] args){
	      
	       FileIO reader = new FileIO();
	       
	       while(true){ //keeps the whole thing looping forever
	    	   
	       String[] sentenceArray =  reader.load("C:\\Users\\peter_000\\Desktop\\Second Year\\CS211\\encoded.txt");
	       String sentence = Arrays.toString(sentenceArray);
	       String binaryString="";      //this stores the string of binary code
	       double freq = 0;
	       
	       
	       for(int i=0; i < sentence.length(); i++){        //go through the sentence
	           int decimalValue = (int)sentence.charAt(i);      //convert to decimal
	           String binaryValue = Integer.toBinaryString(decimalValue);      //convert to binary
	           for(int j=7;j>binaryValue.length();j--){
	               binaryString+="0";           //this loop adds in those pesky leading zeroes
	            }
	           binaryString += binaryValue+" "; //add to the string of binary
	       }
	       System.out.println(binaryString);    //print out the binary
	       
	              
	       int[] array = new int[256];      //an array to store all the frequencies
	       
	       for(int i=0; i < sentence.length(); i++){    //go through the sentence
	           array[(int)sentence.charAt(i)]++;    //increment the appropriate frequencies
	           
	       }
	       
	       
	       PriorityQueue < Tree >  PQ = new PriorityQueue < Tree >() ;  //make a priority queue to hold the forest of trees    
	        
	       
	       for(int i=0; i<array.length; i++){ //go through frequency array
	           if(array[i]>0){ //print out non-zero frequencies - cast to a char
	        	   freq = ((double)array[i]/(double)sentence.length())*100;
	               System.out.println("'"+(char)i+"' has a frequency of " + freq + "%");            
	               Tree myTree = new Tree();    //create a new Tree 
	               myTree.frequency = array[i]; //set the cumulative frequency of that Tree
	               myTree.root=new Node();      //insert the letter as the root node 
	               myTree.root.letter = (char)i;
	               PQ.add(myTree);              //add the Tree into the PQ
	            }
	        }
	        
	        
	        while(PQ.size()>1){             //while there are two or more Trees left in the forest
	            Tree firstTree = PQ.poll(); //get the two trees
	            Tree secondTree = PQ.poll();
	            Tree comboTree = new Tree();    //combine them into a new tree
	            comboTree.frequency=firstTree.frequency+secondTree.frequency;   //add the cumulative frequency of both trees
	            comboTree.root=new Node();        //insert a default root node (or else you get a null pointer exception)
	            comboTree.root.leftChild=firstTree.root;    //the two trees are the left and right children of the combo tree
	            comboTree.root.rightChild=secondTree.root;
	            PQ.add(comboTree);                  //add the combo tree back into the PQ
	        }
	        
	        Tree HuffmanTree = PQ.poll();   //now there's only one tree left - get its codes
	        int totalLength=0;              //keeps track of the length of the new compressed version
	        String theCode;
	        for(int i=0; i<sentence.length(); i++){
	            theCode=HuffmanTree.getCode(sentence.charAt(i));
	            System.out.print(theCode+" ");  //get the code for the letter
	            totalLength+=theCode.length();  //track the length of the solution
	        }
	        //print out all the info
	        System.out.println("\nCompressed size is "+totalLength+" bits / "+sentence.length()*7+" bits = "+(int)((totalLength*100)/(sentence.length()*7))+" %\n");
	        
	        break;
	        }  //keeps the whole thing looping forever 
	    }      
	}


