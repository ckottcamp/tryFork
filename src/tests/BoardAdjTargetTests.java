package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {

	private static Board gameBoard;
	
	/**
	 * Runs before the entire class. Initializes a single game board
	 */
	@BeforeClass
	public static void testSetUp() {
		gameBoard = Board.getInstance();
		gameBoard.setConfigFiles("clueBoard.csv", "legend.txt");
		gameBoard.initialize();
	}

	/**
	 * This set of tests will test the various walkway situations.
	 * EXCEL COLOR CODE: ORANGE
	 */
	@Test
	public void testWalkwayAdjacency() {
		//This tests a walkway cell that has only other walkway cells adjacent to it
		Set<BoardCell> testSet = gameBoard.getAdjList(16, 13);
		assertEquals(4, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(16, 12)));
		assertTrue(testSet.contains(gameBoard.getCellAt(16, 14)));
		assertTrue(testSet.contains(gameBoard.getCellAt(17, 13)));
		assertTrue(testSet.contains(gameBoard.getCellAt(15, 13)));
		
		//This tests adjacency on a cell that is on the top wall
		testSet = gameBoard.getAdjList(0, 3);
		assertEquals(3, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(0, 2)));
		assertTrue(testSet.contains(gameBoard.getCellAt(0, 4)));
		assertTrue(testSet.contains(gameBoard.getCellAt(1, 3)));
		
		//This tests adjacency on a cell that is on the left wall
		testSet = gameBoard.getAdjList(13, 0);
		assertEquals(3, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(13, 1)));
		assertTrue(testSet.contains(gameBoard.getCellAt(12, 0)));
		assertTrue(testSet.contains(gameBoard.getCellAt(14, 0)));
		
		//This tests adjacency on a cell that is on the right wall; this test also
		//doubles as a check for a cell between two rooms. This is due to the board
		//layout
		testSet = gameBoard.getAdjList(13, 22);
		assertEquals(1, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(13, 21)));

		//This tests adjacency on a cell that is on the bottom wall
		testSet = gameBoard.getAdjList(21, 13);
		assertEquals(3, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(21, 12)));
		assertTrue(testSet.contains(gameBoard.getCellAt(21, 14)));
		assertTrue(testSet.contains(gameBoard.getCellAt(20, 13)));
		
		//NOTE: since our specific layout file does not have any walkways that are
		//adjacent to a door with the wrong direction, we have not included any tests
		//for that situation
	}
	
	/**
	 * This tests adjacency for walkway cells that are next to a room cell that is NOT a doorway
	 * EXCEL COLOR CODE: PURPLE
	 */
	@Test
	public void testRoomCellNoDoor() {
		//Tests between two rooms
		Set<BoardCell> testSet = gameBoard.getAdjList(15, 6);
		assertEquals(2, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(14, 6)));
		assertTrue(testSet.contains(gameBoard.getCellAt(16, 6)));
		
		//Tests between a room and the closet
		testSet = gameBoard.getAdjList(6, 15);
		assertEquals(2, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(6, 16)));
		assertTrue(testSet.contains(gameBoard.getCellAt(6, 14)));
		
	}
	
	/**
	 * This test will check to see if cells that are adjacent to doorways with the proper
	 * direction will actually include the doors in the adjacency lists
	 * EXCEL COLOR CODE: GREEN
	 */
	@Test
	public void testWalkwayDoorAdjacency() {
		//Door is on the left
		Set<BoardCell> testSet = gameBoard.getAdjList(9, 6);
		assertEquals(4, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 5)));
		assertTrue(testSet.contains(gameBoard.getCellAt(8, 6)));
		assertTrue(testSet.contains(gameBoard.getCellAt(10, 6)));
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 7)));
		
		//Door is on the right
		testSet = gameBoard.getAdjList(9, 17);
		assertEquals(4, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(8, 17)));
		assertTrue(testSet.contains(gameBoard.getCellAt(10, 17)));
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 16)));
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 18)));
		
		
		//Door is below
		testSet = gameBoard.getAdjList(16, 0);
		assertEquals(2, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(15, 0)));
		assertTrue(testSet.contains(gameBoard.getCellAt(17, 0)));
		
		//Door is above
		testSet = gameBoard.getAdjList(5, 10);
		assertEquals(4, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(4, 10)));
		assertTrue(testSet.contains(gameBoard.getCellAt(6, 10)));
		assertTrue(testSet.contains(gameBoard.getCellAt(5, 9)));
		assertTrue(testSet.contains(gameBoard.getCellAt(5, 11)));
		
	}
	
	/** This test makes sure that cells that are doorways themselves will only have one
	 * cell in their adjacency lists.
	 * EXCEL COLOR CODE: BROWN
	 */
	@Test
	public void testDoorAdjacency() {
		Set<BoardCell> testSet = gameBoard.getAdjList(9, 5);
		assertEquals(1, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 6)));
		
		testSet = gameBoard.getAdjList(2, 21);
		assertEquals(1, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(2, 20)));
	}
	
	/**
	 * This test makes sure that there are no "adjacent cells" within a room.
	 * i.e. the player cannot move inside of a room
	 * EXCEL COLOR CODE: BLACK
	 */
	@Test
	public void testAdjacencyInRoom() {
		//Tests for a walkway above
		Set<BoardCell> testSet = gameBoard.getAdjList(14, 3);
		assertEquals(0, testSet.size());
		//Tests for a walkway to the left and above
		testSet = gameBoard.getAdjList(14, 7);
		assertEquals(0, testSet.size());
		//Tests for a cell in the middle of the room
		testSet = gameBoard.getAdjList(2, 9);
		assertEquals(0, testSet.size());
		//Tests a corner of the board
		testSet = gameBoard.getAdjList(0, 0);
		assertEquals(0, testSet.size());
		//Tests for a cell next to a door
		testSet = gameBoard.getAdjList(11, 5);
		assertEquals(0, testSet.size());
	}
	
	
	
	// Targets along walkways, at various distances //
	// Note: there is one extra testTargetXxxxSteps function (5/4)
	
	/** 
	 * Tests of just walkways, 1 STEP, includes on edge of board
	 * and beside room
	 * These are DARK BLUE on the spreadsheet
	 */
	@Test
	public void testTargetsOneStep() {
		// Beside Room
		gameBoard.calcTargets(13, 18, 1);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(13, 19)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 17)));
		
		// Edge of Board
	//	targets.clear();
		gameBoard.calcTargets(21, 19, 1);
		targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(20, 19)));
	}
	
	/** 
	 * Tests of just walkways, 2 STEPS
	 * These are DARK BLUE on the spreadsheet
	 */
	@Test
	public void testTargetsTwoSteps() {
		gameBoard.calcTargets(13, 18, 2);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(13, 16)));
		assertTrue(targets.contains(gameBoard.getCellAt(12, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 20)));
		assertTrue(targets.contains(gameBoard.getCellAt(14, 19)));
		
		//targets.clear();
		gameBoard.calcTargets(21, 19, 2);
		targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(19, 19)));
	}
	
	/** 
	 * Tests of just walkways, 3 STEPS
	 * These are DARK BLUE on the spreadsheet
	 */
	@Test
	public void testTargetsThreeSteps() {
		gameBoard.calcTargets(13, 18, 3);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(11, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(12, 16)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 15)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 21)));
		assertTrue(targets.contains(gameBoard.getCellAt(15, 19)));
		
	//	targets.clear();
		gameBoard.calcTargets(21, 19, 3);
		targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(18, 19)));
	}
	
	/** 
	 * Tests of just walkways, 5 STEPS
	 * These are DARK BLUE on the spreadsheet
	 */
	@Test
	public void testTargetsFiveSteps() {
		gameBoard.calcTargets(13, 18, 5);
		Set<BoardCell> targets= gameBoard.getTargets();
		//System.out.println(targets);
		assertEquals(12, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(9, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(11, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(12, 16)));
		assertTrue(targets.contains(gameBoard.getCellAt(10, 16)));
		assertTrue(targets.contains(gameBoard.getCellAt(10, 18)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 13)));
		assertTrue(targets.contains(gameBoard.getCellAt(13, 15)));
		assertTrue(targets.contains(gameBoard.getCellAt(12, 14)));
		assertTrue(targets.contains(gameBoard.getCellAt(14, 14)));
		assertTrue(targets.contains(gameBoard.getCellAt(16, 18)));
		assertTrue(targets.contains(gameBoard.getCellAt(16, 20)));
		assertTrue(targets.contains(gameBoard.getCellAt(17, 19)));
		
		//targets.clear();
		gameBoard.calcTargets(21, 19, 5);
		targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(16, 19)));
	}
	
	/** 
	 * Tests of just walkways, 6 STEPS
	 * These are DARK BLUE on the spreadsheet
	 */
	@Test
	public void testTargetsSixSteps() {
		gameBoard.calcTargets(13, 18, 6);
		Set<BoardCell> targets= gameBoard.getTargets();
		System.out.println(targets);
		assertEquals(17, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(16, 21)));//
		assertTrue(targets.contains(gameBoard.getCellAt(16, 17)));//
		assertTrue(targets.contains(gameBoard.getCellAt(18, 19)));//
		assertTrue(targets.contains(gameBoard.getCellAt(15, 14)));//
		assertTrue(targets.contains(gameBoard.getCellAt(14, 13)));//
		assertTrue(targets.contains(gameBoard.getCellAt(13, 14)));//
		assertTrue(targets.contains(gameBoard.getCellAt(13, 12)));//
		assertTrue(targets.contains(gameBoard.getCellAt(12, 13)));//
		assertTrue(targets.contains(gameBoard.getCellAt(12, 15)));//
		assertTrue(targets.contains(gameBoard.getCellAt(11, 16)));//
		assertTrue(targets.contains(gameBoard.getCellAt(10, 17)));//
		assertTrue(targets.contains(gameBoard.getCellAt(9, 16)));//
		assertTrue(targets.contains(gameBoard.getCellAt(9, 18)));//
		assertTrue(targets.contains(gameBoard.getCellAt(10, 18))); // This is the cell that should be in there but isn't
		assertTrue(targets.contains(gameBoard.getCellAt(8, 17)));//
		assertTrue(targets.contains(gameBoard.getCellAt(12, 17)));//
		assertTrue(targets.contains(gameBoard.getCellAt(13, 16)));//
	
	//	targets.clear();
		gameBoard.calcTargets(21, 19, 6);
		targets= gameBoard.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(16, 18)));
		assertTrue(targets.contains(gameBoard.getCellAt(16, 20)));
		assertTrue(targets.contains(gameBoard.getCellAt(15, 19)));
	}
	
	
	// Targets that allow the user to enter a room //
	
	/** 
	 * Tests of just walkways, Make sure a door is a target
	 * This is DARK BLUE on the spreadsheet
	 */
	@Test 
	public void testTargetsIntoRoom() {
		// One room is exactly 2 away
		gameBoard.calcTargets(18, 6, 1);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(3, targets.size());
		// directly up and down
		assertTrue(targets.contains(gameBoard.getCellAt(17, 6)));
		assertTrue(targets.contains(gameBoard.getCellAt(19, 6)));
		// one right - the door
		assertTrue(targets.contains(gameBoard.getCellAt(18, 7)));
		
	}
	
	/** 
	 * Test getting into room, doesn't require all steps
	 * These are DARK BLUE on the planning spreadsheet
	 */
	@Test
	public void testTargetsIntoRoomShortcut() {
		gameBoard.calcTargets(3, 20, 3);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(6, targets.size());
		// directly up and down
		assertTrue(targets.contains(gameBoard.getCellAt(0, 20)));
		assertTrue(targets.contains(gameBoard.getCellAt(6, 20)));
		// doors on right
		assertTrue(targets.contains(gameBoard.getCellAt(3, 21)));
		assertTrue(targets.contains(gameBoard.getCellAt(2, 21)));
		// doors on left
		assertTrue(targets.contains(gameBoard.getCellAt(3, 19)));
		assertTrue(targets.contains(gameBoard.getCellAt(2, 19)));
	}
	
	
	// Targets calculated when leaving a room //
	
	/** 
	 * Test getting out of a room
	 * These are DARK BLUE on the planning spreadsheet
	 */
	@Test
	public void testRoomExit() {
		// Take one step, essentially just the adj list
		gameBoard.calcTargets(4, 10, 1);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(5, 10)));
		
		// Take two steps
	//	targets.clear();
		gameBoard.calcTargets(4, 10, 2);
		targets= gameBoard.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(5, 11)));
		assertTrue(targets.contains(gameBoard.getCellAt(5, 9)));
		assertTrue(targets.contains(gameBoard.getCellAt(6, 10)));
	}
	
	
	/** 
	 * Test getting out of a room with adjacent doors
	 * These are DARK BLUE on the planning spreadsheet
	 */
	@Test
	public void testRoomWithAdjDoorsExit() {
		// Take one step
		gameBoard.calcTargets(10, 18, 1);
		Set<BoardCell> targets= gameBoard.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(10, 17)));
		
		// Take three steps
	//	targets.clear();
		gameBoard.calcTargets(10, 18, 3);
		targets= gameBoard.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(gameBoard.getCellAt(8, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(12, 17)));
		assertTrue(targets.contains(gameBoard.getCellAt(9, 16)));
		assertTrue(targets.contains(gameBoard.getCellAt(11, 16)));
	}
	
}
