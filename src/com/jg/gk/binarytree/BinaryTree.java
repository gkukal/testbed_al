package com.jg.gk.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 07/07/2014 
 * @author tusroy
 * 
 * Youtube link - https://youtu.be/bmaeYtlO2OE
 * Youtube link - https://youtu.be/_SiwrPXG9-g
 * Youtube link - https://youtu.be/NA8B84DZYSA
 *
 */
class NodeRef{
    Node node;
}

enum Color{
    RED,
    BLACK
}

class Node{
    Node left;
    Node right;
    Node next;
    int data;
    int lis;
    int height;
    int size;
    Color color;
    
    public static Node newNode(int data){
        Node n = new Node();
        n.left = null;
        n.right = null;
        n.data = data;
        n.lis = -1;
        n.height = 1;
        n.size = 1;
        n.color = Color.RED;
        return n;
    }
}

public class BinaryTree {
    public Node addNode(int data, Node head){
        Node tempHead = head;
        Node n = Node.newNode(data);
        if(head == null){
            head = n;
            return head;
        }
        Node prev = null;
        while(head != null){
            prev = head;
            if(head.data < data){
                head = head.right;
            }else{
                head = head.left;
            }
        }
        if(prev.data < data){
            prev.right = n;
        }else{
            prev.left = n;
        }
        return tempHead;
    }
    
    class IntegerRef{
        int height;
    }
    
    public int height(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight  = height(root.left);
        int rightHeight = height(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(5, head);
        head = bt.addNode(7, head);
        head = bt.addNode(19, head);
        head = bt.addNode(20, head);
        head = bt.addNode(-1, head);
        head = bt.addNode(21, head);
        System.out.println(bt.height(head));
        BTreePrinter.printNode(head);
        
    }
    

    public static class BTreePrinter {

    	    public static <T extends Comparable<?>> void printNode(Node root) {
    	        int maxLevel = BTreePrinter.maxLevel(root);

    	        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    	    }

    	    private static <T extends Comparable<?>> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
    	        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
    	            return;

    	        int floor = maxLevel - level;
    	        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
    	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
    	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    	        BTreePrinter.printWhitespaces(firstSpaces);

    	        List<Node> newNodes = new ArrayList<Node>();
    	        for (Node node : nodes) {
    	            if (node != null) {
    	                System.out.print(node.data);
    	                newNodes.add(node.left);
    	                newNodes.add(node.right);
    	            } else {
    	                newNodes.add(null);
    	                newNodes.add(null);
    	                System.out.print(" ");
    	            }

    	            BTreePrinter.printWhitespaces(betweenSpaces);
    	        }
    	        System.out.println("");

    	        for (int i = 1; i <= endgeLines; i++) {
    	            for (int j = 0; j < nodes.size(); j++) {
    	                BTreePrinter.printWhitespaces(firstSpaces - i);
    	                if (nodes.get(j) == null) {
    	                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
    	                    continue;
    	                }

    	                if (nodes.get(j).left != null)
    	                    System.out.print("/");
    	                else
    	                    BTreePrinter.printWhitespaces(1);

    	                BTreePrinter.printWhitespaces(i + i - 1);

    	                if (nodes.get(j).right != null)
    	                    System.out.print("\\");
    	                else
    	                    BTreePrinter.printWhitespaces(1);

    	                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
    	            }

    	            System.out.println("");
    	        }

    	        printNodeInternal(newNodes, level + 1, maxLevel);
    	    }

    	    private static void printWhitespaces(int count) {
    	        for (int i = 0; i < count; i++)
    	            System.out.print(" ");
    	    }

    	    private static <T extends Comparable<?>> int maxLevel(Node node) {
    	        if (node == null)
    	            return 0;

    	        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    	    }

    	    private static <T> boolean isAllElementsNull(List<T> list) {
    	        for (Object object : list) {
    	            if (object != null)
    	                return false;
    	        }

    	        return true;
    	    }

    	}
    }

