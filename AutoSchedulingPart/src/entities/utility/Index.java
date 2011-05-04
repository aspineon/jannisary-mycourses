package entities.utility;

public class Index {
	
	private int row;
	private int column;
	
	public Index() {	}
	
	public Index(int rw, int cl) {
		this.row = rw;
		this.column = cl;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setRow(int rw) {
		this.row = rw;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void setColumn(int cl) {
		this.column = cl;
	}
}
