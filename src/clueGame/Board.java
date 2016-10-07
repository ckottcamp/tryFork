package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Board {
	public static final int MAX_BOARD_SIZE= 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board = new BoardCell[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	private Map<Character, String> rooms = new HashMap<Character, String>();
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
	private static Board gameBoard = new Board();
	//rooms = new HashMap<Character, String>();
	private Board() {}	
	public static Board getInstance() {
		return gameBoard;
	}
	
	public void initialize() {
		try {
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (BadConfigFormatException f) {
			f.printStackTrace();
		}
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader roomFile = new FileReader(roomConfigFile);
		Scanner tempScanner = new Scanner(roomFile);
		while (tempScanner.hasNextLine()) {
			String tempString = tempScanner.nextLine();
			String[] legendArray = new String[3];
			legendArray = tempString.split(", "); // NOTE: Delimiter is ", "
			rooms.put(legendArray[0].charAt(0), legendArray[1]);
			
			if (!legendArray[2].equalsIgnoreCase("Card") && 
					!legendArray[2].equalsIgnoreCase("Other") ){
				throw new BadConfigFormatException(roomConfigFile);
			}
			
		}
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader boardFile = new FileReader(boardConfigFile);
		Scanner tempScanner = new Scanner(boardFile);
		int j = 0;
		while (tempScanner.hasNextLine()) {
			String tempString = tempScanner.nextLine();
			String[] boardArray = new String[MAX_BOARD_SIZE];
			boardArray = tempString.split(","); // NOTE: CSV file delimiter is "," not ", "
			numColumns = boardArray.length;
			for (int i = 0; i < numColumns; i++) {
				board[j][i] = new BoardCell(j,i,boardArray[i]);
			}
			j++;
		}
		numRows = j;
		for (int i = 0; i < numRows - 1; ++i) {
			if (board[i].length != board[i + 1].length) {				
				// Makes sure that every row has the same number of columns
				System.out.println();
				throw new BadConfigFormatException(boardConfigFile);
			}
		}
		for (int i = 0; i < numRows; ++i) {
			for (int k = 0; k < numColumns; ++k) {
				if (rooms.containsKey(board[i][k].getInitial()) == false) {
					throw new BadConfigFormatException(boardConfigFile);
				}
			}
		}
	}
	
	public void calcAdjacencies() {
		// This function will be completed, but at a later date during Section III
	}
	
	public void calcTargets(BoardCell cell, int pathlength) {
		// This function will be completed, but at a later date during Section III		
	}	
	
	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public void setConfigFiles(String board, String legend) {
		boardConfigFile = board;
		roomConfigFile = legend;
	}
	
	public Map<Character, String> getLegend() {
		return rooms;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
}
