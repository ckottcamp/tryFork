package tests;

import static org.junit.Assert.*;
import java.util.*;
import experiment.*;

import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	/*
	 * Corners are numbered as:
	 * 		0___________1
	 * 		|			|
	 * 		|			|
	 * 		|			|
	 * 		|			|
	 * 		3___________2
	 */
	private IntBoard board;
	
	
	// @Before method to set up your IntBoard.
	@Before
	public void executedBeforeEach() {
		board = new IntBoard();
	}
	
	
	/*
	 * Test adjacencies for top left corner
	 *
	 */
	@Test
	public void testAdjacency0() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency1() {
		fail("Not yet implemented");
	}
	@Test
	public void testAdjacency2() {
		fail("Not yet implemented");
	}
	@Test
	public void testAdjacency3() {
		fail("Not yet implemented");
	}
	
	
	
	
	
	/*
	@Test
	public void testTargets0_3() {
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
	}
	*
	*/
	

}
