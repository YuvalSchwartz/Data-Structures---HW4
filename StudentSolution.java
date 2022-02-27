
import java.util.LinkedList;
import java.util.ListIterator;


public class StudentSolution  implements MyInterface{

	private AVL<ObjectWithCoordinates> myXTree;
	private AVL<ObjectWithCoordinates> myYTree;
	
	public StudentSolution() {
		this.myXTree = new AVL<ObjectWithCoordinates>();
		this.myYTree = new AVL<ObjectWithCoordinates>();
	}
	
	@Override
	public void insertDataFromDBFile(String objectName, int objectX, int objectY) {
		ObjectWithCoordinates temp = new Point(objectX, objectY, objectName);
		this.myXTree.insert(objectX, temp);
		this.myYTree.insert(temp.getY(), temp);
	}
	
	private HashTable inXRange(HashTable curXTable, AVLNode<ObjectWithCoordinates> curNode, int a, int b){
		if (curNode == null) {
			return curXTable;
		}
		if (curNode.getData().getX() >= a && curNode.getData().getX() <= b) {
			curXTable.insert(curNode.getData());
			curXTable = inXRange(curXTable, curNode.getLeftChild(), a, b);
			curXTable = inXRange(curXTable, curNode.getRightChild(), a, b);
			return curXTable;
		}
		else if (curNode.getData().getX() < a) {
			return inXRange(curXTable, curNode.getRightChild(), a, b);
		}
		else {
			return inXRange(curXTable, curNode.getLeftChild(), a, b);
		}
	}
	private HashTable inYRange(HashTable curYTable, AVLNode<ObjectWithCoordinates> curNode, int a, int b){
		if (curNode == null) {
			return curYTable;
		}
		if (curNode.getData().getY() >= a && curNode.getData().getY() <= b) {
			curYTable.insert(curNode.getData());
			curYTable = inYRange(curYTable, curNode.getLeftChild(), a, b);
			curYTable = inYRange(curYTable, curNode.getRightChild(), a, b);
			return curYTable;
		}
		else if (curNode.getData().getY() < a) {
			return inYRange(curYTable, curNode.getRightChild(), a, b);
		}
		else {
			return inYRange(curYTable, curNode.getLeftChild(), a, b);
		}
	}
	
	@Override
	public String[] firstSolution(int leftTopX, int leftTopY, int rightBottomX,
			int rightBottomY) {
		HashTable xHash = new HashTable(13);
		xHash = inXRange(xHash, this.myXTree.getRoot(), leftTopX, rightBottomX);
		HashTable yHash = new HashTable(13);
		yHash = inYRange(yHash, this.myYTree.getRoot(), rightBottomY, leftTopY);
		LinkedList<String> resultList = new LinkedList<String>();
		LinkedList<ObjectWithCoordinates>[] xTable = xHash.getTable();
		for (int i=0 ; i < xTable.length ; i++) {
			ListIterator<ObjectWithCoordinates> tempXIter = xTable[i].listIterator(0);
			while(tempXIter.hasNext()){
				ObjectWithCoordinates tempPoint = tempXIter.next();
				int tempX = tempPoint.getX();
				int tempY = tempPoint.getY();
				if (yHash.search(tempX, tempY) != null) {
					resultList.addFirst(tempPoint.toString());
				}
		    }
		}
		String[] result = resultList.toArray(new String[resultList.size()]);
		return result;
	}
	
	
	private LinkedList<ObjectWithCoordinates> inXRange(LinkedList<ObjectWithCoordinates> curXList, AVLNode<ObjectWithCoordinates> curNode, int a, int b){
		if (curNode == null) {
			return curXList;
		}
		if (curNode.getData().getX() >= a && curNode.getData().getX() <= b) {
			curXList = inXRange(curXList, curNode.getRightChild(), a, b);
			curXList.addFirst(curNode.getData());
			curXList = inXRange(curXList, curNode.getLeftChild(), a, b);
			return curXList;
		}
		else if (curNode.getData().getX() < a) {
			return inXRange(curXList, curNode.getRightChild(), a, b);
		}
		else {
			return inXRange(curXList, curNode.getLeftChild(), a, b);
		}
	}
	private LinkedList<ObjectWithCoordinates> inYRange(LinkedList<ObjectWithCoordinates> curYList, AVLNode<ObjectWithCoordinates> curNode, int a, int b){
		if (curNode == null) {
			return curYList;
		}
		if (curNode.getData().getY() >= a && curNode.getData().getY() <= b) {
			curYList = inYRange(curYList, curNode.getRightChild(), a, b);
			curYList.addFirst(curNode.getData());
			curYList = inYRange(curYList, curNode.getLeftChild(), a, b);			
			return curYList;
		}
		else if (curNode.getData().getY() < a) {
			return inYRange(curYList, curNode.getRightChild(), a, b);
		}
		else {
			return inYRange(curYList, curNode.getLeftChild(), a, b);
		}
	}
	
	
	public boolean xBinarySearch(ObjectWithCoordinates[] searchArr, int left, int right, int xTarget) {
		if (right >= left) {
			int middle = (left + right)/2;
			if (searchArr[middle].getX() == xTarget) {
				return true;
			}
			if (searchArr[middle].getX() > xTarget) {
				return xBinarySearch(searchArr, left, middle-1, xTarget);
			}
			return xBinarySearch(searchArr, middle+1, right, xTarget);
		}
		return false;
	}
	
	public boolean yBinarySearch(ObjectWithCoordinates[] searchArr, int left, int right, int yTarget) {
		if (right >= left) {
			int middle = (left + right)/2;
			if (searchArr[middle].getY() == yTarget) {
				return true;
			}
			if (searchArr[middle].getY() > yTarget) {
				return yBinarySearch(searchArr, left, middle-1, yTarget);
			}
			return yBinarySearch(searchArr, middle+1, right, yTarget);
		}
		return false;
	}
	
	@Override
	public String[] secondSolution(int leftTopX, int leftTopY,
			int rightBottomX, int rightBottomY) {
		
		LinkedList<ObjectWithCoordinates> xList = new LinkedList<ObjectWithCoordinates>();
		LinkedList<ObjectWithCoordinates> yList = new LinkedList<ObjectWithCoordinates>();
		xList = inXRange(xList,this.myXTree.getRoot(), leftTopX, rightBottomX);
		yList = inYRange(yList, this.myYTree.getRoot(), rightBottomY, leftTopY);
		
		
		ObjectWithCoordinates[] xArray = xList.toArray(new Point[xList.size()]);
		ObjectWithCoordinates[] yArray = yList.toArray(new Point[yList.size()]);
		LinkedList<String> resultList = new LinkedList<String>();
		

		
		if (xArray.length >= yArray.length) {
			for (int i =0; i < yArray.length; i++) {
				if (xBinarySearch(xArray, 0, xArray.length-1, yArray[i].getX())) {
					resultList.addFirst(yArray[i].toString());
				}
			}
		}
		
		else {
			for (int i =0; i < xArray.length; i++) {
				if (yBinarySearch(yArray, 0, yArray.length-1, xArray[i].getY())) {
					resultList.addFirst(xArray[i].toString());
				}
			}
		}
		
		String[] resArray = resultList.toArray(new String[resultList.size()]);
		return resArray;
		
		
	}
}