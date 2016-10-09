package tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class GameBoardAdjacentTargetTests {

	private static Board gameBoard;
	@BeforeClass
	public static void testSetUp() {
		gameBoard = Board.getInstance();
		gameBoard.setConfigFiles("clueBoard.csv", "legend.txt");
		gameBoard.initialize();
	}

	
	//This set of tests will test the various walkway situations.
	//EXCEL COLOR CODE: ORANGE
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
	
	//This tests adjacency for walkway cells that are next to a room cell that is NOT a doorway
	//EXCEL COLOR CODE: PURPLE
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
	
	
	//This test will check to see if cells that are adjacent to doorways with the proper
	//direction will actually include the doors in the adjacency lists
	//EXCEL COLOR CODE: GREEN
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
		assertEquals(3, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(8, 17)));
		assertTrue(testSet.contains(gameBoard.getCellAt(10, 17)));
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 16)));
		
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
	
	//This test makes sure that cells that are doorways themselves will only have one
	//cell in their adjacency lists.
	//EXCEL COLOR CODE: BROWN
	@Test
	public void testDoorAdjacency() {
		Set<BoardCell> testSet = gameBoard.getAdjList(9, 5);
		assertEquals(1, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(9, 6)));
		
		testSet = gameBoard.getAdjList(2, 21);
		assertEquals(1, testSet.size());
		assertTrue(testSet.contains(gameBoard.getCellAt(2, 20)));
	}
	
	//This test makes sure that there are no "adjacent cells" within a room.
	//i.e. the player cannot move inside of a room
	//EXCEL COLOR CODE: BLACK
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
	
	
	//This set of tests will test the target selection algorithm
	
	
	
	
}
