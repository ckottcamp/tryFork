package experiment;

import java.util.*;

public class IntBoard {
	private BoardCell[][] boardGrid;
	private Map<BoardCell, Set<BoardCell>> adjList;
	private Set<BoardCell> visitedList;
	private Set<BoardCell> targetList;
	
	public final static int BOARD_SIZE = 4;
	
	public IntBoard() {
		boardGrid = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		adjList = new HashMap<BoardCell, Set<BoardCell>>();
		// TODO: Fix these errors:
		//visitedList = new Set<BoardCell>();
		//targetList = new Set<BoardCell>();
		
	}
	
	public void calcAdjacencies() {
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		return;
	}
	
	public Set<BoardCell> getTargets() {
		return null;
	}
	
	public Set<BoardCell> getAdjList(BoardCell c) {
		return null;
	}
	
	public BoardCell getCell(int x, int y) {
		return null;
	}
}
