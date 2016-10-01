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
		// TODO: should these be TreeSets or something eles?????
		visitedList = new TreeSet<BoardCell>();
		targetList = new TreeSet<BoardCell>();
	}
	
	public void calcAdjacencies() {
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		return;
	}
	
	public Set<BoardCell> getTargets() {
		return targetList;
	}
	
	public Set<BoardCell> getAdjList(BoardCell currentCell) {
		return adjList.get(currentCell);
	}
	
	public BoardCell getCell(int x, int y) {
		return boardGrid[x][y];
	}
}
