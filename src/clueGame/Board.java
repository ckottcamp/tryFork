package clueGame;

import java.util.*;

public class Board {
	public static final int MAX_BOARD_SIZE= 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> rooms;
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
		
	}
	
	public void loadRoomConfig() {
		
	}
	
	public void loadBoardConfig() {
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathlength) {
		
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	
	public void setConfigFiles(String string, String string2) {
		
	}
	
	public Map<Character, String> getLegend() {
		return rooms;
	}
}
