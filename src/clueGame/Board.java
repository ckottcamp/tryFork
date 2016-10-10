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
		this.calcAdjacencies();
		
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
			// NOTE: CSV file delimiter is "," not ", "
			boardArray = tempString.split(",");
			if (numColumns != 0 && boardArray.length != numColumns) {
				// Makes sure that every row has the same number of columns
				throw new BadConfigFormatException(boardConfigFile);
			}
			numColumns = boardArray.length;
			for (int i = 0; i < numColumns; i++) {
				board[j][i] = new BoardCell(j,i,boardArray[i]);
				if (rooms.containsKey(board[j][i].getInitial()) == false) {
					// Make sure each room is contained in the legend
					throw new BadConfigFormatException(boardConfigFile);
				}
			}
			j++;
		}
		numRows = j;
	}
	
	public void calcAdjacencies() {
		// This function will be completed, but at a later date during Section III
		/*
		 * COPIED FROM INTBOARD
		 * 
		 * 	x->cols
		 *	y	 		2
		 *	| 		1	c	3
		 *	v 			4
		 *rows
		 *
		 
		for (int x=0; x<numRows; x++) {
			for (int y=0; y<numColumns; y++) {
				Set<BoardCell> tempAdj = new HashSet<BoardCell>();
				// TODO: we will need to add && cellType == walkable later
				// add 1
				if (x>0){tempAdj.add(board[y][x-1]);}
				// add 2
				if (y>0){tempAdj.add(board[y-1][x]);}
				// add 3
				if (x<numRows - 1){tempAdj.add(board[y][x+1]);}
				// add 4
				if (y<numColumns - 1){tempAdj.add(board[y+1][x]);}
				adjMatrix.put(board[y][x], tempAdj);
			}
		}
		return;
		*/
		
		
	}
	
	public void calcTargets(int row, int col, int pathlength) {
		// This function will be completed, but at a later date during Section III		
		
		/*
		 * COPIED FROM INTBOARD
		 * 
		visitedList.add(startCell);
		for (BoardCell tempCell : adjList.get(startCell)) {
			if (visitedList.contains(tempCell)) {
				continue;
			} else {
				visitedList.add(tempCell);
				if (pathLength == 1) {
					targetList.add(tempCell);
					visitedList.remove(tempCell);
					continue;
				} else {
					calcTargets(tempCell, pathLength - 1);
				}
					visitedList.remove(tempCell);
			}
			

		}
		return;
		 */
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
	
	public Set<BoardCell> getAdjList(int x, int y) {
		Set<BoardCell> adjList = new HashSet<BoardCell>();
		return adjList;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
}
