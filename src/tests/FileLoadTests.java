package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import clueGame.*;

public class FileLoadTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	// NOTE: Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use our config files
		board.setConfigFiles("clueBoard.csv", "legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	/**
	 * Ensure the legend file is loaded correctly (correct number of rooms, 
	 * test several entries including first and last in file)
	 */
	@Test
	public void testRooms() {
		// Get the map of initial => room 
		Map<Character, String> legend = board.getLegend();
		// Ensure we read the correct number of rooms
		assertEquals(LEGEND_SIZE, legend.size());
		// To ensure data is correctly loaded, test retrieving a few rooms 
		// from the hash, including the first and last in the file and a few others
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
		// Ensure we have the proper number of rows and columns
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
		BoardCell c = board.getCellAt(2, 3);
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
