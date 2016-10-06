package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import clueGame.*;

public class FileLoadTests {
	// Constants that we will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		board = Board.getInstance();
		board.setConfigFiles("clueBoard.csv", "legend.txt");		
		board.initialize();
	}
	
	/**
	 * Ensure the legend file is loaded correctly (correct number of rooms, 
	 * test several entries including first and last in file)
	 */
	@Test
	public void testRooms() {
		Map<Character, String> legend = board.getLegend();
		//Checks for the right # of rooms
		assertEquals(LEGEND_SIZE, legend.size());

		//Checks all rooms in the legend
		assertEquals("Closet", legend.get('X'));
		assertEquals("Walkway", legend.get('W'));
		assertEquals("Bedroom", legend.get('B'));
		assertEquals("Kitchen", legend.get('K'));
		assertEquals("Library", legend.get('L'));
		assertEquals("Dining Room", legend.get('D'));
		assertEquals("Bathroom", legend.get('R'));
		assertEquals("Dungeon", legend.get('Z'));
		assertEquals("Gaming Room", legend.get('G'));
		assertEquals("Living Room", legend.get('V'));
		assertEquals("Balcony", legend.get('A'));
		
	}
	
	/**
	 * Ensure the correct number of rows/columns have been read
	 */
	@Test
	public void testBoardDimensions() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	
	
	/**
	 * Verify at least one doorway in each direction. 
	 * Also verify cells that don't contain doorways return false for isDoorway.
	 * Test a doorway in each direction (RIGHT/LEFT/UP/DOWN), plus 
	 * two cells that are not a doorway.
	 * These cells are white on the planning spreadsheet
	 */
	@Test
	public void FourDoorDirections() {
		// LR
		BoardCell c = board.getCellAt(3, 2);
		assertTrue(c.isDoorway());
		assertEquals(DoorDirection.RIGHT, c.getDoorDirection());
		// VD
		c = board.getCellAt(4, 10);
		assertTrue(c.isDoorway());
		assertEquals(DoorDirection.DOWN, c.getDoorDirection());
		// GL
		c = board.getCellAt(16, 21);
		assertTrue(c.isDoorway());
		assertEquals(DoorDirection.LEFT, c.getDoorDirection());
		// ZU
		c = board.getCellAt(17, 0);
		assertTrue(c.isDoorway());
		assertEquals(DoorDirection.UP, c.getDoorDirection());
		// Test that room pieces that aren't doors know it
		c = board.getCellAt(0, 0);
		assertFalse(c.isDoorway());	
		c = board.getCellAt(17, 16);
		assertFalse(c.isDoorway());	
		
		// Test that walkways are not doors
		c = board.getCellAt(14, 13);
		assertFalse(c.isDoorway());
		c = board.getCellAt(5, 10);
		assertFalse(c.isDoorway());

	}
	
	/**
	 * Test that the correct number of doors have been loaded.
	 */
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell c = board.getCellAt(row, col);
				if (c.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(16, numDoors);
	}
	
	/**
	 * Test some of the cells to ensure they have the correct initial
	 */
	@Test
	public void testRoomInitials() {
		// Test room cells that are not doors
		assertEquals('L', board.getCellAt(0, 0).getInitial());
		assertEquals('V', board.getCellAt(2, 10).getInitial());
		assertEquals('D', board.getCellAt(3, 17).getInitial());
		assertEquals('A', board.getCellAt(4, 22).getInitial());
		assertEquals('K', board.getCellAt(9, 20).getInitial());
		assertEquals('G', board.getCellAt(18, 22).getInitial());
		assertEquals('R', board.getCellAt(18, 18).getInitial());
		assertEquals('Z', board.getCellAt(18, 2).getInitial());
		assertEquals('X', board.getCellAt(9,13).getInitial());
		// Now test room cells that are also doors
		assertEquals('Z', board.getCellAt(17,0).getInitial());
		assertEquals('D', board.getCellAt(3,19).getInitial());
		
		
	}
	
}
