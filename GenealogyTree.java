import java.util.*;
import java.io.*;

/**
 * A general tree that is can be used to keep 
 * track of parent child relationships between data.
 * 
 * Nodes in a general tree can have multiple children.
 * 
 * This tree is built by reading lines from a file.
 * 
 * To help with program 4, this class also has a method
 * that returns a stack of String (names) that are the
 * ancestors of the specified node.  See getAncestorStack.
 */
public class GenealogyTree{

	public static final String LOAD_GENEALOGY_ERROR_MESSAGE = "Error loading genealogy from file";
	private TreeNode<String> root;

	public GenealogyTree(){
		root = null;
	}

	// Get the root node of the GenealogyTree
	public TreeNode<String> getRoot(){
		return root;
	}

	/**
	 * return a Stack that contains the ancestors of the node with matching data
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all ancestors of the matching data node.
	 *
	 * THIS METHOD CALLS A COMPANION (HELPER) METHOD that is recursive
	 * 
	 * If the top of the stack is not target,
	 * return an empty stack indicating that the target
	 * was not found.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom,
	 * or return an empty stack if the target was not found.
	 */
	public StackADT<String> getAncestorStack(String target) {
		// DO NOT CHANGE THIS METHOD
		StackADT<String> stack = new Stack<String>();
		stack = getAncestorStack(stack,root,target);
		if (stack.peek().equals(target)) {
			return stack;
		}
		return new Stack<String>(); // empty stack
	}

	/**
	 * Perform a pre-order traversal of the current node and 
	 * return a Stack that contains the ancestors of the target node.
	 * 
	 * The root is at the bottom of the stack and the matching node is at the top
	 * and the stack contains all and only ancestors of the matching data node.
	 * 
	 * NOTE: If target data is not found, the stack returned does not have
	 * target at the top.  Be sure to check this in the calling method.
	 *
	 * @param target the data you are trying to find
	 * @return a stack with the target data node at top and the root at the bottom 
	 * or an empty stack if target is not found
	 */
	private StackADT<String> getAncestorStack(StackADT<String> st, TreeNode<String> curr, String target) 
	{
		if(curr != null)
		{
			st.push(curr.getData());
			if(st.peek().equals(target))
			{
				return st;
			}
			else
			{
				ListADT<TreeNode<String>> kids = curr.getChildren();
				Iterator<TreeNode<String>> itr = kids.iterator();
			
				while(itr.hasNext())
				{
					getAncestorStack(st, itr.next(), target);
					if(st.peek().equals(target))
					{
						return st;
					}
				}
				st.pop();
			}
			
		}
		else
		{
			return st;
		}
		return st;
	}

	
	
	/**
	 * Load a tree from file.
	 *
	 * If there are IOException when loading the tree from file, 
	 * print LOAD_GENEALOGY_ERROR_MESSAGE 
	 * and throw the IOException.
	 *
	 * All the lines in the file "parent -> children" relationships.
	 *
	 * The relationships are listed in a pre-order traversal order starting from root.
	 *
	 * For example, for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The input file must follow this form:
	 * a -> b
	 * a -> c
	 * a -> d
	 * b -> e
	 * d -> f
	 * d -> g
	 *
	 * Note: all lines of a file must contain a relationship to be a valid format.
	 * Blank lines will cause exceptions.
	 * 
	 * Pseudocode for the work done by this method:
	 * 
	 * 	// Create a queue, add each new node to the queue
	 *	// create a Scanner connect to the file
	 *  // for each line of the file
	 *  	// read the line
	 *      // parse the line into parent and child
	 *
	 *      // if root is null
	 *      	// create the root node
	 *          // add the root's first child
	 *          // add the root and child to the queue
	 *
	 *      // else Construct other TreeNode
	 *      	// while queue is not empty
	 *          	// get next node from queue without removing it from queue
	 *              // if "front" node matches the parent
	 *              	// create a TreeNode for the child
	 *                  // add the child node to the current "front" node (its parent)
	 *                  // add the child to the queue
	 *                  // break out of the loop
	 *              	// else dequeue the front node 
	 *
	 *  // catch IO exceptions, display error message and rethrow the exception
	 *	// close the file scanner
	 * 
	 */
	public void buildFromFile(String filename) throws IOException{
        // TODO: COMPLETE THIS METHOD

		// Create a queue, add each new node to the queue
		Queue<TreeNode<String>> q = new Queue<TreeNode<String>>();
		
		
		try{
			File file = new File(filename);
			Scanner scnr = new Scanner(file);// create a Scanner connect to the file

			
			while (scnr.hasNextLine()){ // for each line of the file
				String line = scnr.nextLine().trim();	// read the line

				try{
					
					
					String[] parts = line.split("->"); 	// parse the line into parent and child

					String parent = parts[0].trim();
					String child = parts[1].trim();

					if (getRoot() == null){		// if root is null

						TreeNode<String> newNode = new TreeNode<String>(parent); // create the root
						TreeNode<String> newChild = new TreeNode<String>(child);
						newNode.addChild(newChild);		// add its first child
						
						root = newNode;

						
						q.enqueue(newNode);	// add the root and child to the queue
						q.enqueue(newChild);
						
						//System.out.println("newNode is " + newNode.getData());
						//System.out.println("newChild is " + newChild.getData());
					}
					
					else { 	// else Construct other TreeNode

												
						while (!q.isEmpty()){ // while queue is not empty
							TreeNode<String> front = q.element(); // get next node from queue without removing it from queue
							
//							System.out.println("front is " + front.getData());
//							System.out.println("parent is " + parent);
							
							if (front.getData().equals(parent)){// if "front" node matches the parent
								TreeNode<String> childNode = new TreeNode<String>(child);// create a TreeNode for the child
								front.addChild(childNode);// add the child node to the current "front" node (its parent)

								q.enqueue(childNode);// add the child to the queue
								
//								System.out.println("newChild is " + childNode.getData() +"\n");
								
								break; // break out of the loop
							}
													
							else {		// else dequeue the front node 
								q.dequeue();
							}
						}	
		
					}
										
				}
				catch (Exception e){
					continue;
				}
								
			}
			scnr.close(); // close the file scanner
		}
		
		catch (FileNotFoundException e){ // catch IO exceptions, display error message and rethrow the exception
			System.out.println(LOAD_GENEALOGY_ERROR_MESSAGE);
		}
		

		


	}            
	/**
	 * Display the contents of the tree in a horizontal tree form.
	 * 
	 * This method is a private recursive helper method for the
	 * printTree() method.
	 * 
	 * It uses the indentation levels to indicate how many 
	 * dots (two per each level) to print for the node
	 * 
	 * @param current node to print
	 * @param indent_count indicates how many dots .. to print for the current level
	 * @param indent_str indicates string of characters precede each print level
	 */
	private void printTreeWithIndent(TreeNode<String> current, int indent_count, String indent_str)
	{
        if(indent_count == 0)
        {
        	System.out.println(current.getData());
        }
        else
        {
        	System.out.print(indent_str);
        	printTreeWithIndent(current, indent_count - 1, indent_str);
        }   
	}

	/**
	 * Print a tree with indent.
	 *
	 * You should use pre-order to print a tree, which means:
	 * (1) Print the data at current node
	 * (2) For all children nodes of current node,
	 *       recursively use pre-order to print children nodes.
	 *
	 * Each line of output represents a node, use indent (number of spaces before node data)
	 * to indicate which level the current node belongs to.
	 * For root node (at level 0), use 0 spaces.
	 * For nodes at other levels, add 2 spaces of indent each level.
	 *
	 * Like for the following tree:
	 *     a
	 *  /  |  \
	 *  b  c  d
	 *  |     | \
	 *  e     f g
	 *
	 * The displayed output should be:
	 * <pre>
	 *  a
	 *  ..b
	 *  ....e
	 *  ..c
	 *  ..d
	 *  ....f
	 *  ....g
	 * </pre>
	 */
	public void printTree() 
	{
		recursivePrint(root, 0);
	}
	
	private void recursivePrint(TreeNode<String> curr, int height)
	{
		String indent = "..";
		ListADT<TreeNode<String>> kids = curr.getChildren();
		Iterator<TreeNode<String>> itr = kids.iterator();
		printTreeWithIndent(curr, height, indent);
		if(kids.isEmpty())
		{
			return;
		}
		
		while(itr.hasNext())
		{
			recursivePrint(itr.next(), height + 1);
		}
		
	}

}
