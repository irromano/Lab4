package pkgGame;

import static org.junit.Assert.*;
import java.util.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.Test;

public class SudokuTest {

	private void PrintStars() {
		for (int i = 0; i < 50; i++)
			System.out.print("*");
		System.out.println();
	}

//	@Test
//	public void Sudoku_Test1() {
//
//		try {
//			Sudoku s1 = new Sudoku(9);
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//
//	@Test(expected = Exception.class)
//	public void Sudoku_Test2() throws Exception {
//
//		Sudoku s1 = new Sudoku(10);
//
//	}
//
//	@Test
//	public void getRegion_Test1() {
//
//		int[][] puzzle = { { 1, 2, 3, 4 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 } };
//		int[] ExpectedRegion = { 3, 4, 1, 2 };
//
//		//
//		// 1 2 3 4
//		// 3 4 1 2
//		// 2 1 4 3
//		// 4 3 2 1
//		//
//		// region 0 = 1 2 3 4
//		// region 1 = 3 4 1 2
//
//		int[] region;
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//
//			region = s1.getRegion(1);
//			System.out.println(Arrays.toString(region));
//			assertTrue(Arrays.equals(ExpectedRegion, region));
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//
//	@Test
//	public void getRegion_Test2() {
//
//		int[][] puzzle = { { 1, 2, 3, 4 }, { 3, 4, 1, 2 }, { 2, 1, 4, 3 }, { 4, 3, 2, 1 } };
//		int[] ExpectedRegion = { 2, 1, 4, 3 };
//
//		//
//		// 1 2 3 4
//		// 3 4 1 2
//		// 2 1 4 3
//		// 4 3 2 1
//		//
//		// region at 0,2 = 2 1 4 3
//
//		int[] region;
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//
//			region = s1.getRegion(0, 2);
//			System.out.println(Arrays.toString(region));
//			assertTrue(Arrays.equals(ExpectedRegion, region));
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//
//	@Test
//	public void Sudoku_test1() {
//		int[][] puzzle = { { 5, 3, 4, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
//				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
//				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
//
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//			assertTrue(s1.isSudoku());
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//
//	@Test
//	public void Sudoku_test2() {
//		int[][] puzzle = { { 5, 5, 5, 6, 7, 8, 9, 1, 2 }, { 6, 7, 2, 1, 9, 5, 3, 4, 8 }, { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
//				{ 8, 5, 9, 7, 6, 1, 4, 2, 3 }, { 4, 2, 6, 8, 5, 3, 7, 9, 1 }, { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
//				{ 9, 6, 1, 5, 3, 7, 2, 8, 4 }, { 2, 8, 7, 4, 1, 9, 6, 3, 5 }, { 3, 4, 5, 2, 8, 6, 1, 7, 9 } };
//
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//			assertFalse(s1.isSudoku());
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//	
//	@Test
//	public void isValidColumnValue_Test() {
//		int[][] puzzle = { {1,2,3,4}, {1,2,3,4}, {1,2,3,4}, {1,2,3,4}};
//		// Test which values 1-4 would work in column 2
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//			assertTrue(s1.isValidColumnValue(2, 1));
//			assertTrue(s1.isValidColumnValue(2, 2));
//			assertFalse(s1.isValidColumnValue(2, 3));
//			assertTrue(s1.isValidColumnValue(2, 4));
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//	@Test
//	public void isValidRowValue_Test() {
//		int[][] puzzle = { {1,1,1,1}, {2,2,2,2}, {3,3,3,3}, {4,4,4,4}};
//		// Test which values 1-4 would work in row 1
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//			assertTrue(s1.isValidRowValue(1, 1));
//			assertFalse(s1.isValidRowValue(1, 2));
//			assertTrue(s1.isValidRowValue(1, 3));
//			assertTrue(s1.isValidRowValue(1, 4));
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}
//	@Test
//	public void isValidRegionValue_Test() {
//		int[][] puzzle = { {1,1,2,2}, {1,1,2,2}, {3,3,4,4}, {3,3,4,4}};
//		// Test which values 1-4 would work in row 3 column 2 (region 3)
//		try {
//			Sudoku s1 = new Sudoku(puzzle);
//			assertTrue(s1.isValidRegionValue(3, 2, 1));
//			assertTrue(s1.isValidRegionValue(3, 2, 2));
//			assertTrue(s1.isValidRegionValue(3, 2, 3));
//			assertFalse(s1.isValidRegionValue(3, 2, 4));
//
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//
//	}


//	@Test
//	public void buildCellSet_Test2() {
//		//int[][] puzzle = { {1,2,0,0}, {3,4,0,0}, {0,0,4,1}, {0,0,2,3}};
//		try {
//			Sudoku s1 = new Sudoku(4);
//			s1.FillDiagonalRegions();
//			s1.PrintPuzzle();
//			System.out.print(s1.getAllValidCellValues(0,0));
//			System.out.print(s1.getAllValidCellValues(0,1));
//			System.out.print(s1.getAllValidCellValues(0,2));
//			System.out.print(s1.getAllValidCellValues(0,3));
//			System.out.println();
//			System.out.print(s1.getAllValidCellValues(1,0));
//			System.out.print(s1.getAllValidCellValues(1,1));
//			System.out.print(s1.getAllValidCellValues(1,2));
//			System.out.print(s1.getAllValidCellValues(1,3));
//			System.out.println();
//			System.out.print(s1.getAllValidCellValues(2,0));
//			System.out.print(s1.getAllValidCellValues(2,1));
//			System.out.print(s1.getAllValidCellValues(2,2));
//			System.out.print(s1.getAllValidCellValues(2,3));
//			System.out.println();
//			System.out.print(s1.getAllValidCellValues(3,0));
//			System.out.print(s1.getAllValidCellValues(3,1));
//			System.out.print(s1.getAllValidCellValues(3,2));
//			System.out.print(s1.getAllValidCellValues(3,3));
//			
//		} catch (Exception e) {
//			fail("Test failed to build a Sudoku");
//		}
//		
//
//	}


	/**
	 * Sudoku Constructor Test1 - This test ensures that the constructor returns a true Sudoku.
	 * It implicitly tests setCells, fillRemaining and all cell functions.
	 * 
	 */
	@Test
	public void Sudoku_Test1() {
		try {
			Sudoku s1 = new Sudoku(9); // a 9x9 Sudoku.
			s1.PrintPuzzle();			// prints the puzzle
			assertTrue(s1.isSudoku());	// checks if the puzzle is a true Sudoku

		} catch (Exception e) {
			fail("Test failed to build a Sudoku");
		}
		

	}

}
