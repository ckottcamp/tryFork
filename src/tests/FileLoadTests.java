package tests;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;
import clueGame.*;

public class FileLoadTests {
	// Constants that I will use to test whether the file was loaded correctly
	public static final int LEGEND_SIZE = 9;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;

	// NOTE: Board static because I only want to set it up one 
	// time (using @BeforeClass), no need to do setup before each test.
	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("clueBoard.csv", "legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	
	
	
	
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
