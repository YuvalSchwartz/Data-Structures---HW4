
public class Point implements ObjectWithCoordinates{
	
	private int x;
	private int y;
	private Object data;
	
	public Point(int x, int y, Object data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public String toString() {
		return this.data + " X=" + this.x + " Y=" + this.y;
	}
}
