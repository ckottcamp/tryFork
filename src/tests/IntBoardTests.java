package tests;

import static org.junit.Assert.*;
import java.util.*;
import experiment.*;

import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	
	private IntBoard board;
	
	// @Before method to set up IntBoard.
	@Before
	public void executedBeforeEach() {
		board = new IntBoard();
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
	public void testTargets0_3() {
		// Test first location
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
		// Test second location
	}
	
	// Test for one step
	@Test
	public void testTargets1_1() {
		// Test first location
		// Test second location
	}
	
	// test for six steps
	@Test
	public void testTargets2_6() {
		// Test first location
		// Test second location
	}
	

}
