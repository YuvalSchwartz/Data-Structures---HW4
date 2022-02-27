import java.util.LinkedList;
import java.util.ListIterator;

public class HashTable {
	
	private int size;
	private int m;
	private LinkedList<ObjectWithCoordinates>[] table;
	
	@SuppressWarnings("unchecked")
	public HashTable(int m) {
		this.size = 0;
		this.m = m;
		this.table = new LinkedList[m];
		for (int i=0 ; i<m ; i++) {
			this.table[i] = new LinkedList<ObjectWithCoordinates>();
		}
	}
	
	public LinkedList<ObjectWithCoordinates>[] getTable(){
		return this.table;
	}
	
	public int hashFunc(int x, int y) {
		return (x + y) % this.m;
	}
	public int hashFunc(ObjectWithCoordinates o) {
		return (o.getX() + o.getY()) % this.m;
	}
	
	public void insert(ObjectWithCoordinates object){
		int index = hashFunc(object);
		this.table[index].addFirst(object);
		this.size += 1;
	}
	
	public int getSize() {
		return this.size;
	}

	public ObjectWithCoordinates search(int x, int y){
		int index = hashFunc(x, y);
		LinkedList<ObjectWithCoordinates> myList = this.table[index];
		ListIterator<ObjectWithCoordinates> list_Iter = myList.listIterator(0);
		while(list_Iter.hasNext()){
			ObjectWithCoordinates temp = list_Iter.next();
			int tempX = temp.getX();
			int tempY = temp.getY();
			if (x==tempX && y==tempY) {
				return temp;
			}
	    }
		return null;
	}
	
	public String toString() {//////////
		String text = "";
		for (int i=0 ; i < this.table.length ; i++) {
			ListIterator<ObjectWithCoordinates> tempXIter = this.table[i].listIterator(0);
			while(tempXIter.hasNext()){
				text += tempXIter.next();
		    }
		}
		return text;
	}
	
}