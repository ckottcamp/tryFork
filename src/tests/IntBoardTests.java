/*
 * Authors: Bane Sullivan & Max Watson
 * CSCI 306: ClueGame
 * October 2016
 */

package tests;

import static org.junit.Assert.*;
import java.util.*;
import experiment.*;

import org.junit.Before;
import org.junit.Test;

import clueGame.BoardCell;

public class IntBoardTests {
	
	private IntBoard board;
	
	// @Before method to set up IntBoard.
	@Before
	public void executedBeforeEach() {
		board = new IntBoard();
		board.calcAdjacencies();
	}
	
	/*
	 * Test adjacencies for top left corner
	 * top left corner (i.e., location [0][0])
	 */
	@Test
	public void testAdjacency0() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	
	/*
	 * Test adjacencies for bottom right corner
	 * bottom right corner (i.e., location [3][3])
	 */
	@Test
	public void testAdjacency1() {
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(2, testList.size());
	}
	
	/*
	 * Test adjacencies for right edge
	 * a right edge (e.g., location [1][3])
	 */
	@Test
	public void testAdjacency2() {
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(3, testList.size());
	}
	
	/*
	 * Test adjacencies for left edge
	 * a left edge (e.g., location [3][0])
	 */
	@Test
	public void testAdjacency3() {
		BoardCell cell = board.getCell(3,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	
	/*
	 * Test adjacencies for left edge second column, middle of grid 
	 * (e.g., location [1][1])
	 */
	@Test
	public void testAdjacency4() {
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertEquals(4, testList.size());
	}
	
	/*
	 * Test adjacencies for second from last column, middle of grid
	 * (e.g., location [2][2]).
	 */
	@Test
	public void testAdjacency5() {
		BoardCell cell = board.getCell(2,2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertEquals(4, testList.size());
	}
	
	
	
	//////////////////////////////////////////////////////////////
	// TEST TARGETS:
	/*
	 * Be sure to test different numbers of steps
	 * Test from at least two different starting locations
	 * Number of steps should not exceed 6 (realistic roll of die)
	 */
	
	// Test for three steps
	@Test
	public void testTargets0_3_3() {
		// Test first location (0,0)
		BoardCell cell1 = board.getCell(0, 0);
		board.calcTargets(cell1, 3);
		Set targets1 = board.getTargets();
		assertEquals(6, targets1.size());
		assertTrue(targets1.contains(board.getCell(3, 0)));
		assertTrue(targets1.contains(board.getCell(2, 1)));
		assertTrue(targets1.contains(board.getCell(0, 1)));
		assertTrue(targets1.contains(board.getCell(1, 2)));
		assertTrue(targets1.contains(board.getCell(0, 3)));
		assertTrue(targets1.contains(board.getCell(1, 0)));
	}
	
	@Test
	public void testTargets2_2_3() {
		// Test second location (2,2)
		BoardCell cell2 = board.getCell(2, 2);
		board.calcTargets(cell2, 3);
		Set targets2 = board.getTargets();
		assertEquals(8, targets2.size());
		assertTrue(targets2.contains(board.getCell(1, 0)));
		assertTrue(targets2.contains(board.getCell(3, 0)));
		assertTrue(targets2.contains(board.getCell(0, 1)));
		assertTrue(targets2.contains(board.getCell(2, 1)));
		assertTrue(targets2.contains(board.getCell(1, 2)));
		assertTrue(targets2.contains(board.getCell(3, 2)));
		assertTrue(targets2.contains(board.getCell(0, 3)));
		assertTrue(targets2.contains(board.getCell(2, 3)));
	}
	
	
	// Test for one step
	@Test
	public void testTargets1_1_1() {
		// Test first location (1,1)
		BoardCell cell1 = board.getCell(1, 1);
		board.calcTargets(cell1, 1);
		Set targets1 = board.getTargets();
		assertEquals(4, targets1.size());
		assertTrue(targets1.contains(board.getCell(1, 0)));
		assertTrue(targets1.contains(board.getCell(0, 1)));
		assertTrue(targets1.contains(board.getCell(1, 2)));
		assertTrue(targets1.contains(board.getCell(2, 1)));
	}
	
	@Test
	public void testTargets2_3_1() {
		// Test second location (2,3)
		BoardCell cell2 = board.getCell(2, 3);
		board.calcTargets(cell2, 1);
		Set targets2 = board.getTargets();
		assertEquals(3, targets2.size());
		assertTrue(targets2.contains(board.getCell(1, 3)));
		assertTrue(targets2.contains(board.getCell(2, 2)));
		assertTrue(targets2.contains(board.getCell(3, 3)));
	}
	
	// test for six steps
	@Test
	public void testTargets2_6_6() {
		// Test first location (0,3)
		BoardCell cell1 = board.getCell(0, 3);
		board.calcTargets(cell1, 6);
		Set targets1 = board.getTargets();
		assertEquals(7, targets1.size());
		assertTrue(targets1.contains(board.getCell(1, 0)));
		assertTrue(targets1.contains(board.getCell(0, 1)));
		assertTrue(targets1.contains(board.getCell(3, 0)));
		assertTrue(targets1.contains(board.getCell(2, 1)));
		assertTrue(targets1.contains(board.getCell(1, 2)));
		assertTrue(targets1.contains(board.getCell(3, 2)));
		assertTrue(targets1.contains(board.getCell(2, 3)));
	}
	
	@Test
	public void testTargets1_1_6() {
		// Test second location (1,1)
		BoardCell cell2 = board.getCell(1, 1);
		board.calcTargets(cell2, 6);
		Set targets2 = board.getTargets();
		assertEquals(7, targets2.size());
		assertTrue(targets2.contains(board.getCell(0, 0)));
		assertTrue(targets2.contains(board.getCell(2, 0)));
		assertTrue(targets2.contains(board.getCell(3, 1)));
		assertTrue(targets2.contains(board.getCell(0, 2)));
		assertTrue(targets2.contains(board.getCell(2, 2)));
		assertTrue(targets2.contains(board.getCell(1, 3)));
		assertTrue(targets2.contains(board.getCell(3, 3)));
	}
}
