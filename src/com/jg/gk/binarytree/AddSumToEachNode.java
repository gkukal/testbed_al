package com.jg.gk.binarytree;

import com.jg.gk.binarytree.BinaryTree.BTreePrinter;

public class AddSumToEachNode {

    public static void main( String[] inputArgs ){
    	BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(5, head);
        head = bt.addNode(8, head);
        head = bt.addNode(4, head);
        head = bt.addNode(3, head);
        BTreePrinter.printNode(head);
        new AddSumToEachNode().sumNodes( head , 0);
        BTreePrinter.printNode(head);
    	
    }
    
    public int sumNodes( Node root , int sumInherited ){
    	
    	//Base condition
    	if( root == null ){
    		return 0;
    	}
    	
    	//
    	int sumFromRightChildren = 0 ;
    	sumFromRightChildren = sumNodes( root.right , 0 );//No sum to be given
    	int valueOfRoot = root.data ;
    	root.data = valueOfRoot + sumFromRightChildren + sumInherited ;
    	int sumFromLeftChildren  = sumNodes( root.left , root.data  );//No sum to be given
    	
    	
    	return root.data;
    	
    }
    
    
    
}
