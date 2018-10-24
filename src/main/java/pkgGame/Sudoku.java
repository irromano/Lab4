package pkgGame;

import java.security.SecureRandom;
import java.util.*;
import java.util.HashSet;
import java.util.Random;

import pkgHelper.LatinSquare;

/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @version 1.2
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {

	/**
	 * 
	 * iSize - the length of the width/height of the Sudoku puzzle.
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */
	private int iSize;

	/**
	 * iSqrtSize - SquareRoot of the iSize. If the iSize is 9, iSqrtSize will be
	 * calculated as 3
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */

	private int iSqrtSize;

	/**
	 * cellMap - HashMap of Hashsets for every cell in puzzle. The key is an int or
	 * string representing the cell, from 0 to (iSize^2) -1.
	 * 
	 * @version 1.2
	 * @since Lab #2
	 */
	public HashMap<Integer, Sudoku.Cell> cells = new HashMap<Integer, Sudoku.Cell>();

	/**
	 * Cell a class which has one attribute, HashSet<Integers>, of possible values
	 * for that cell.
	 * 
	 * @version 1.0
	 * @since Lab #4
	 */
	private class Cell {
		private int iCol;
		private int iRow;
		private ArrayList<Integer> lstValidValues = new ArrayList<Integer>();

		public Cell(int iRow, int iCol) {
			super();
			this.iRow = iRow;
			this.iCol = iCol;
		}

		public int getiCol() {
			return iCol;
		}

		public int getiRow() {
			return iRow;
		}

		@Override
		public int hashCode() {
			return (iRow * iSize) + iCol;
		}

		@Override
		public boolean equals(Object obj) {
//			if (obj == this) {
//				return true;
//			}
//			if (!(obj instanceof Cell)) {
//				return false;
//			}

			Cell cell1 = (Cell) obj;
			return (cell1.iRow == this.iRow) && (cell1.iCol == this.iCol);
		}

		public ArrayList<Integer> getLstValidValues() {
			return lstValidValues;
		}

		public void setLstValidValues() {
			HashSet<Integer> validHash = new HashSet<Integer>();
			
			for (int i = 1; i <= iSize; i++) {
				if (isValidValue(iRow, iCol, i)) {
					validHash.add(i);
				}
			}
			lstValidValues = new ArrayList<Integer>(validHash);
			lstValidValues.trimToSize();

		}

		public void ShuffleValidValues() {
			int[] arr = new int[lstValidValues.size()];
			for (int i = 0; i < lstValidValues.size(); i++) {
				arr[i] = lstValidValues.get(i);
			}
			shuffleArray(arr);
			lstValidValues.clear();
			for (int i = 0; i < arr.length; i++) {
				lstValidValues.add(i, arr[i]);
			}

		}
		
		public Sudoku.Cell GetNextCell(Sudoku.Cell c) {
			Integer index = c.hashCode();
			index++;
			if(index >= iSize*iSize||index<0)
				return null;
			while(getPuzzle()[index/iSize][index%iSize-1] != 0) {
				index++;
			}
			return (Sudoku.Cell) cells.get(index);

		}


	}
	
	private HashMap<Integer, Sudoku.Cell> getCells() {
		return cells;
	}

	/**
	 * Sudoku - for Lab #2... do the following:
	 * 
	 * set iSize If SquareRoot(iSize) is an integer, set iSqrtSize, otherwise throw
	 * exception
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iSize- length of the width/height of the puzzle
	 * @throws Exception if the iSize given doesn't have a whole number square root
	 */

	public Sudoku(int iSize) throws Exception {

		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

		int[][] puzzle = new int[iSize][iSize];
		super.setLatinSquare(puzzle);
		FillDiagonalRegions();
	}

	/**
	 * Sudoku - pass in a given two-dimensional array puzzle, create an instance.
	 * Set iSize and iSqrtSize
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param puzzle - given (working) Sudoku puzzle. Use for testing
	 * @throws Exception will be thrown if the length of the puzzle do not have a
	 *                   whole number square root
	 */
	public Sudoku(int[][] puzzle) /* throws Exception */ {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
//		} else {
//			throw new Exception("Invalid size");
		}

	}

	/**
	 * getPuzzle - return the Sudoku puzzle
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns the LatinSquare instance
	 */
	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}

	/**
	 * getRegionNbr - Return region number based on given column and row
	 * 
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegionNbr(3,0) should return a value of 1
	 * 
	 * @param iCol - Given column number
	 * @param iRow - Given row number
	 * @version 1.3
	 * @since Lab #3
	 * 
	 * @return - return region number based on given column and row
	 */
	public int getRegionNbr(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return i;
	}

	/**
	 * getRegion - figure out what region you're in based on iCol and iRow and call
	 * getRegion(int)<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(0,3) would call getRegion(1) and return [2],[3],[3],[4]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol given column
	 * @param iRow given row
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */
	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	/**
	 * getRegion - pass in a given region, get back a one-dimensional array of the
	 * region's content<br>
	 * 
	 * Example, the following Puzzle:
	 * 
	 * 0 1 2 3 <br>
	 * 1 2 3 4 <br>
	 * 3 4 1 2 <br>
	 * 4 1 3 2 <br>
	 * 
	 * getRegion(2) and return [3],[4],[4],[1]
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param r given region
	 * @return - returns a one-dimensional array from a given region of the puzzle
	 */

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int i = (r % iSqrtSize) * iSqrtSize;
		int j = (r / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int iCnt = 0;

		for (; j < jMax; j++) {
			for (i = (r % iSqrtSize) * iSqrtSize; i < iMax; i++) {
				reg[iCnt++] = super.getLatinSquare()[j][i];
			}
		}

		return reg;
	}

	/**
	 * isPartialSudoku - return 'true' if...
	 * 
	 * It's a LatinSquare If each region doesn't have duplicates If each element in
	 * the first row of the puzzle is in each region of the puzzle At least one of
	 * the elemnts is a zero
	 * 
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return true if the given puzzle is a partial sudoku
	 */
	public boolean isPartialSudoku() {

		if (!super.isLatinSquare()) {
			return false;
		}

		for (int k = 0; k < this.getPuzzle().length; k++) {

			if (super.hasDuplicates(getRegion(k))) {
				return false;
			}

			if (!hasAllValues(getRow(0), getRegion(k))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;

	}

	/**
	 * isSudoku - return 'true' if...
	 * 
	 * Is a partialSudoku Each element doesn't a zero
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @return - returns 'true' if it's a partialSudoku, element match (row versus
	 *         column) and no zeros
	 */
	public boolean isSudoku() {

		if (!isPartialSudoku()) {
			return false;
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given column /
	 * row
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param iCol   puzzle column
	 * @param iRow   puzzle row
	 * @param iValue given value
	 * @return - returns 'true' if the proposed value is valid for the row and
	 *         column
	 */
	// -Revised 10/6/2018 to use isValidColumnValue, isValidRowValue, and
	// isValidRegionValue methods
	public boolean isValidValue(int iRow, int iCol, int iValue) {

		if (!isValidColumnValue(iCol, iValue)) {
			return false;
		}
		if (!isValidRowValue(iRow, iValue)) {
			return false;
		}
		if (!isValidRegionValue(iRow, iCol, iValue)) {
			return false;
		}

		return true;
	}

	/**
	 * isValidValue - test to see if a given value would 'work' for a given cell
	 * 
	 * @version 1.2
	 * @since Lab #2
	 * @param        Sudoku.Cell c
	 * @param iValue given value
	 * @return - returns 'true' if the proposed value is valid for the cell
	 */
	public boolean isValidValue(Sudoku.Cell c, int iValue) {

		if (!isValidColumnValue(c.getiCol(), iValue)) {
			return false;
		}
		if (!isValidRowValue(c.getiRow(), iValue)) {
			return false;
		}
		if (!isValidRegionValue(c.getiRow(), c.getiCol(), iValue)) {
			return false;
		}

		return true;
	}

	// Tests to see if a given value is already in the cell's column
	public boolean isValidColumnValue(int iCol, int iValue) {
		if (doesElementExist(super.getColumn(iCol), iValue))
			return false;
		return true;
	}

	// Tests to see if a given value is already in the cell's row
	public boolean isValidRowValue(int iRow, int iValue) {
		if (doesElementExist(super.getRow(iRow), iValue))
			return false;
		return true;
	}

	// Tests to see if a given value is already in the cell's region
	public boolean isValidRegionValue(int iRow, int iCol, int iValue) {
		if (doesElementExist(this.getRegion(iCol, iRow), iValue))
			return false;
		return true;
	}

	/**
	 * PrintPuzzle This method will print the puzzle to the console (space between
	 * columns, line break after row)
	 * 
	 * @version 1.3
	 * @since Lab #3
	 */
	public void PrintPuzzle() {
		for (int i = 0; i < this.getPuzzle().length; i++) {
			System.out.println("");
			for (int j = 0; j < this.getPuzzle().length; j++) {
				System.out.print(this.getPuzzle()[i][j]);
				if ((j + 1) % iSqrtSize == 0)
					System.out.print(" ");
			}
			if ((i + 1) % iSqrtSize == 0)
				System.out.println(" ");

		}
		System.out.println("");
	}

	/**
	 * FillDiagonalRegions - After the puzzle is created, set the diagonal regions
	 * with random values
	 * 
	 * @version 1.3
	 * @since Lab #3
	 */
	public void FillDiagonalRegions() {

		for (int i = 0; i < iSize; i = i + iSqrtSize) {
			System.out.println("Filling region: " + getRegionNbr(i, i));
			SetRegion(getRegionNbr(i, i));
			ShuffleRegion(getRegionNbr(i, i));
		}
	}

	/**
	 * SetRegion - purpose of this method is to set the values of a given region
	 * (they will be shuffled later)
	 * 
	 * Example, the following Puzzle start state:
	 * 
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * SetRegion(2) would transform the Puzzle to:<br>
	 * 
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 1 2 0 0 <br>
	 * 3 4 0 0 <br>
	 * 
	 * @version 1.3
	 * @since Lab #3
	 * @param r - Given region number
	 */
	private void SetRegion(int r) {
		int iValue = 0;

		iValue = 1;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = iValue++;
			}
		}
	}

	/**
	 * SetRegion - purpose of this method is to set the values of a given region
	 * (they will be shuffled later)
	 * 
	 * Example, the following Puzzle start state:
	 * 
	 * 1 2 0 0 <br>
	 * 3 4 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * ShuffleRegion(0) might transform the Puzzle to:<br>
	 * 
	 * 2 3 0 0 <br>
	 * 1 4 0 0 <br>
	 * 0 0 0 0 <br>
	 * 0 0 0 0 <br>
	 * 
	 * @version 1.3
	 * @since Lab #3
	 * @param r - Given region number
	 */
	private void ShuffleRegion(int r) {
		int[] region = getRegion(r);
		shuffleArray(region);
		int iCnt = 0;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = region[iCnt++];
			}
		}
	}

	/**
	 * shuffleArray this method will shuffle a given one-dimension array
	 * 
	 * @version 1.3
	 * @since Lab #3
	 * @param ar given one-dimension array
	 */
	private void shuffleArray(int[] ar) {

		Random rand = new SecureRandom();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	private HashSet<Integer> getAllValidCellValues(int iRow, int iCol) {
		HashSet<Integer> cellHashSet = new HashSet<Integer>();
		Cell newCell = new Cell(iRow, iCol);
		newCell.setLstValidValues();
		if (this.getLatinSquare()[iRow][iCol] != 0) {
			cellHashSet.add(this.getLatinSquare()[iRow][iCol]);
		} else {
			cellHashSet = new HashSet<Integer>(newCell.getLstValidValues());
		}
		return cellHashSet;

	}

	public HashSet<Integer> getAllValidCellValues(Cell newCell, int iRow, int iCol) {
		newCell.setLstValidValues();
		HashSet<Integer> cellHashSet = new HashSet<Integer>(newCell.getLstValidValues());
		return cellHashSet;

	}

	/**
	 * SetCells Builds a HashMap of cells of possible values for a cell. It can
	 * build a HashSet from 1 to iSize, then use isValid method to remove ints that
	 * wouldn’t work.
	 * 
	 * @version 1.0
	 * @since Lab #4
	 */
	public void SetCells() {
		for (int iRow = 0; iRow < iSize; iRow++) {
			for (int iCol = 0; iCol < iSize; iCol++) {
				Sudoku.Cell newCell = new Sudoku.Cell(iRow, iCol);
				newCell.setLstValidValues();
				newCell.ShuffleValidValues();
				cells.put(newCell.hashCode(), newCell);
			}
		}
	}

	/**
	 * FillRemaining This method will set the zero value cells with a valid value.
	 * Uses pickValue for every cell that is zero.
	 * 
	 * @version 1.0
	 * @since Lab #4
	 * @param cell
	 */
//	private void fillRemaining() {
//		//TODO
//	}

}
