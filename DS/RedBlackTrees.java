import java.util.Scanner;

class Node{
	int value;
	boolean isBlack;
	Node right;
	Node left;
	Node parent;
	@Override
	public String toString() {
		return value+"";
	}
}

public class RedBlackTrees {
	Node root;
	
	public void leftShift(Node par){
		if(par.right!=null){
			Node right = par.right;
			par.right = right.left;
			if(par.right!=null){
				par.right.parent = par;
			}
			right.left = par;
			right.parent = par.parent;	
			if(par.parent!=null){
				if(par.parent.right==par){
					par.parent.right = right;
				}else{
					par.parent.left = right;
				}
			}else{
				root = right;
			}
			par.parent = right;
		}
	}
	
	public void rightShift(Node par){
		if(par.left!=null){
			Node left = par.left;
			par.left = left.right;
			if(par.left!=null){
				par.left.parent = par;
			}
			left.right = par;
			left.parent = par.parent;
			if(par.parent!=null){
				if(par.parent.right==par){
					par.parent.right = left;
				}else{
					par.parent.left = left;
				}
			}else{
				root = left;
			}
			par.parent = left;
		}
	}
	
	void insert(int val){
		Node pos = parentSearchforInsertion(val, root);
		if (pos == null) {
			root = new Node();
			root.value = val;
			root.isBlack = true;
		} else {
			if (pos.value >= val) {
				pos.left = new Node();
				pos.left.parent = pos;
				pos.left.value = val;
				pos.left.isBlack = false;
				balance(pos.left);
				
			} else {
				pos.right = new Node();
				pos.right.parent = pos;
				pos.right.value = val;
				pos.right.isBlack = false;
				balance(pos.right);
			}
		}
	}
	
	void balance(Node n){
		//case0-if par is black
		if(n.isBlack)
			return;
		if(n.parent.isBlack)
			return;
		else {
			boolean isChildLeft = false;
			boolean isParentLeft = false;
			Node parent = n.parent;
			Node gParent = parent.parent;
			if(parent.left==n){
				isChildLeft = true;
			}
			if(gParent.left == parent){
				isParentLeft = true;
			}
			//case 1 if x's uncle is red both right/left
			if((!isParentLeft&&(gParent.left!=null&&!gParent.left.isBlack))||
							(isParentLeft&&(gParent.right!=null&&!gParent.right.isBlack))
					){
				recolor(parent);
				if(!isParentLeft){
					if(gParent.left!=null)
						recolor(gParent.left);
				}else{
					if(gParent.right!=null)
						recolor(gParent.right);
				}
				if(gParent.parent!=null){
					recolor(gParent);
					balance(gParent);
				}
				return;
			}
			//case 2.1 uncle's black left left case
			else if(isChildLeft&&isParentLeft/*&&gParent.right.isBlack*/){
				rightShift(gParent);
				recolor(gParent);
				recolor(parent);
			}
			//case 2.2 uncle's black left right case
			else if(!isChildLeft&&isParentLeft/*&&gParent.right.isBlack*/){
				leftShift(parent);
				balance(parent);
				return;
			}
			//case 2.3 uncle's black right right case
			else if(!isChildLeft&&!isParentLeft/*&&gParent.left.isBlack*/){
				leftShift(gParent);
				recolor(gParent);
				recolor(parent);
				return;
			}
			//case 2.4 uncle's black right left case
			else if(isChildLeft&&!isParentLeft/*&&gParent.left.isBlack*/){
				rightShift(parent);
				balance(parent);
				return;
			}
		}
	}
	
	void recolor(Node n){
		if(n.isBlack)
			n.isBlack = false;
		else
			n.isBlack = true;
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
	
	public static void main(String s[]){
		RedBlackTrees rbt = new RedBlackTrees();
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i=0;i<n;i++){
			rbt.insert(scan.nextInt());
		}
		rbt.inorder(rbt.root);
		scan.close();
	}
}