import java.lang.Math;

public class AVL<T> {
	
	private AVLNode<T> root;
	
	// Constructor
	public AVL(AVLNode<T> root){
		this.root = root;
	}
	
	public AVL(){
		this.root = null;
	}
	
	public AVLNode<T> classicInsertRecursive(AVLNode<T> curNode, AVLNode<T> newNode){
		if (curNode == null) {
			curNode = newNode;
			return curNode;
		}
		int newKey = newNode.getKey();
		if (newKey < curNode.getKey()) {
			curNode.setLeftChild(classicInsertRecursive(curNode.getLeftChild(), newNode));
		}
		else {
			curNode.setRightChild(classicInsertRecursive(curNode.getRightChild(), newNode));
		}
		return curNode;
	}
	
	public boolean isBallanced(AVLNode<T> node) {
		int leftChildHeight = -1;
		if (node.getLeftChild() != null){
			leftChildHeight = node.getLeftChild().getHeight();
		}
		int rightChildHeight = -1;
		if (node.getRightChild() != null){
			rightChildHeight = node.getRightChild().getHeight();
		}
		if (Math.abs(rightChildHeight-leftChildHeight) <= 1) {
			return true;
		}
		return false;
	}
	
	public void rightRotation(AVLNode<T> z) {
		AVLNode<T> zFather = z.getFather();
		AVLNode<T> y = z.getLeftChild();
		AVLNode<T> b = y.getRightChild();
		int  aHeight = -1, cHeight = -1, bHeight = -1;
		if (y.getLeftChild() != null) {
			aHeight = y.getLeftChild().getHeight();
		}
		if (z.getRightChild() != null) {
			cHeight = z.getRightChild().getHeight();
		}
		if (b != null) {
			bHeight = b.getHeight();
		}
		y.setRightChild(z);
		z.setLeftChild(b);
		if (zFather != null) {
			if (z==zFather.getLeftChild()) {
				zFather.setLeftChild(y);
			}
			else {
				zFather.setRightChild(y);
			}	
		}
		else {
			this.root = y;
			this.root.setFather(null);
		}
		z.setHeight(Math.max(bHeight, cHeight) +1);
		y.setHeight(Math.max(aHeight, z.getHeight()) +1);
		y.arrangeHeigts();
	}
	
	public void leftRotation(AVLNode<T> z) {
		AVLNode<T> zFather = z.getFather();
		AVLNode<T> y = z.getRightChild();
		AVLNode<T> b = y.getLeftChild();
		int  aHeight = -1, cHeight = -1, bHeight = -1;
		if (z.getLeftChild() != null) {
			aHeight = z.getLeftChild().getHeight();
		}
		if (y.getRightChild() != null) {
			cHeight = y.getRightChild().getHeight();
		}
		if (b != null) {
			bHeight = b.getHeight();
		}
		y.setLeftChild(z);
		z.setRightChild(b);
		if (zFather != null) {
			if (z==zFather.getLeftChild()) {
				zFather.setLeftChild(y);
			}
			else {
				zFather.setRightChild(y);
			}
		}
		else {
			this.root = y;
			this.root.setFather(null);
		}
		z.setHeight(Math.max(aHeight, bHeight) +1);
		y.setHeight(Math.max(cHeight, z.getHeight()) +1);
		y.arrangeHeigts();
	}
	
	public void insert(int key, T data){
		AVLNode<T> newNode = new AVLNode<T>(key, data);
		this.root = classicInsertRecursive(this.root, newNode);
		newNode.arrangeHeigts();
		AVLNode<T> x = null;
		AVLNode<T> y= newNode;
		while (y.getFather() != null && isBallanced(y.getFather())){
			x=y;
			y=y.getFather();
		}
		AVLNode<T> z = y.getFather();
		if (z != null) {
			boolean yLeftOfZ = (y == z.getLeftChild());
			boolean xLeftOfY = (x == y.getLeftChild());
			if (yLeftOfZ && xLeftOfY) {
				rightRotation(z);
			}
			if (yLeftOfZ && !xLeftOfY) {
				leftRotation(y);
				rightRotation(z);
			}
			if (!yLeftOfZ && !xLeftOfY) {
				leftRotation(z);
			}
			if (!yLeftOfZ && xLeftOfY) {
				rightRotation(y);
				leftRotation(z);
			}
		}
	}

	public T search(int key){
		return recursiceSearch(this.root, key);
	}
	
	public T recursiceSearch(AVLNode<T> cur, int key){
		if (cur == null) {
			return null;
		}
		if (key == cur.getKey()) {
			return cur.getData();
		}
		if (key < cur.getKey()) {
			return recursiceSearch(cur.getLeftChild(), key);
		}
		else {
			return recursiceSearch(cur.getRightChild(), key);
		}
	}

	public AVLNode<T> getRoot(){
		return this.root;
	}
	
}