/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Fall 2017 
// PROJECT:          P4 Research Geneology
// FILE:             Stack.java
//
// TEAM:    P4 Pair 32
// Authors: Matt P'ng, Jasper Nelson
// Author1: Matt P'ng, mpng@wisc.edu, mpng, 002
// Author2: Jasper Nelson, jnelson27@wisc.edu, jnelson27, 002
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: NA
// 
// Online sources: NA
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * The main program (class) for determining the 
 * closest (lowest) common ancestor for a pair of researchers.
 * 
 * A file with the parent-&gt;child (professor-&gt;student) relationship
 * is read and used to build a GenealogyTree of the relationships.
 * 
 * That tree is then used to find the closest common ancestor.
 * 
 * Required classes include:
 * LinkedList that implements ListADT
 * Stack that implements StackADT (with a reverse method)
 * Queue that implements QueueADT
 * EmptyQueueException
 * GenealogyTree class that used TreeNode objects
 * 
 */
public class Ancestor{

    // Use this STDIN to read input from command line.
    // Don't create new Scanner.
    private static final Scanner STDIN = new Scanner(System.in);

    private static final String MAIN_LOOP_MESSAGE = "(c)heck, (p)rint, (q)uit";
    private static final String UNRECOGNIZED_COMMAND_ERROR_MESSAGE = "Unrecognized command";
    private static final String UNABLE_TO_INITIALIZE = "Unable to initialize Ancestor";
    private static final String INPUT_1_PROMPT = "Please input name 1";
    private static final String INPUT_2_PROMPT = "Please input name 2";
    private static final String NAME_NOT_FOUND_MESSAGE = "Can not find name: ";
    private static final String PROGRAM_USAGE_MESSAGE = "Usage: \njava Ancestor ancestors_data.txt";

    private GenealogyTree g;

    public Ancestor() 
    {
        g = new GenealogyTree();
    }


    /**
     * Given two names, return the lowest common ancestor 
     * as found in the GenealogyTree.
     *
     *<pre>
     * (1) If name does not exist in GenealogyTree, 
     *     print NAME_NOT_FOUND_MESSAGE
     *     and the corresponding name, like "Can not find name: leonhard_euler"
     *
     * (2) If both names are not found in GenealogyTree, 
     *     do step (1) for both name1 and name2.
     *
     * (3) If common ancestor does not exist, return null.
     *</pre>
     *
     * @param name1 of first researcher to find
     * @param name2 of second researcher to find
     * @return the name of the closest (lowest level) common ancestor researcher
     */
    public String lowestCommonAncestor(String name1, String name2)
    {
        // TODO: COMPLETE THIS METHOD
    	StackADT<String> anc1 = null;
    	StackADT<String> anc2 = null;
    	
    	try
    	{
    		anc1 = g.getAncestorStack(name1);
    		// Get the ancestors of name 1
    	}
    	catch(EmptyQueueException e)
    	{
    		
    	}
    	
    	try
    	{
    		anc2 = g.getAncestorStack(name2);
    		// Get the ancestors of name 2
    	}
    	catch(EmptyQueueException e)
    	{
    		
    	}
    	
    	if(anc1==null)
    	{
    		System.out.println(NAME_NOT_FOUND_MESSAGE + name1);
    		// display not found message
    	}
    	if(anc2==null)
    	{
    		System.out.println(NAME_NOT_FOUND_MESSAGE + name2);
    		// display not found message
    	}
    	
    	
    	if(!(anc1==null) && !(anc2==null))
    	{
    		// if neither stack is empty
            // reverse both stacks so that the ancestors can be compared
    		anc1 = anc1.reverse();
    		anc2 = anc2.reverse();
    		String common = "";// stores common ancestor
    		
    		while(!anc1.isEmpty() && !anc2.isEmpty())
    		{
    			String check1 = anc1.pop(); //ancestor of name1
    			String check2 = anc2.pop(); //ancestor of name2
    			if(check1.equals(check2))
    			{
    				//if the ancestor is the same, update common and continue
    				common = check1;
    				continue;
    			}
    			else
    			{
    				//return last common ancestor
    				return common;
    			}
    		}
    		
    		return common;
    	}
    	else
    	{
    		return null;
    		// return null if either stack is empty
    	}
    }

    /** 
     * Handles the main menu loop's check operation.
     * DO NOT CHANGE THIS METHOD
     */
    private void handleCheck(){
        System.out.println(INPUT_1_PROMPT);
        String name1 = STDIN.nextLine();

        System.out.println(INPUT_2_PROMPT);
        String name2 = STDIN.nextLine();

        String result = lowestCommonAncestor(name1, name2);

        if(result != null){
            System.out.println(String.format("Lowest common ancester is %s", result));
        }
    }

    /**
     * The main menu loop
     * DO NOT CHANGE THIS METHOD
     */
    private void mainLoop(){
        String command = "";
        while(!command.equalsIgnoreCase("q")){
            System.out.println(MAIN_LOOP_MESSAGE);
            command = STDIN.nextLine().trim();
            switch(command){
                case "c": handleCheck(); break;
                case "p": g.printTree(); break;
                case "q": break;
                default:
                    System.out.println(UNRECOGNIZED_COMMAND_ERROR_MESSAGE);
            }
        }
    }

    /**
     * Initialize the GenealogyTree with data
     * from the specified file.
     * 
     * @param filename is the name of a file with (professor-&gt;student) research pairs
     * @return true iff if the file was read successfully
     */
    public boolean initialize(String filename){
        // DO NOT CHANGE THIS METHOD
        try {
            g.buildFromFile(filename);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /** 
     * THE MAIN METHOD THAT STARTS THE APPLICATION 
     * DO NOT CHANGE THIS METHOD
     * @param args Command Line arguments used for file name of genealogy data
     */
    public static void main(String[] args) {
        Ancestor a = new Ancestor();
        try {
            if ( ! a.initialize(args[0]) ) {
                System.out.println(UNABLE_TO_INITIALIZE);
                return;
            }
            a.mainLoop();
        } catch( Exception e ) {
            System.out.println(PROGRAM_USAGE_MESSAGE);
        }
    }

}
