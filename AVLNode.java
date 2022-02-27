public class AVLNode<T> {
	
	private  int key;
	private  T data;
	private  AVLNode<T> father;
	private  AVLNode<T> left_child;
	private  AVLNode<T> right_child;
	private  int height;
	
	// Constructor
	public AVLNode(int key, T data){
		this.key = key;
		this.data = data;
		this.father = null;
		this.left_child = null;
		this.right_child = null;
		this.height = 0;
	}
	
	// Getters
	public AVLNode<T> getLeftChild(){
		return this.left_child;
	}
	
	public AVLNode<T> getRightChild(){
		return this.right_child;
	}
	
	public AVLNode<T> getFather(){
		return this.father;
	}
	
	public int getKey(){
		return this.key;
	}
	
	public T getData(){
		return this.data;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void arrangeHeigts() {
		AVLNode<T> cur = this;
		while (cur != null){
			int leftChildHeight = -1;
			if (cur.getLeftChild() != null){
				leftChildHeight = cur.getLeftChild().getHeight();
			}
			int rightChildHeight = -1;
			if (cur.getRightChild() != null){
				rightChildHeight = cur.getRightChild().getHeight();
			}
			if (leftChildHeight >= rightChildHeight){
				cur.setHeight(leftChildHeight + 1);
			}
			else {
				cur.setHeight(rightChildHeight + 1);
			}
			cur=cur.getFather();
		}
		
	}
	
	// Setters
	public void setFather(AVLNode<T> father) {
		this.father = father;
	}
	
	public void setLeftChild(AVLNode<T> left_child){
		this.left_child = left_child;
		if (left_child != null) {
			left_child.setFather(this);
		}
	}
	
	public void setRightChild(AVLNode<T> right_child){
		this.right_child = right_child;
		if (right_child != null) {
			right_child.setFather(this);
		}
	}
	
	public void setHeight(int h){
		 this.height = h;
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		if (getLeftChild() != null){
			s+="(";
			s+=getLeftChild().toString();
			s+=")";
		}
		s+=getKey();
		
		if (getRightChild() != null){
			s+="(";
			s+=getRightChild().toString();
			s+=")";
		}
		
		return s;
		
	}
}