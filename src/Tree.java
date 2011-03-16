/**
 * A class which contains a Binary Search Tree.
 * 
 * @author Björn Tegelund
 * @version 2011.02.15
 */
public class Tree<T extends Comparable<T>> {
	/**
	 * Nodes are used to represent "branches" in the tree.
	 * Each node holds a reference to the two nodes below it, one node which is to the left and one node which is to the right.
	 * It also contains some data which has been associated with it.
	 */
	private class Node {
		Node left;
		Node right;
		T data;
		
		/**
		 * Creates a new Node for use in the Tree.
		 * @param data The data to be stored in this node.
		 */
		public Node(T data) {
			this.data = data;
		}
	}
	
	private int depth;
	private int size;
	private Node root;
	static private StringBuilder sb;
	
	/**
	 * Creates a new empty tree.
	 */
	public Tree() {
		depth = 0;
		size = 0;
	}
	
	/**
	 * Checks if data is contained within the tree.
	 * @param data The data to compare to the values of the nodes.
	 */
	public boolean contains(T data) {
		Node node = root;
		int comparison;
		while(node != null) {
			comparison = data.compareTo(node.data);
			if(comparison < 0) {
				node = node.left;
			}
			else if(comparison > 0) {
				node = node.right;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a new node containing data to the tree.
	 * This method also keeps track of the number of elements, leaves and depth of the tree on its own, 
	 * making reevaluateLeaves, reevaluateSize and reevaluateDepth practically obsolete.
	 * @param data The data which should be added to the tree.
	 */
	public boolean add(T data) {
		if(root == null) {
			root = new Node(data);
		}
		
		Node node = root;
		int comparison;
		int i = 1;
		
		for(; i <= depth; i++) {
			comparison = data.compareTo(node.data);
			if(comparison < 0) {
				if(node.left == null) {
					node.left = new Node(data);
					break;
				}
				else
					node = node.left;
			}
			else if(comparison > 0) {
				if(node.right == null) {
					node.right = new Node(data);
					break;
				}
				else
					node = node.right;
			}
			else
				return false; //data == node.data, element already exists
		}
		
		if(i+1 > depth) {
			depth++;
		}
		size++;
		return true;
	}
	
	/**
	 * Returns the tree as a list of sorted values.
	 * Format is: (lowest value, second lowest value, ..., highest value)
	 * @return The tree as a list of sorted values.
	 */
	@Override
	public String toString() {
		sb = new StringBuilder();
		sb.append('(');
		if(root == null) {
			return "()";
		}
		inorder(root);
		sb.delete(sb.length()-2, sb.length());
		sb.append(')');
		return sb.toString();
	}
	
	/**
	 * Private help method for toString which orders all the values in the tree.
	 */
	private void inorder(Node node) {
		if(node == null) {
		}
		else {
			inorder(node.left);
			sb.append(node.data);
			sb.append(", ");
			inorder(node.right);
		}
	}
	
	/**
	 * Returns the size of the tree (number of elements).
	 * @return The size of the tree.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the depth of the tree.
	 * @return The depth of the tree.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * Obsolete method written as an exercise.
	 * 
	 * Reevaluates the depth of the tree.
	 * @return The depth of the tree.
	 */
	public int reevaluateDepth() {
		if(root == null)
			return 0;
		return recursiveDepth(root, 1);
	}
	
	/**
	 * Private help method used by reevaluateDepth which recursively goes through the entire tree and returns the highest depth found.
	 * @param node The current node where the algorithm looks for children.
	 * @param currentDepth The current depth of this branch.
	 * @return The highest depth found.
	 */
	private int recursiveDepth(Node node, int currentDepth) {
		if(node == null)
			return currentDepth-1;
		
		int leftDepth = recursiveDepth(node.left, currentDepth+1);
		int rightDepth = recursiveDepth(node.right, currentDepth+1);
		return leftDepth > rightDepth ? leftDepth : rightDepth;
	}
	
	/**
	 * Reevaluates the number of leaves (nodes that only have null as children) in the tree.
	 * @return The number of leaves in the tree.
	 */
	public int getLeaves() {
		if(root == null)
			return 0;
		return recursiveLeaves(root);
	}
	
	/**
	 * Private help method used by reevaluateLeaves to find the current number of leaves in the tree.
	 * @param node The current node where the algorithm looks for leaves.
	 * @return The number of leaves in the tree.
	 */
	private int recursiveLeaves(Node node) {
		if(node == null)
			return 0;
		if(node.left == null && node.right == null)
			return 1;
		return recursiveLeaves(node.right) + recursiveLeaves(node.left);
	}
}
