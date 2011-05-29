package entities.utility;

import entities.utility.Index;
import java.util.ArrayList;


public class MatrixCompare {
	private boolean result;
	private ArrayList<Index> indexes;
	
	public MatrixCompare() {	}
	
	public MatrixCompare(int[][] mtrx1, int[][] mtrx2) {
		ArrayList<Index> retList = new ArrayList<Index>();
		int row = 0;
		int col;
		while(row < mtrx1.length) {
			col = 0;
			while(col < mtrx1[row].length) {
				if(mtrx1[row][col] == mtrx2[row][col]) {
					Index item = new Index(row, col);
					retList.add(item);
				}
				col++;
			}
			row++;
		}
		this.indexes = retList;
		this.result = true;
		if(this.indexes.isEmpty() == true) { this.result = false; }
	}
	
	public boolean getResult() {
		return this.result;
	}

	public ArrayList<Index> getIndexes() {
		return this.indexes;
	}
}
