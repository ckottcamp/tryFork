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
	private Map<BoardCell, Set<BoardCell>> adjMatrix = new HashMap<BoardCell, Set<BoardCell>>();
	private Set<BoardCell> visitedList = new HashSet<BoardCell>();
	private Set<BoardCell> targets = new HashSet<BoardCell>();
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
		for (int y=0; y<numRows; y++) {
			for (int x=0; x<numColumns; x++) {
				Set<BoardCell> adj = new HashSet<BoardCell>();
			/*	if (x > 0) {
					if ((board[y][x - 1].isDoorway() && board[y][x - 1].getDoorDirection() == DoorDirection.RIGHT) || board[y][x - 1].isWalkway()) {
						adj.add(board[y][x - 1]);
					}
				}
				
				if (y > 0) {
					if ((board[y - 1][x].isDoorway() && board[y - 1][x].getDoorDirection() == DoorDirection.DOWN) || board[y - 1][x].isWalkway()) {
						adj.add(board[y - 1][x]);
					}
				}
				
				if (x < numColumns - 1) {
					if ((board[y][x + 1].isDoorway() && board[y][x + 1].getDoorDirection() == DoorDirection.LEFT) || board[y][x + 1].isWalkway()) {
						adj.add(board[y][x + 1]);
					}
				}
				
				if (y < numRows - 1) {
					if ((board[y + 1][x].isDoorway() && board[y + 1][x].getDoorDirection() == DoorDirection.UP) || board[y + 1][x].isWalkway()) {
						adj.add(board[y + 1][x]);
					}
				}*/
				// add 1
				if (x>0 && isAdj(board[y][x], board[y][x-1], 1) ){adj.add(board[y][x-1]);}
				// add 2
				if (y>0 && isAdj(board[y][x], board[y-1][x], 2) ){adj.add(board[y-1][x]);}
				// add 3
				if (x<numColumns-1 && isAdj(board[y][x], board[y][x+1], 3) ){adj.add(board[y][x+1]);}
				// add 4
				if (y<numRows-1 && isAdj(board[y][x], board[y+1][x], 4) ){adj.add(board[y+1][x]);}
				// Fill Map 
				adjMatrix.put(board[y][x], adj);
			}
		}
		return;
	}
	
	private boolean isAdj(BoardCell c, BoardCell a, int loc) {
		/* 	x->cols
		 *	y	 		2
		 *	| 		1	c	3
		 *	v 			4
		 *rows
		 */
		DoorDirection dir = DoorDirection.NONE;
		switch (loc) {
		case 1:
			dir = DoorDirection.RIGHT;
		case 2:
			dir = DoorDirection.DOWN;
		case 3:
			dir = DoorDirection.LEFT;
		case 4:
			dir = DoorDirection.UP;
		}
		
		// is the current cell a door
		if (c.isDoorway() && a.isWalkway()) {
			// then the only adjacent cells can be walkway
			return true;
		}
		// or current cell is a walkway?
		else if (c.isWalkway() && (a.isWalkway() || (a.isDoorway() && a.getDoorDirection() == dir))) {
			// then adj cell must either be a walkway or a door with the correct orientation
			return true;
		}
		else {
			return false;
		}
	}
	
	
<<<<<<< HEAD
	public void calcTargets(int row, int col, int pathlength) {
		
		/*
		 * COPIED FROM INTBOARD
		 * 
		visitedList.add(startCell);
		for (BoardCell tempCell : adjList.get(startCell)) {
			if (visitedList.contains(tempCell)) {
=======
	public void calcTargets(int row, int col, int pathLength) {
		BoardCell tempCell = getCellAt(row, col);
		visitedList.add(tempCell);
		for (BoardCell adjCell : adjMatrix.get(tempCell)) {
			if (visitedList.contains(adjCell)) {
>>>>>>> 1bed5cf248085cded2013dc9a2fbae8a48daf73c
				continue;
			}
				visitedList.add(adjCell);
				if (pathLength == 1) {
					targets.add(adjCell);
					visitedList.remove(adjCell);
					continue;
				} else {
					calcTargets(adjCell.getRow(), adjCell.getCol(), pathLength - 1);
				}
		//		visitedList.remove(adjCell);
		}
		return;		 
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
	
	public Set<BoardCell> getAdjList(int r, int c) {
		return adjMatrix.get(board[r][c]);
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
}
