import java.util.Scanner;

class Node {
	Node parent;
	Node right;
	Node left;
	int value;
	int nodesBelow;
}

public class BinarySearchTree {

	Node root;

	void insert(int val) {
		Node pos = parentSearchforInsertion(val, root);
		if (pos == null) {
			root = new Node();
			root.value = val;
			incNodeCounter(root);
		} else {
			if (pos.value >= val) {
				pos.left = new Node();
				pos.left.parent = pos;
				pos.left.value = val;
				incNodeCounter(pos.left);
			} else {
				pos.right = new Node();
				pos.right.parent = pos;
				pos.right.value = val;
				incNodeCounter(pos.right);
			}
		}
	}

	void incNodeCounter(Node n) {
		n.nodesBelow++;
		Node p = n.parent;
		while (p != null) {
			p.nodesBelow++;
			p = p.parent;
		}
	}

	void decNodeCounter(Node n) {
		n.nodesBelow--;
		Node p = n.parent;
		while (p != null) {
			p.nodesBelow--;
			p = p.parent;
		}
	}

	void delete(int val) {
		Node pos = parentSearchforDeletion(val, root);
		if (pos != null) {
			if (pos.left != null && pos.left.value == val) {
				Node lt = deleteNode(pos.left);
				pos.left = lt;
				if (lt != null) {
					lt.parent = pos;
				}
			} else if(pos.right!=null && pos.right.value == val){
				Node rt = deleteNode(pos.right);
				pos.right = rt;
				if (rt != null) {
					rt.parent = pos;
				}
			} else{
				Node r = deleteNode(root);
				root = r;
				if(r!=null){
					r.parent=null;
				}
			}
			decNodeCounter(pos);
		}
	}

	Node deleteNode(Node del) {
		Node rt = del.right;
		Node lt = del.left;
		Node p;
		if (rt == null) {
			p = lt;
		} else if (lt == null) {
			p = rt;
		} else {
			p = rt;
			Node p1 = rt;
			while (p1.left != null) {
				p1 = p1.left;
			}
			p1.left = lt;
			lt.parent = p1;
			while(p1!=rt.parent){
				p1.nodesBelow += lt.nodesBelow;
				p1 = p1.parent;
			}
		}
		return p;
	}

	Node search(int val, Node start) {
		if (start == null)
			;
		else if (start.value > val)
			return search(val, start.left);
		else if (start.value < val)
			return search(val, start.right);
		return start;
	}

	Node parentSearchforInsertion(int val, Node start) {
		if (start == null)
			;
		else if (start.value >= val) {
			if (start.left == null)
				;
			else
				return parentSearchforInsertion(val, start.left);
		} else if (start.value < val) {
			if (start.right == null)
				;
			else
				return parentSearchforInsertion(val, start.right);
		}
		return start;
	}

	Node parentSearchforDeletion(int val, Node start) {
		if (start == null)
			;
		else if (start.value > val) {
			if (start.left != null)
				if (start.left.value == val)
					;
				else
					return parentSearchforDeletion(val, start.left);
			else
				return null;
		} else if (start.value < val) {
			if (start.right != null)
				if (start.right.value == val)
					;
				else 
					return parentSearchforDeletion(val, start.right);
			else
				return null;
		}
		return start;
	}

	void inorder(Node start) {
		if (start != null) {
			inorder(start.left);
			System.out.print(start.value + " ");
			inorder(start.right);
		}
	}

	void preorder(Node start) {
		if (start != null) {
			preorder(start.right);
			System.out.print(start.value + " ");
			preorder(start.left);
		}
	}

	Node predecessor(int val) {
		Node s = search(val, root);
		if (s != null)
			if (s.left != null) {
				s = s.left;
				while (s.right != null)
					s = s.right;
			} else {
				s = s.parent;
				while (s != null && s.value > val)
					s = s.parent;
			}
		return s;
	}

	Node successor(int val) {
		Node s = search(val, root);
		if (s != null)
			if (s.right != null) {
				s = s.right;
				while (s.right != null)
					s = s.right;
			} else {
				s = s.parent;
				while (s != null && s.value < val)
					s = s.parent;
			}
		return s;
	}

	Node select(int rank, Node start){
		int a = 0;
		if(start.left!=null)
			a = start.left.nodesBelow;
		if(a==rank-1)
			return start;
		else if(a>=rank)
			return select(rank, start.left);
		else
			return select(rank-a-1, start.right);
			
	}
	public static void main(String s[]) {
		BinarySearchTree bst = new BinarySearchTree();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			bst.insert(scan.nextInt());
		}
		bst.inorder(bst.root);
		System.out.println();
		bst.preorder(bst.root);
		System.out.println();
		bst.delete(4);
		bst.inorder(bst.root);
	//	bst.delete(323);
		/*System.out.println((bst.predecessor(55) != null) ? bst.predecessor(55).value : "");
		System.out.println((bst.successor(55) != null) ? bst.successor(55).value : "");*/
		//System.out.println(bst.select(6, bst.root).value);
		scan.close();
	}
}
